package com.usermanged.usermanagement.config;

import com.usermanged.usermanagement.customannotation.RateLimited;
import com.usermanged.usermanagement.exception.RateLimitExceededException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitingAspect {

    private static final ConcurrentHashMap<String, Long> lastAccessTimes =
            new ConcurrentHashMap<String, Long>();


//    @Around("@annotation(rateLimited)")
//    public void beforeRequest(){
//        String clientId = ""; // Implement method to get client ID
//        AtomicInteger count = requestCount.computeIfAbsent(clientId, k -> new AtomicInteger(0));
//        if (count.incrementAndGet() > rateLimited) {
//            throw new RateLimitExceededException("Rate limit exceeded");
//        }
//        if (requestCount.size() == 1) {
//            resetRequestCounts();
//        }
//    }

    @Around("@annotation(rateLimited)")
    public Object applyRateLimit(ProceedingJoinPoint joinPoint,
                            RateLimited rateLimited) throws Throwable {
        // Rate Limiting Logic
        String methodName = joinPoint.getSignature().toLongString();
        long currentTime = System.currentTimeMillis();
        long lastAccessTime = lastAccessTimes.getOrDefault(methodName, 0L);
        long timeSinceLastAccess = currentTime - lastAccessTime;

        if (timeSinceLastAccess < rateLimited.timeWindow()) {
            throw new RateLimitExceededException("Rate limit exceeded");
        } else {
            lastAccessTimes.put(methodName, currentTime);
            return joinPoint.proceed();
        }
    }
}
