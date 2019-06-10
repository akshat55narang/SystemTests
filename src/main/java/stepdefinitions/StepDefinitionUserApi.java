package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import general.AbstractApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import managers.RootInitializer;
import org.json.JSONObject;

import static managers.ConfigFileManager.*;
import static org.hamcrest.Matchers.*;

public class StepDefinitionUserApi extends AbstractApi {
    private RootInitializer rootInitializer;
    private Response response;

    public StepDefinitionUserApi(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
    }

    @Given("I call the users api with page size {string}")
    public void userApiPageSize(String pageSize) {
        response = baseRequestSpecification().queryParam("page", getPropertyValueByName(DEFAULT_API_PAGE_SIZE))
                .get(getPropertyValueByName(USERS_API));
    }

    @Then("I should receive a status code {string} and the response body should contain multiple user ids")
    public void validateMultipleUserResponse(String code) {
        response.then().assertThat().statusCode(Integer.parseInt(code));
        Response responseFinal = response.then().contentType(ContentType.JSON).extract().response();
        responseFinal.then().assertThat().body("data.id.size()", greaterThan(1));
    }

    @Given("I call the users api for a single user with id {string}")
    public void singeUserApi(String userId) {
        response = baseRequestSpecification().get(getPropertyValueByName(USERS_API) + "/" + userId);
        rootInitializer.setWorldResponse(response);
    }

    @Then("I should receive a status code {string} and the response body should contain single user id")
    public void validateSingleUserResponse(String code) {
        rootInitializer.getWorldResponse().then().assertThat().statusCode(Integer.parseInt(code)).
                assertThat().body("data.id", equalTo(Integer.parseInt("2")));
    }

    @Then("I should receive a status code {string} and the response body should be empty")
    public void validateResponseInvalidUser(String code) {
        rootInitializer.getWorldResponse().then().assertThat().statusCode(Integer.parseInt(code))
                .assertThat().body("size()", equalTo(0));
    }

    @Given("I call the users api to create user with name {string} and job {string}")
    public void createUserWithNameAndJob(String userName, String job) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", userName)
                .put("job", job);
        rootInitializer.setWorldResponse(baseRequestSpecification().body(jsonObject.toString())
                .post(getPropertyValueByName(USERS_API)));
    }

    @Then("I should receive a status code {string} and the response body contain user with name {string} and  job {string}")
    public void verifyUserNameAndJob(String code, String userName, String job) {
        Response extractResponse = rootInitializer.getWorldResponse().then().extract().response();
        extractResponse.then().assertThat().statusCode(Integer.parseInt(code));
        extractResponse.then().assertThat().body("name", equalTo(userName));
        extractResponse.then().assertThat().body("job", equalTo(job));
    }

    @Given("I call the users api to update user with name {string} and job {string} using {string} HTTP method")
    public void updateUserNameAndJob(String userName, String job, String httpMethod) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", userName)
                .put("job", job);
        if (httpMethod.equals("put")) {
            rootInitializer.setWorldResponse(baseRequestSpecification().body(jsonObject.toString())
                    .put(getPropertyValueByName(USERS_API) + "/2"));
        } else {
            rootInitializer.setWorldResponse(baseRequestSpecification().body(jsonObject.toString())
                    .patch(getPropertyValueByName(USERS_API) + "/2"));
        }
    }

    @Given("I call the users api to delete user with id {string}")
    public void deleteUser(String string) {
        rootInitializer.setWorldResponse(baseRequestSpecification()
                .delete(getPropertyValueByName(USERS_API) + "/2"));
    }

    @Then("I should receive a status code {string} and an empty response body")
    public void verifyUserDeletion(String code) {
        rootInitializer.getWorldResponse().then().assertThat().statusCode(Integer.parseInt(code))
                .assertThat().contentType(isEmptyOrNullString());

    }

}
