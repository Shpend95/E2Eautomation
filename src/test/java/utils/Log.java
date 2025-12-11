package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    // Creates logger instance for this class
    private static final Logger logger = LogManager.getLogger(Log.class);

    // Info - general information
    public static void info(String message) {
        logger.info(message);
    }

    // Warn - potential problems
    public static void warn(String message) {
        logger.warn(message);
    }

    // Error - errors but app continues
    public static void error(String message) {
        logger.error(message);
    }

    // Fatal - critical errors, app crashes
    public static void fatal(String message) {
        logger.fatal(message);
    }

    // Debug - detailed debugging info
    public static void debug(String message) {
        logger.debug(message);
    }
}
