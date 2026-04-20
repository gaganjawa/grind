package org.multithreading.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter {

    private final int capacity;
    private final int refillRate; // tokens per second

    private int tokens;
    private long lastRefillTimestamp;

    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity; // Start with a full bucket
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public boolean allowRequest() {

        lock.lock();
        try {
            refillTokens();
            if (tokens > 0) {
                tokens--;
                return true; // Request allowed
            }
            return false; // Request denied
        } finally {
            lock.unlock();
        }
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTimestamp;
        int tokensToAdd = (int) (elapsedTime * refillRate / 1000);

        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
