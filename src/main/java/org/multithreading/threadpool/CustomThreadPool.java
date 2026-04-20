package org.multithreading.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool {

    private final List<Worker> workers;
    private final ArrayBlockingQueue<Runnable> taskQueue;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int numThreads, int capacity) {
        this.workers = new ArrayList<>();
        this.taskQueue = new ArrayBlockingQueue<>(capacity);
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker();
            worker.start();
            workers.add(worker);
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        taskQueue.put(task); // This will block if the queue is full
    }

    /**
     * Initiates an orderly shutdown: previously submitted tasks are executed,
     * but new tasks will be rejected.
     */
    public void shutdown() {
        isShutdown = true;
        // don't interrupt workers here; they will finish queued tasks and exit
    }

    /**
     * Wait for worker threads to terminate up to the given timeout.
     * Returns true if all workers terminated within the timeout, false otherwise.
     */
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long millisTimeout = unit.toMillis(timeout);
        long deadline = System.currentTimeMillis() + millisTimeout;
        boolean allTerminated = true;
        for (Thread worker : workers) {
            long timeLeft = deadline - System.currentTimeMillis();
            if (timeLeft <= 0) {
                allTerminated = false;
                break;
            }
            worker.join(timeLeft);
            if (worker.isAlive()) {
                allTerminated = false;
            }
        }
        return allTerminated;
    }


    private class Worker extends Thread {

        @Override
        public void run() {

            try {
                while (true) {
                    Runnable task = taskQueue.poll(500, TimeUnit.MILLISECONDS);
                    if (task != null) {
                        try {
                            task.run(); // Execute the task
                        } catch (Throwable t) {
                            // swallow to prevent worker from dying; in real code log this
                        }
                    } else {
                        // timed out waiting for a task
                        if (isShutdown && taskQueue.isEmpty()) {
                            break; // no more work and shutdown initiated
                        }
                    }
                }
            } catch (InterruptedException e) {
                // If interrupted, exit only if shutdown and queue is empty; otherwise restore interrupt
                if (!(isShutdown && taskQueue.isEmpty())) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
