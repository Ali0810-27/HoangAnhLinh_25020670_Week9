package com.caching;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DataProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessor.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(Map<String, Object> data) {
        logger.info("Serializing map to JSON payload");
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("JSON serialization failed: {}", e.getMessage(), e);
            throw new RuntimeException("Serialization error", e);
        }
    }

    public String capitalizeWords(String input) {
        logger.debug("Capitalizing words for input: {}", input);
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return StringUtils.capitalize(input);
    }
}
// Trigger CI run 2 for Maven cache hit verification
