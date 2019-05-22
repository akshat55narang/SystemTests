package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import general.AbstractApi;
import managers.RootInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageobjects.WebLoginPage;

public class StepDefinitionWebLogin extends AbstractApi {
    private WebLoginPage webLoginPage;
    private RootInitializer rootInitializer;

    private static final Logger log = LogManager.getLogger(StepDefinitionWebLogin.class);

    public StepDefinitionWebLogin(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        webLoginPage = rootInitializer.getPageManager().getWebLoginPage();
    }

    @Given("User clicks on the button \"([^\"]*)\"")
    public void clickOnButton(String buttonText) {
        webLoginPage.clickSignInButton(buttonText);
    }

    @Then("user is redirected to view Sign In form")
    public void verifySignInAuthenticationPage() {

    }

    @When("the user enters a valid email in the create account section")
    public void enterValidEmail() {

    }

    @Then("the user should be redirected to account creation page")
    public void verifyRedirectionToAccountCreationPage() {

    }

}
