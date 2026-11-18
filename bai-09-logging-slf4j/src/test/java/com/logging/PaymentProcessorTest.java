package com.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentProcessorTest {
    private PaymentProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PaymentProcessor();
    }

    @Test
    void testProcessPaymentSuccess() {
        boolean success = processor.processPayment("TX-9901", "CUST-100", 250000.0, "VND");
        assertTrue(success);
    }

    @Test
    void testProcessPaymentHighValueWarn() {
        boolean success = processor.processPayment("TX-9902", "CUST-200", 75000000.0, "VND");
        assertTrue(success);
    }

    @Test
    void testProcessPaymentInvalidAmountError() {
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processPayment("TX-9903", "CUST-300", -5000.0, "VND");
        });
    }

    @Test
    void testProcessPaymentInvalidCustomerIdError() {
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processPayment("TX-9904", "", 100000.0, "VND");
        });
    }
}
