package org.multithreading.threadpool;

import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CustomThreadPool pool = new CustomThreadPool(3, 10);

        // Submit simple tasks that print their thread name
        for (int i = 1; i <= 6; i++) {
            final int id = i;
            pool.submit(() -> {
                System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Also submit a Callable and use the returned Future
        Future<String> future = pool.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Callable-Result";
        });

        // Give a moment for tasks to run
        Thread.sleep(1000);

        // Retrieve the Callable result (waits if needed)
        try {
            String result = future.get();
            System.out.println("Callable returned: " + result);
        } catch (ExecutionException ee) {
            System.err.println("Callable execution failed: " + ee.getCause());
            throw ee; // rethrow to propagate
        }

        // Shutdown and wait for termination
        pool.shutdown();
        boolean terminated = pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Pool terminated: " + terminated);
    }
}
