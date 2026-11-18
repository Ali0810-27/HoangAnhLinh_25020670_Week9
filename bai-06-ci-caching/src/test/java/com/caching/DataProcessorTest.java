package com.caching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataProcessorTest {
    private DataProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new DataProcessor();
    }

    @Test
    void testToJson() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "SUCCESS");
        data.put("code", 200);

        String json = processor.toJson(data);
        assertTrue(json.contains("\"status\":\"SUCCESS\""));
        assertTrue(json.contains("\"code\":200"));
    }

    @Test
    void testCapitalizeWords() {
        assertEquals("Hello", processor.capitalizeWords("hello"));
        assertEquals("", processor.capitalizeWords(""));
    }
}
