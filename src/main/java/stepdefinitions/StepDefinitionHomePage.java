package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

    @When("user hovers on {string} tab and click on {string}")
    public void hoverOnTabAndPerformClickButton(String string, String string2) {
        
        
    }

    @Then("user should be on the {string} page")
    public void user_should_be_on_the_page(String string) {
        
        
    }

    @When("user adds item {string} to the cart")
    public void user_adds_item_to_the_cart(String string) {
        
        
    }

    @Then("a popup with successful addition of products {string} to cart should appear")
    public void a_popup_with_successful_addition_of_products_to_cart_should_appear(String string) {
        
        
    }

    @When("user click on the button {string}")
    public void user_click_on_the_button(String string) {
        
        
    }

    @Then("the quantity of item {string} should be {string}")
    public void the_quantity_of_item_should_be(String string, String string2) {
        
        
    }

    @When("user clicks on the button {string}")
    public void user_clicks_on_the_button(String string) {
        
        
    }

    @When("the user enters {string} as {string} in personal information")
    public void the_user_enters_as_in_personal_information(String string, String string2) {
        
        
    }

    @When("the user enters {string} as {string} in address")
    public void the_user_enters_as_in_address(String string, String string2) {
        
        
    }

    @When("the user {string} as {string} in address")
    public void the_user_as_in_address(String string, String string2) {
        
    }

}
