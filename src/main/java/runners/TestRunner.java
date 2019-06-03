package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Feature"
        , glue = "stepdefinitions"
        , tags = "@api"
        , monochrome = true
        //, plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
        //, dryRun = true
)

public class TestRunner {

    @BeforeClass
    public static void setup() {

    }

    @AfterClass
    public static void writeExtentReport() {
    }

}
