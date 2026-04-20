package org.multithreading.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class CustomThreadPoolTest {

    @Test
    public void testTasksRunAndShutdown() throws InterruptedException {
        int numThreads = 2;
        int capacity = 20;
        CustomThreadPool pool = new CustomThreadPool(numThreads, capacity);

        int tasks = 10;
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < tasks; i++) {
            pool.submit(() -> {
                // simulate some work
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                counter.incrementAndGet();
            });
        }

        // shutdown and wait up to 5 seconds for completion
        pool.shutdown();
        boolean terminated = pool.awaitTermination(5, TimeUnit.SECONDS);

        assertTrue(terminated, "Pool did not terminate in time");
        assertEquals(tasks, counter.get(), "Not all tasks completed");
    }

    @Test
    public void testSubmitAfterShutdownThrows() throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(1, 10);
        pool.shutdown();
        // wait for worker thread to exit
        pool.awaitTermination(1, TimeUnit.SECONDS);

        try {
            pool.submit(() -> {});
            fail("Expected IllegalStateException when submitting after shutdown");
        } catch (IllegalStateException ise) {
            // expected
        }
    }
}
