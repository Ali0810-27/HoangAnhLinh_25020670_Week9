package com.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderProcessingServiceTest {
    private OrderProcessingService service;

    @BeforeEach
    void setUp() {
        service = new OrderProcessingService();
    }

    @Test
    void testCalculateTotalStandard() {
        // subtotal 100, discount 10% -> 90. tax 10% on 90 -> 9. total = 99.0
        double total = service.calculateTotal(100.0, 10.0, 10.0);
        assertEquals(99.0, total, 0.001);
    }

    @Test
    void testCalculateTotalNoDiscountNoTax() {
        double total = service.calculateTotal(250.0, 0.0, 0.0);
        assertEquals(250.0, total, 0.001);
    }

    @Test
    void testCalculateTotalInvalidSubtotal() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(-50.0, 10.0, 10.0);
        });
    }

    @Test
    void testCalculateTotalInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(100.0, 150.0, 10.0);
        });
    }
}
