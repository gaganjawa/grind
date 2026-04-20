package org.multithreading.boundedblockingq;

import java.util.concurrent.*;

/**
 * Small client that demonstrates and exercises BoundedBlockingQueue.
 * Run this class to manually observe producer/consumer behavior.
 */
public class BoundedBlockingQueueClient {

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            System.out.println("args: " + String.join(",", args));
        }

        final int capacity = 5;
        final int total = 50;

        BoundedBlockingQueue queue = new BoundedBlockingQueue(capacity);

        try (AutoCloseableExecutor ex = AutoCloseableExecutor.newFixedThreadPool(2)) {
            Callable<Void> producer = () -> {
                for (int i = 0; i < total; i++) {
                    try {
                        queue.enqueue(i);
                        System.out.printf("Produced: %d (approx)\n", i);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                return null;
            };

            Callable<Void> consumer = () -> {
                for (int i = 0; i < total; i++) {
                    try {
                        int v = queue.dequeue();
                        System.out.printf("Consumed: %d\n", v);
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                return null;
            };

            Future<Void> pf = ex.submit(producer);
            Future<Void> cf = ex.submit(consumer);

            try {
                pf.get();
                cf.get();
            } catch (ExecutionException e) {
                System.err.println("Execution failed: " + e.getCause());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Demo finished");
        }
    }
}
