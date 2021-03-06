package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Feature"
        , glue = "stepdefinitions"
        , monochrome = true
        , plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class TestRunner {

    @BeforeClass
    public static void setup() {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(System.getProperty("user.dir")
                + "/test.log", false), false)) {
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void writeExtentReport() {

    }

}
