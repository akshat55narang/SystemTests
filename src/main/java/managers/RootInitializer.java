package managers;

import general.AbstractApi;
import initializedriver.DriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static managers.ConfigFileManager.*;

public class RootInitializer {
    private DriverProvider driverProvider;
    private PageManager pageManager;
    private RequestSpecification requestSpecification;

    private static final Logger logger = LogManager.getLogger(RootInitializer.class);

    static {
        RestAssured.baseURI = getDefaultApiUri();
        logger.info("Set Base URI to =" + RestAssured.baseURI);
    }

    public RootInitializer() {
        requestSpecification = getBaseRequest();
        logger.info("Cucumber Tags = " + System.getProperty("cucumber.options"));
        if (System.getProperty("cucumber.options").contains("@ui")) {
            WebDriverManager.chromedriver().setup();
            logger.info("Setting up ChromeDriver !!");
            driverProvider = new DriverProvider();
            logger.info("Initializing Page Manager !!");
            pageManager = new PageManager(driverProvider.getWebDriver());
            logger.info("Opening URL" + getDefaulftWebUrl());
            driverProvider.getWebDriver().get(getDefaulftWebUrl());
        }
    }

    public RequestSpecification getBaseRequest() {
        return (requestSpecification == null) ? new AbstractApi().baseRequestSpecification() : requestSpecification;
    }

    public DriverProvider getDriverProvider() {
        return driverProvider;
    }

    public PageManager getPageManager() {
        return pageManager;
    }

    public static String getParams(String variableName, String defaultValue) {
        String env = System.getenv(variableName);
        if (env != null) {
            variableName = defaultValue;
        }
        String result = System.getProperty(variableName, defaultValue);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }
}
