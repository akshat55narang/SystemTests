package managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

import static managers.RootInitializer.getParams;

public class ConfigFileManager {
    private static final Logger log = LogManager.getLogger(ConfigFileManager.class);
    public static final String PROPERTIES_PATH = "data.properties";
    public static final String DEFAULT_API_URI = "api";
    public static final String DEFAULT_WEB_URL = "url";
    public static final String DEFAULT_BROWSER = "browser";
    public static final String DEFAULT_TIMEOUT = "explicit_timeout";
    public static final String DEFAULT_EMAIL_ADDRESS = "default_email_address";
    public static final String USERS_API = "users.api";
    public static final String REGISTER_API = "register.api";
    public static final String DEFAULT_API_PAGE_SIZE = "default.pagesize";
    public static final String DEFAULT_TEST_TYPE = "test.type";
    public static final String DEFAULT_CUCUMBER_TAGS = "default.cucumber.tags";

    public static Properties loadProperties() {
        Properties properties = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTIES_PATH))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            log.error("Not able to Load property file !!");
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPropertyValueByName(String name) {
        return loadProperties().getProperty(name);
    }

    public static String getDefaultTestType() {
        return getParams("testType", getPropertyValueByName(DEFAULT_TEST_TYPE));
    }

}

