package org.multithreading.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowRateLimiterOptimised {

    private final int maxRequests;
    private final long windowSizeInSec;

    private final AtomicInteger[] buckets;
    private volatile long lastResetTime;

    public SlidingWindowRateLimiterOptimised(int maxRequests, long windowSizeInSec) {
        this.maxRequests = maxRequests;
        this.windowSizeInSec = windowSizeInSec;
        buckets = new AtomicInteger[maxRequests];

        for(int i = 0; i < maxRequests; i++) {
            buckets[i] = new AtomicInteger(0);
        }

        lastResetTime = System.currentTimeMillis() / 1000;
    }

    public boolean allowRequest() {
        long now  = System.currentTimeMillis() / 1000;
        int index = (int) (now % maxRequests);

        if (now != lastResetTime) {
            buckets[index].set(0); // reset the bucket for the new time slot
            lastResetTime = now;
        }

        int totalRequests = 0;
        for (AtomicInteger bucket : buckets) {
            totalRequests += bucket.get();
        }

        if (totalRequests < maxRequests) {
            buckets[index].incrementAndGet();
            return true;
        }

        return false;
    }
}
