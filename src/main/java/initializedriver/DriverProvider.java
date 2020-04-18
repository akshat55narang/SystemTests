package initializedriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static managers.ConfigFileManager.*;

public class DriverProvider {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverProvider.class);

    public WebDriver getWebDriver() {
        return (driver == null) ? driver = createWebDriver() : driver;
    }

    private WebDriver createWebDriver() {
        switch (getDefaultBrowser()) {
            case "chrome":
                driver = new ChromeDriver();
                logger.info("Starting Chrome !!!");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Starting Firefox !!!");
                break;
        }
        return driver;
    }


}
