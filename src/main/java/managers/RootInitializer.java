package managers;

import general.AbstractApi;
import initializedriver.DriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static managers.ConfigFileManager.getDefaulftWebUrl;
import static managers.ConfigFileManager.getDefaultApiUri;

public class RootInitializer {
    private PageManager pageManager;
    private RequestSpecification requestSpecification;

    private static ThreadLocal<DriverProvider> driverProvider = new ThreadLocal<>();
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
            driverProvider.set(new DriverProvider());
            logger.info("Initializing Page Manager !!");
            pageManager = new PageManager(driverProvider.get().getWebDriver());
            logger.info("Opening URL" + getDefaulftWebUrl());
            driverProvider.get().getWebDriver().get(getDefaulftWebUrl());
        }
    }

    public RequestSpecification getBaseRequest() {
        return (requestSpecification == null) ? new AbstractApi().baseRequestSpecification() : requestSpecification;
    }

    public DriverProvider getDriverProvider() {
        return driverProvider.get();
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
