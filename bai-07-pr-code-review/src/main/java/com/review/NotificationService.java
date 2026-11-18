package com.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public boolean sendEmail(String recipient, String subject, String body) {
        logger.info("Sending notification email to: {}", recipient);
        if (recipient == null || !recipient.contains("@")) {
            logger.error("Invalid email address: {}", recipient);
            throw new IllegalArgumentException("Invalid email format");
        }
        if (subject == null || subject.isBlank()) {
            logger.warn("Email subject is blank");
            return false;
        }
        logger.info("Email dispatched successfully to {}", recipient);
        return true;
    }
}
