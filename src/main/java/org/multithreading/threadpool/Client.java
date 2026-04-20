package org.multithreading.threadpool;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
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

        // Give a moment for tasks to run
        Thread.sleep(1000);

        // Shutdown and wait for termination
        pool.shutdown();
        boolean terminated = pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Pool terminated: " + terminated);
    }
}
