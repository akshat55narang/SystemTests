package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import general.AbstractApi;
import io.restassured.response.Response;
import managers.RootInitializer;

import static org.hamcrest.Matchers.greaterThan;

import static managers.ConfigFileManager.REGISTER_API;
import static managers.ConfigFileManager.getPropertyValueByName;

public class StepDefinitionResourceApi extends AbstractApi {
    private RootInitializer rootInitializer;
    private Response response;

    public StepDefinitionResourceApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
    }

    @Given("I call the register api")
    public void callRegisterApi() {
        response = baseRequestSpecification().get(getPropertyValueByName(REGISTER_API));
    }

    @Then("I should receive a status code {string} and the response body should contain multiple resource ids")
    public void verifyResponseMultipleResourceIds(String string) {
        response.then().assertThat().statusCode(200)
                .assertThat().body("data.id.size()", greaterThan(1));
    }

    @Given("I call the register api for a single id with id {string}")
    public void callRegisterApiWithId(String resourceId) {
        response = baseRequestSpecification().get(getPropertyValueByName(REGISTER_API) + "/" + resourceId);
        rootInitializer.setWorldResponse(response);
    }

}
