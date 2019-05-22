package managers;

import initializedriver.DriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static managers.ConfigFileManager.DEFAULT_API_URI;
import static managers.ConfigFileManager.getPropertyValueByName;

public class RootInitializer {
    private DriverProvider driverProvider;
    private PageManager pageManager;
    private static final Logger log = LogManager.getLogger(RootInitializer.class);

    public RootInitializer() {
        RestAssured.baseURI = getPropertyValueByName(DEFAULT_API_URI);
        log.info("Set Base URI to =" + RestAssured.baseURI);
        WebDriverManager.chromedriver().setup();
        log.info("Setting up ChromeDriver !!");
        driverProvider = new DriverProvider();
        log.info("Initializing Driver !!");
        pageManager = new PageManager(driverProvider.getWebDriver());
    }

    public DriverProvider getDriverProvider() {
        return driverProvider;
    }

    public PageManager getPageManager() {
        return pageManager;
    }
}
