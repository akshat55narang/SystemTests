package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import general.AbstractApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import managers.RootInitializer;

import static managers.ConfigFileManager.RESOURCE_API;
import static managers.ConfigFileManager.getPropertyValueByName;
import static org.hamcrest.Matchers.*;

public class StepDefinitionResourceApi extends AbstractApi {
    private RootInitializer rootInitializer;
    private Response response;
    private RequestSpecification requestSpecification;

    public StepDefinitionResourceApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        requestSpecification = rootInitializer.getBaseRequest();
    }

    @When("I list all the resources")
    public void callRegisterApi() {
        response = requestSpecification.get(getPropertyValueByName(RESOURCE_API));
    }

    @Then("I should receive a status code {string} and multiple resource ids for resource api")
    public void verifyMultipleResources(String statusCode) {
        response.then().assertThat().statusCode(Integer.parseInt(statusCode)).and()
                .assertThat().statusCode(200).and().assertThat().body("total", greaterThan(1))
                .and().assertThat().body("data.size()", greaterThan(1));
    }

    @Given("I list the resource with id {string}")
    public void callRegisterApiWithSignleId(String resourceId) {
        response = requestSpecification.get(getPropertyValueByName(RESOURCE_API) + "/" + resourceId);
    }

    @Then("I should receive a status code {string} and a single resource id for resource api")
    public void verifySingleResource(String statusCode) {
        response.then().assertThat().statusCode(Integer.parseInt(statusCode)).and()
                .assertThat().statusCode(200).and()
                .assertThat().body("data.id", equalTo(2));
    }

    @And("I should receive a status code {string} and no body for resource api")
    public void verifyEmptyResponseResourceIds(String statusCode) {
        response.then().assertThat().statusCode(Integer.parseInt(statusCode))
        .and().assertThat().body("isEmpty()", is(true));
    }

}
