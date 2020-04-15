package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.RootInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageobjects.ContactUsPage;

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
       contactUsPage.verify();
    }

    @Given("User is on the contact page")
    public void onContactUsPage() {
        contactUsPage.open();
        contactUsPage.verify();
    }

    @When("user selects {string} as heading")
    public void selectSubjectHeading(String visibleText) {
        contactUsPage.selectMessageHeading(visibleText);
    }

    @When("user enters the email {string}")
    public void enterEmail(String email) {
        contactUsPage.enterEmailAddress(email);
    }

    @When("user enters the order reference {string}")
    public void enterOrderReference(String orderReference) {
        contactUsPage.enterOrderReference(orderReference);
    }

    @When("user attaches the file {string}")
    public void attachFile(String fileName) {
        contactUsPage.attachFileToContactForm(fileName);
    }

    @When("user enters the message {string}")
    public void enterMessage(String message) {
        contactUsPage.enterMessage(message);
    }

    @Then("the message should be sent successfully")
    public void verifySuccessMessageContactUsForm() {
        contactUsPage.verifySuccessMessage();
    }

}
