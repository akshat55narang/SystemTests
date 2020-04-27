package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import general.AbstractApi;
import io.restassured.response.Response;
import managers.RootInitializer;
import org.json.JSONObject;

import static managers.ConfigFileManager.REGISTER_API;
import static managers.ConfigFileManager.getPropertyValueByName;
import static org.hamcrest.Matchers.*;

public class StepDefinitionRegisterApi extends AbstractApi {
    private RootInitializer rootInitializer;
    private ThreadLocal<Response> response = new ThreadLocal<>();

    public StepDefinitionRegisterApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
    }

    @Given("I register a user with email {string} and password {string}")
    public void successfulRegistration(String email, String password) {
        JSONObject jsonObject = baseRegistrationLoginApiObject(email).put("password", password);
        response.set(baseRequestSpecification().body(jsonObject.toString())
                .post(getPropertyValueByName(REGISTER_API)));
    }

    @Given("I register a user with email {string}")
    public void unsuccessfulRegistration(String email) {
        response.set(baseRequestSpecification().body(baseRegistrationLoginApiObject(email).toString())
                .post(getPropertyValueByName(REGISTER_API)));
    }

    @Then("I should receive a status code {string} and a user id for register api")
    public void verifySuccessfulRegistration(String statusCode) {
        response.get().then().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body("id", notNullValue());
    }

    @Then("I should receive a status code {string} and error with {string} for register api")
    public void verifyUnsuccessfulRegistration(String statusCode, String error) {
        response.get().then().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body("error", equalTo(error));
    }
}
