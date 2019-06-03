package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.RootInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageobjects.ContactUsPage;

import static org.junit.Assert.assertTrue;

public class StepDefinitionContactUsPage {
    private RootInitializer rootInitializer;
    private ContactUsPage contactUsPage;

    private static final Logger log = LogManager.getLogger(StepDefinitionContactUsPage.class);

    public StepDefinitionContactUsPage(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        contactUsPage = rootInitializer.getPageManager().getContactUsPage();
    }

    @Then("the user should be redirected to Contact us page")
    public void verifyContactUsPage() {
        assertTrue("Not on Contact us page!!", contactUsPage.getCurrentUrl().endsWith("controller=contact"));
    }

    @Given("User is on the contact page")
    public void onContactUsPage() {
        contactUsPage.verify();
    }

    @When("user selects {string} as heading")
    public void selectSubjectHeading(String visibleText) {
        contactUsPage.selectMessageHeading(visibleText);
    }

    @When("user enters the {string}")
    public void user_enters_the(String string) {


    }

    @When("user attaches the file {string}")
    public void user_attaches_the_file(String string) {


    }

    @When("user enters the default message")
    public void user_enters_the_default_message() {


    }

    @Then("the message should be sent successfully")
    public void the_message_should_be_sent_successfully() {


    }

}
