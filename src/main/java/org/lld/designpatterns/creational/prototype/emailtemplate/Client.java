package org.lld.designpatterns.creational.prototype.emailtemplate;

import java.util.concurrent.*;

public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Demonstrates usage of the prototype EmailTemplateRegistry and
        // concurrent usage patterns (ExecutorService, Future, ScheduledExecutorService,
        // CountDownLatch). Comments explain intent and the concurrency behaviour.

        // Use args to avoid 'never used' warnings from static analyzers. No semantic
        // behaviour depends on them in this example; we're only referencing the length.
        if (args != null && args.length > 0) {
            System.out.println("Client started with " + args.length + " arguments");
        }

        // Get a prototype email template and modify content for a single-threaded flow.
        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        welcomeEmail.setContent("Hello, welcome to our service!");
        welcomeEmail.send("gagan@gaganjawa.com");

        // Get another instance from the registry (should be a separate clone/prototype)
        // and modify it independently.
        EmailTemplate anotherWelcomeEmail = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
        anotherWelcomeEmail.setContent("Hi there, we're glad to have you!");
        anotherWelcomeEmail.send("jawa@gaganjawa.com");

        // Use a fixed thread pool to demonstrate obtaining templates from multiple threads.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            // Submit a Callable that obtains a template in a worker thread, modifies it,
            // and returns it. Using Future.get() below will block until the task completes.
            Future<EmailTemplate> a = executorService.submit(() -> {
                // This runs on a worker thread.
                EmailTemplate threadEmail = EmailTemplateRegistry.getTemplate("WELCOME_EMAIL");
                // Each thread can set its own content safely if the registry returns a clone
                // (prototype). If the registry returned a shared instance, this would be
                // a race and require synchronization.
                threadEmail.setContent("Thread-specific content");
                threadEmail.send("");
                return threadEmail;
            });

            // Blocks until the submitted Callable completes and returns the EmailTemplate.
            EmailTemplate b = a.get();
            // Use the returned template (log it) to avoid unused variable warnings.
            System.out.println("Worker thread returned template: " + b);

            // Use a CountDownLatch to wait for a scheduled repeating task to run N times.
            CountDownLatch lock = new CountDownLatch(5);

            // Scheduled executor to run a repeating task. We only need a single thread
            // for the scheduled task here; using a pool of 5 is unnecessary.
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            try {
                // Schedule a repeating task that prints and counts down the latch.
                // The latch ensures the main thread can wait for the task to run a fixed
                // number of times (5 runs in this example).
                ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
                    System.out.println("Hello World");
                    lock.countDown(); // decrement the latch on each run
                }, 500, 100, TimeUnit.MILLISECONDS);

                // Wait until the scheduled task has run 5 times (or the thread is interrupted).
                lock.await();

                // After the latch reaches zero we cancel the repeating task. We pass
                // `false` to allow the currently running invocation to finish.
                future.cancel(false);

                // Optionally you could inspect/return the last produced EmailTemplate `b`.
            } finally {
                // Clean up scheduled executor
                executor.shutdown();
                // Wait briefly for tasks to finish; if they don't terminate in time,
                // force shutdown to avoid hanging the JVM.
                boolean terminated = executor.awaitTermination(1, TimeUnit.SECONDS);
                if (!terminated) {
                    executor.shutdownNow();
                }
            }
        } finally {
            // Shut down the worker pool and wait for termination; force shutdown if needed.
            executorService.shutdown();
            boolean terminated = executorService.awaitTermination(1, TimeUnit.SECONDS);
            if (!terminated) {
                executorService.shutdownNow();
            }
        }
    }
}
