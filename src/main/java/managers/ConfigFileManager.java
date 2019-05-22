package managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ConfigFileManager {
    private static final Logger log = LogManager.getLogger(ConfigFileManager.class);
    public static final String PROPERTIES_PATH = "data.properties";
    public static final String DEFAULT_API_URI = "api";
    public static final String DEFAULT_WEB_URL = "url";
    public static final String DEFAULT_BROWSER = "browser";
    public static final String DEFAULT_TIMEOUT = "explicit_timeout";

    public static Properties loadProperties() {
        Properties properties = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTIES_PATH))) {
            properties = new Properties();
            properties.load(reader);
            log.info("Property File loaded successfully!! ");
        } catch (IOException e) {
            log.error("Not able to Load proeprty file !!");
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPropertyValueByName(String name) {
        return loadProperties().getProperty(name);
    }

}

