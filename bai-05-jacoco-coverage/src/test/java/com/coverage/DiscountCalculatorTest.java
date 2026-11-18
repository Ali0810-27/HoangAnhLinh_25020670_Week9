package com.coverage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountCalculatorTest {
    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    @Test
    void testVipHighAmount() {
        assertEquals(240.0, calculator.calculateDiscount(1200.0, "VIP"), 0.001);
    }

    @Test
    void testVipStandardAmount() {
        assertEquals(120.0, calculator.calculateDiscount(800.0, "VIP"), 0.001);
    }

    @Test
    void testGoldHighAmount() {
        assertEquals(60.0, calculator.calculateDiscount(600.0, "GOLD"), 0.001);
    }

    @Test
    void testGoldStandardAmount() {
        assertEquals(32.0, calculator.calculateDiscount(400.0, "GOLD"), 0.001);
    }

    @Test
    void testSilverTier() {
        assertEquals(10.0, calculator.calculateDiscount(200.0, "SILVER"), 0.001);
    }

    @Test
    void testRegularTier() {
        assertEquals(0.0, calculator.calculateDiscount(500.0, "REGULAR"), 0.001);
    }

    @Test
    void testNegativeAmountThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(-100.0, "VIP");
        });
    }

    @Test
    void testNullTierThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(100.0, null);
        });
    }

    @Test
    void testUnknownTierThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateDiscount(100.0, "PLATINUM");
        });
    }
}
