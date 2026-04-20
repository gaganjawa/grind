package org.multithreading.boundedblockingq;

import java.util.concurrent.*;

/**
 * Small AutoCloseable wrapper around ExecutorService so tests can use try-with-resources and
 * automatically shut it down.
 */
public final class AutoCloseableExecutor implements AutoCloseable {
    private final ExecutorService executor;

    public AutoCloseableExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    public static AutoCloseableExecutor newFixedThreadPool(int nThreads) {
        return new AutoCloseableExecutor(Executors.newFixedThreadPool(nThreads));
    }

    public static AutoCloseableExecutor newSingleThreadExecutor() {
        return new AutoCloseableExecutor(Executors.newSingleThreadExecutor());
    }

    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    @Override
    public void close() {
        executor.shutdownNow();
    }
}
