package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import general.AbstractApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import managers.RootInitializer;
import org.hamcrest.Matchers;

import static managers.ConfigFileManager.REGISTER_API;
import static managers.ConfigFileManager.getPropertyValueByName;
import static org.hamcrest.Matchers.greaterThan;

public class StepDefinitionResourceApi extends AbstractApi {
    private RootInitializer rootInitializer;
    private Response response;
    private RequestSpecification requestSpecification;

    public StepDefinitionResourceApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        requestSpecification = rootInitializer.getBaseRequest();
    }

    @When("I call the register api")
    public void callRegisterApi() {
        response = requestSpecification.get(getPropertyValueByName(REGISTER_API));
    }

    @And("the response body should contain multiple resource ids")
    public void verifyResponseMultipleResourceIds() {
        response.then().log().all();
        response.then().assertThat().statusCode(200)
                .assertThat().body("data.id.size()", greaterThan(1));
    }

    @Given("I call the register api for a single id with id {string}")
    public void callRegisterApiWithSignleId(String resourceId) {
        response = requestSpecification.get(getPropertyValueByName(REGISTER_API) + "/" + resourceId);

    }

    @Then("I should receive a status code {string} for register api")
    public void verifyStatusCodeRegisterApi(String statusCode) {
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
    }

    @And("the response body should not contain any resource ids")
    public void verifyEmptyResponseResourceIds() {
        response.then().assertThat().body("isEmpty()", Matchers.is(true));
    }

    @And("the response body should contain single resource id")
    public void verifyResponseSingleResourceId() {
        response.then().extract().jsonPath().getString("data.id").equals(2);
    }
}
