package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String read(String key, String path) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException fe) {
            fe.printStackTrace();
        }

        return properties.getProperty(key);
    }

    /**
     * Reads configuration value
     * Priority: Maven system property > config.properties file
     */
    public static String read(String key) {
        // First check Maven system properties (passed from Jenkins)
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isEmpty()) {
            return systemValue;
        }

        // Fallback to config.properties file
        return read(key, Constants.CONFIG_FILE_PATH);

    }


}
