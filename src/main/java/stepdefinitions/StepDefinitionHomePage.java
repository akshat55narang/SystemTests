package stepdefinitions;

import cucumber.api.java.en.Given;
import managers.RootInitializer;
import pageobjects.HomePage;

import static general.CustomWaits.assertEventually;
import static org.junit.Assert.assertTrue;

public class StepDefinitionHomePage {
    private RootInitializer rootInitializer;
    private HomePage homePage;

    public StepDefinitionHomePage(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        homePage = rootInitializer.getPageManager().getHomePage();
    }

    @Given("I'm on the Home Page")
    public void onHomePage() {
        assertEventually(3, 1000, () -> {
            assertTrue("", homePage.getCurrentUrl().endsWith("index.php"));
        });

    }
}
