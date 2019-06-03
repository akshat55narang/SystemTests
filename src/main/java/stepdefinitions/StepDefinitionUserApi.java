package stepdefinitions;

import com.mongodb.util.JSON;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import general.AbstractApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

import java.util.List;

import static managers.ConfigFileManager.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class StepDefinitionUserApi extends AbstractApi {
    private RestAssured restAssured;
    private Response response;

    public StepDefinitionUserApi() {

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
        List<String> ids = responseFinal.jsonPath().getList("data.id");
        assertTrue("Multiple Ids are not returned !!", ids.size() > 1);
    }

    @Given("I call the users api for a single user")
    public void singeUserApi() {
        response = baseRequestSpecification().get(getPropertyValueByName(USERS_API) + "2");
    }

    @Then("I should receive a status code {string} and the response body should contain single user id")
    public void validateSingleUserResponse(String code) {
        response.then().assertThat().statusCode(Integer.parseInt(code)).
                assertThat().body("data.id", equalTo(Integer.parseInt("2")));
    }

}
