package org.multithreading.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    private final ConcurrentHashMap<String, TokenBucketRateLimiter> userRateLimiters = new ConcurrentHashMap<>();

    public boolean allow(String userId) {
      TokenBucketRateLimiter limiter = userRateLimiters
              .computeIfAbsent(userId, id -> new TokenBucketRateLimiter(5, 1));

      return limiter.allowRequest();

    }
}
