package com.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingService {
    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    public String generateGreeting(String name) {
        logger.debug("Generating greeting for: {}", name);
        if (name == null || name.isBlank()) {
            return "Xin chao, Khach!";
        }
        return "Xin chao, " + name + "!";
    }

    public int computeWorkload(int a, int b) {
        logger.debug("Computing workload for a={}, b={}", a, b);
        return (a * 2) + (b * 3);
    }
}
