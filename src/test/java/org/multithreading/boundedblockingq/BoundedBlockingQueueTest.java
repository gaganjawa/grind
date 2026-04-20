package org.multithreading.boundedblockingq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Simplified tests for BoundedBlockingQueue: single test covering producer/consumer and FIFO.
 */
public class BoundedBlockingQueueTest {

    @Test
    @Timeout(5)
    void testSimpleProducerConsumer() throws Exception {
        final int total = 50;
        BoundedBlockingQueue q = new BoundedBlockingQueue(3);

        try (AutoCloseableExecutor ex = AutoCloseableExecutor.newFixedThreadPool(2)) {
            CopyOnWriteArrayList<Integer> consumed = new CopyOnWriteArrayList<>();

            Future<?> pf = ex.submit(() -> {
                for (int i = 0; i < total; i++) {
                    try {
                        q.enqueue(i);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            Future<?> cf = ex.submit(() -> {
                for (int i = 0; i < total; i++) {
                    try {
                        consumed.add(q.dequeue());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            // Wait for producer and consumer to finish (bounded timeout to avoid hangs)
            pf.get(3, TimeUnit.SECONDS);
            cf.get(3, TimeUnit.SECONDS);

            assertEquals(total, consumed.size(), "All produced items should be consumed");
            for (int i = 0; i < total; i++) {
                assertEquals(i, consumed.get(i), "FIFO order should be preserved");
            }
        }
    }
}
