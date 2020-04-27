package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import general.AbstractApi;
import io.restassured.response.Response;
import managers.RootInitializer;
import org.json.JSONObject;

import static managers.ConfigFileManager.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepDefinitionLoginApi extends AbstractApi {
    private RootInitializer rootInitializer;

    private ThreadLocal<Response> response = new ThreadLocal<>();

    public StepDefinitionLoginApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
    }

    @Given("I login a user with email {string} and password {string}")
    public void successfulRegistration(String email, String password) {
        JSONObject jsonObject = baseRegistrationLoginApiObject(email).put("password", password);
        response.set(baseRequestSpecification().body(jsonObject.toString())
                .post(getPropertyValueByName(LOGIN_API)));
    }

    @Given("I login a user with email {string} and no password")
    public void unsuccessfulRegistration(String email) {
        response.set(baseRequestSpecification().body(baseRegistrationLoginApiObject(email).toString())
                .post(getPropertyValueByName(LOGIN_API)));
    }

    @Then("I should receive a status code {string} and a user id for login api")
    public void verifySuccessfulRegistration(String statusCode) {
        response.get().then().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body("token", notNullValue());
    }

    @Then("I should receive a status code {string} and error with {string} for login api")
    public void verifyUnsuccessfulRegistration(String statusCode, String error) {
        response.get().then().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body("error", equalTo(error));
    }
}
