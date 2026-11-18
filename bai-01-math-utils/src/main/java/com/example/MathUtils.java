package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtils {
    private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);

    public int add(int a, int b) {
        int result = a + b;
        logger.info("Phep cong: a={}, b={}, result={}", a, b, result);
        return result;
    }

    public int divide(int a, int b) {
        logger.info("Thuc hien phep chia: a={}, b={}", a, b);
        if (b == 0) {
            logger.error("Loi chia cho 0 voi tu so a={}", a);
            throw new IllegalArgumentException("So chia khong duoc bang 0");
        }
        return a / b;
    }
}
