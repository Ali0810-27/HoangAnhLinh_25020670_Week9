package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathUtilsTest {
    private final MathUtils mathUtils = new MathUtils();

    @Test
    void testAdd() {
        assertEquals(15, mathUtils.add(10, 5));
    }

    @Test
    void testDivideSuccess() {
        assertEquals(5, mathUtils.divide(10, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.divide(10, 0));
    }
}
