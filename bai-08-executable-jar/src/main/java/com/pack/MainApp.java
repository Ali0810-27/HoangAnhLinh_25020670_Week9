package com.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        logger.info("==================================================");
        logger.info("  KHOI CHAY UNG DUNG THUC THI INDEPENDENT JAR");
        logger.info("  Phien ban: 1.0.0 | Runtime: Java {}", System.getProperty("java.version"));
        logger.info("==================================================");

        GreetingService service = new GreetingService();
        String message = service.generateGreeting("Sinh vien UET");
        logger.info("Thong diep: {}", message);

        int total = service.computeWorkload(10, 20);
        logger.info("Ket qua tinh toan doc lap tren Terminal: {}", total);

        logger.info("Ung dung da hoan tat xu ly va thoat an toan.");
    }
}
