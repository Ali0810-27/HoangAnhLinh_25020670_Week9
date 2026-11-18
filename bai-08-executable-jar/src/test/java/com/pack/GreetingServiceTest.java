package com.pack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingServiceTest {
    private GreetingService service;

    @BeforeEach
    void setUp() {
        service = new GreetingService();
    }

    @Test
    void testGreetingWithName() {
        assertEquals("Xin chao, Linh!", service.generateGreeting("Linh"));
    }

    @Test
    void testGreetingWithNullOrBlank() {
        assertEquals("Xin chao, Khach!", service.generateGreeting(null));
        assertEquals("Xin chao, Khach!", service.generateGreeting("   "));
    }

    @Test
    void testComputeWorkload() {
        assertEquals(80, service.computeWorkload(10, 20));
    }
}
