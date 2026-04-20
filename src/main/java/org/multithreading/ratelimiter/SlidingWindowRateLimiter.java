package org.multithreading.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowRateLimiter {

    private final int maxRequests;
    private final long windowSizeInMillis;

    Deque<Long> requestTimestamps = new ArrayDeque<>();

    ReentrantLock lock = new ReentrantLock();

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public boolean allowRequest() {
        long now  = System.currentTimeMillis();

        lock.lock();
        try {
            while(!requestTimestamps.isEmpty() && now - requestTimestamps.peekFirst() > windowSizeInMillis) {
                requestTimestamps.pollFirst();
            }

            if(requestTimestamps.size() < maxRequests) {
                requestTimestamps.addLast(now);
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }
}
