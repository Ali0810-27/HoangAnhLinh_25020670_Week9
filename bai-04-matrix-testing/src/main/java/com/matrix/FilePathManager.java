package com.matrix;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePathManager {
    private static final Logger logger = LoggerFactory.getLogger(FilePathManager.class);

    public String buildHardcodedWindowsPath(String folder, String filename) {
        logger.warn("Warning: using hardcoded Windows separator backslash");
        return folder + "\\" + filename;
    }

    public String buildHardcodedUnixPath(String folder, String filename) {
        logger.warn("Warning: using hardcoded Unix separator slash");
        return folder + "/" + filename;
    }

    public String buildCrossPlatformPath(String folder, String filename) {
        Path path = Paths.get(folder, filename);
        String result = path.toString();
        logger.info("Generated cross-platform path: {}", result);
        return result;
    }

    public String buildPathWithFileSeparator(String folder, String filename) {
        String result = folder + File.separator + filename;
        logger.info("Generated path using File.separator: {}", result);
        return result;
    }
}
