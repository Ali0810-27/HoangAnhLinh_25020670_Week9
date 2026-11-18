package com.matrix;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilePathManagerTest {
    private FilePathManager manager;

    @BeforeEach
    void setUp() {
        manager = new FilePathManager();
    }

    @Test
    @DisplayName("Kiem tra duong dan Cross-Platform voi java.nio.file.Path hoat dong tren moi he dieu hanh")
    void testCrossPlatformPath() {
        String expected = Paths.get("data", "reports", "annual_2026.pdf").toString();
        String actual = manager.buildCrossPlatformPath("data" + File.separator + "reports", "annual_2026.pdf");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Kiem tra duong dan su dung File.separator tuong thich dong voi OS dang chay")
    void testBuildPathWithFileSeparator() {
        String folder = "var";
        String file = "log.txt";
        String expected = folder + File.separator + file;
        String actual = manager.buildPathWithFileSeparator(folder, file);
        assertEquals(expected, actual);
        assertTrue(actual.contains(File.separator));
    }
}
