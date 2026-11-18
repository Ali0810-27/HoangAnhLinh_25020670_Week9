package com.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationServiceTest {
    private NotificationService service;

    @BeforeEach
    void setUp() {
        service = new NotificationService();
    }

    @Test
    void testSendEmailSuccess() {
        assertTrue(service.sendEmail("user@example.com", "Welcome", "Hello!"));
    }

    @Test
    void testSendEmailBlankSubject() {
        assertFalse(service.sendEmail("user@example.com", "   ", "Content"));
    }

    @Test
    void testSendEmailInvalidRecipient() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.sendEmail("invalid-email", "Test", "Content");
        });
    }
}
