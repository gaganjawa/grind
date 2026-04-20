package org.multithreading.ratelimiter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class RateLimiterServiceTest {

    @Test
    void testAllowSingleUserInitialBurst() {
        RateLimiterService service = new RateLimiterService();
        String user = "user1";

        // The service creates a TokenBucketRateLimiter with capacity=5, refillRate=1
        // First 5 requests should be allowed (initial bucket full)
        for (int i = 0; i < 5; i++) {
            assertTrue(service.allow(user), "request " + i + " should be allowed");
        }
        // 6th request should be denied
        assertFalse(service.allow(user), "6th request should be denied");
    }

    @Test
    void testMultipleUsersIndependent() {
        RateLimiterService service = new RateLimiterService();
        String userA = "alice";
        String userB = "bob";

        // Each user has their own limiter (independent buckets)
        for (int i = 0; i < 5; i++) {
            assertTrue(service.allow(userA), "alice request " + i + " should be allowed");
            assertTrue(service.allow(userB), "bob request " + i + " should be allowed");
        }

        // Both should now be exhausted
        assertFalse(service.allow(userA), "alice should be exhausted");
        assertFalse(service.allow(userB), "bob should be exhausted");
    }

    @Test
    void testRefillPerUser() throws InterruptedException {
        RateLimiterService service = new RateLimiterService();
        String user = "charlie";

        // consume all tokens
        for (int i = 0; i < 5; i++) {
            assertTrue(service.allow(user));
        }
        assertFalse(service.allow(user));

        // Wait slightly more than 1 second so that refillRate=1 should add one token
        Thread.sleep(1100);

        // Now one request should be allowed again
        assertTrue(service.allow(user), "one token should have been refilled for charlie");
    }

    @Test
    void testConcurrencySingleUser() throws InterruptedException {
        RateLimiterService service = new RateLimiterService();
        String user = "dave";

        final int capacity = 5;      // as created by RateLimiterService
        final int threads = 20;      // more threads than capacity to create contention

        ExecutorService exec = Executors.newFixedThreadPool(threads);
        try {
            CountDownLatch start = new CountDownLatch(1);
            CountDownLatch done = new CountDownLatch(threads);
            AtomicInteger allowedCount = new AtomicInteger(0);

            for (int i = 0; i < threads; i++) {
                exec.execute(() -> {
                    try {
                        start.await();
                        if (service.allow(user)) {
                            allowedCount.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }
                });
            }

            // release workers to contend concurrently
            start.countDown();
            // wait for all to finish
            done.await();

            // exactly `capacity` attempts should have been allowed
            assertEquals(5, allowedCount.get());
        } finally {
            exec.shutdownNow();
        }
    }

    @Test
    void testConcurrencyMultipleUsers() throws InterruptedException {
        RateLimiterService service = new RateLimiterService();
        String user1 = "eve";
        String user2 = "frank";

        final int perUserCapacity = 5;
        final int threadsPerUser = 10;

        ExecutorService exec = Executors.newFixedThreadPool(threadsPerUser * 2);
        try {
            CountDownLatch start = new CountDownLatch(1);
            CountDownLatch done = new CountDownLatch(threadsPerUser * 2);
            AtomicInteger allowedUser1 = new AtomicInteger(0);
            AtomicInteger allowedUser2 = new AtomicInteger(0);

            // submit tasks for user1
            for (int i = 0; i < threadsPerUser; i++) {
                exec.execute(() -> {
                    try {
                        start.await();
                        if (service.allow(user1)) allowedUser1.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }
                });
            }

            // submit tasks for user2
            for (int i = 0; i < threadsPerUser; i++) {
                exec.execute(() -> {
                    try {
                        start.await();
                        if (service.allow(user2)) allowedUser2.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }
                });
            }

            // kick off all tasks
            start.countDown();
            done.await();

            // each user should have at most their own capacity tokens allowed
            assertEquals(perUserCapacity, allowedUser1.get(), "user1 should have exactly capacity successes");
            assertEquals(perUserCapacity, allowedUser2.get(), "user2 should have exactly capacity successes");
        } finally {
            exec.shutdownNow();
        }
    }
}
