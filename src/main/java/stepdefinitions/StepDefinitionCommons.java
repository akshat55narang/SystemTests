package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.RootInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static managers.ConfigFileManager.*;

public class StepDefinitionCommons {
    private RootInitializer rootInitializer;
    private WebDriver driver;

    private static final Logger log = LogManager.getLogger(StepDefinitionCommons.class);

    public StepDefinitionCommons(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("*******  Before Scenario ********");
        log.info("Executing Scenario - " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info("****** Executing After Scenario *******");
        if (!getDefaultTestType().contains("API")) {
            driver = rootInitializer.getDriverProvider().getWebDriver();
            if (driver != null) {
                driver.quit();
                log.info("Closed all Webdriver Instances !!!");

            }
        }
        log.info("Scenario " + scenario.getName() + " Complete with Status " + scenario.getStatus());
    }


}
