package managers;

import initializedriver.DriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static managers.ConfigFileManager.*;

public class RootInitializer {
    private DriverProvider driverProvider;
    private PageManager pageManager;
    private Response worldResponse;

    private static final Logger logger = LogManager.getLogger(RootInitializer.class);

    public RootInitializer() {
        RestAssured.baseURI = getPropertyValueByName(DEFAULT_API_URI);
        logger.info("Set Base URI to =" + RestAssured.baseURI);
        WebDriverManager.chromedriver().setup();
        logger.info("Setting up ChromeDriver !!");
        driverProvider = new DriverProvider();
        if (getPropertyValueByName(TEST_TYPE).equals("UI")) {
            logger.info("Initializing Page Manager !!");
            pageManager = new PageManager(driverProvider.getWebDriver());
            logger.info("Opening URL" + getPropertyValueByName(DEFAULT_WEB_URL));
            driverProvider.getWebDriver().get(getPropertyValueByName(DEFAULT_WEB_URL));
        }
        worldResponse = new RestAssuredResponseImpl();
    }

    public Response getWorldResponse() {
        return worldResponse;
    }

    public void setWorldResponse(Response worldResponse) {
        this.worldResponse = worldResponse;
    }

    public DriverProvider getDriverProvider() {
        return driverProvider;
    }

    public PageManager getPageManager() {
        return pageManager;
    }
}
