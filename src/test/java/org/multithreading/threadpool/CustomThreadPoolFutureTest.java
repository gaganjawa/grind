package org.multithreading.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomThreadPoolFutureTest {

    @Test
    public void testCallableReturnsValue() throws Exception {
        CustomThreadPool pool = new CustomThreadPool(2, 10);
        Future<Integer> f = pool.submit(() -> 42);
        Integer res = f.get();
        assertEquals(42, res.intValue());
        pool.shutdown();
        assertTrue(pool.awaitTermination(1, TimeUnit.SECONDS));
    }

    @Test
    public void testCallableThrowsExecutionException() throws Exception {
        CustomThreadPool pool = new CustomThreadPool(1, 10);
        Future<String> f = pool.submit(() -> {
            throw new IllegalStateException("boom");
        });
        ExecutionException ee = assertThrows(ExecutionException.class, f::get);
        assertInstanceOf(IllegalStateException.class, ee.getCause());
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void testCancelQueuedTask() throws Exception {
        CustomThreadPool pool = new CustomThreadPool(1, 10);
        // occupy the single worker so next submitted task is queued
        pool.submit(() -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Future<String> f = pool.submit(() -> "ok");
        boolean canceled = f.cancel(false);
        assertTrue(canceled, "cancel should return true");
        assertTrue(f.isCancelled());
        assertTrue(f.isDone());
        assertThrows(CancellationException.class, () -> f.get());

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void testGetWithTimeout() throws Exception {
        CustomThreadPool pool = new CustomThreadPool(1, 10);
        Future<String> f = pool.submit(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "done";
        });

        ExecutionException ee = assertThrows(ExecutionException.class, () -> f.get(100, TimeUnit.MILLISECONDS));
        assertTrue(ee.getCause() instanceof TimeoutException);

        // wait for the task to finish normally then verify value
        Thread.sleep(600);
        assertEquals("done", f.get());

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }

    @Test
    public void testSubmitCallableAfterShutdownThrows() throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(1, 10);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);

        assertThrows(IllegalStateException.class, () -> pool.submit(() -> 1));
    }
}
