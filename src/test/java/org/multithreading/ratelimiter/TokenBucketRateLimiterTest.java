package org.multithreading.ratelimiter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TokenBucketRateLimiterTest {

    @Test
    void testInitialBurst() {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 1);
        // first 5 requests should be allowed
        for (int i = 0; i < 5; i++) {
            assertTrue(limiter.allowRequest(), "request " + i + " should be allowed");
        }
        // 6th should be denied
        assertFalse(limiter.allowRequest(), "6th request should be denied");
    }

    @Test
    void testRefill() throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(2, 2); // 2 tokens/sec

        // consume both tokens
        assertTrue(limiter.allowRequest());
        assertTrue(limiter.allowRequest());
        assertFalse(limiter.allowRequest());

        // wait enough time for one token to be refilled (>= 500ms)
        Thread.sleep(600);

        // now one request should be allowed
        assertTrue(limiter.allowRequest(), "one token should have been refilled");
    }

    @Test
    void testCapacityNotExceeded() throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(3, 10); // 10 tokens/sec

        // consume one token
        assertTrue(limiter.allowRequest()); // tokens now 2

        // wait 1.1s so many tokens would be added but should be capped at capacity
        Thread.sleep(1100);

        // should have at most capacity tokens available -> allow 3 requests
        assertTrue(limiter.allowRequest());
        assertTrue(limiter.allowRequest());
        assertTrue(limiter.allowRequest());
        // next one should be denied
        assertFalse(limiter.allowRequest());
    }

    @Test
    void testConcurrency() throws InterruptedException {
        // ------------------ Test intent & setup ------------------
        // We want to stress the limiter's concurrency logic by having more threads
        // attempt a request simultaneously than available tokens.
        final int capacity = 10;            // limiter starts with this many tokens
        final int threads = 20;            // number of concurrent workers trying once
        // Set refillRate to 0 so tokens are NOT replenished during the test; this
        // makes the total number of successful requests deterministic (<= capacity)
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(capacity, 0); // no refill

        // ------------------ Executor to run tasks concurrently ------------------
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        try {
            // start latch: used so all workers wait and then begin attempting allowRequest()
            // at (nearly) the same time, increasing contention on the limiter.
            CountDownLatch start = new CountDownLatch(1);
            // done latch: main test thread waits on this until all workers finish.
            CountDownLatch done = new CountDownLatch(threads);
            // thread-safe counter to track how many threads received a token
            AtomicInteger allowedCount = new AtomicInteger(0);

            // Submit worker tasks. Each worker waits for the `start` latch, calls
            // limiter.allowRequest() exactly once, and increments the counter if allowed.
            for (int i = 0; i < threads; i++) {
                exec.execute(() -> {
                    try {
                        // all workers block here until test calls start.countDown()
                        start.await();
                        // single request attempt per worker
                        if (limiter.allowRequest()) {
                            allowedCount.incrementAndGet(); // thread-safe increment
                        }
                    } catch (InterruptedException e) {
                        // If interrupted, restore interrupt status and continue to cleanup
                        Thread.currentThread().interrupt();
                    } finally {
                        // always count down so the test doesn't hang waiting for completion
                        done.countDown();
                    }
                });
            }

            // Release all workers to run concurrently. This increases the chance of
            // revealing any race conditions inside allowRequest().
            start.countDown();

            // Wait until every worker has finished (ensures allowedCount is final)
            done.await();

            // ------------------ Invariant being checked ------------------
            // Because the limiter started with `capacity` tokens and refill=0,
            // exactly `capacity` of the `threads` attempts should succeed.
            // The limiter's internal lock must ensure the check-and-decrement is atomic,
            // so no more than `capacity` threads can get a token. We assert equality
            // rather than <= to catch bugs where the implementation might lose tokens
            // or where some workers never ran their attempt.
            assertEquals(capacity, allowedCount.get());
        } finally {
            // Ensure executor is shutdown to avoid resource leaks in test runners.
            // shutdownNow() is used as a defensive measure; since we waited for all
            // tasks to finish above, this is just cleanup.
            exec.shutdownNow();
        }
    }

    @Test
    void testZeroCapacity() throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(0, 1);
        assertFalse(limiter.allowRequest());
        Thread.sleep(1100);
        assertFalse(limiter.allowRequest());
    }
}
