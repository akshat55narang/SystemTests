package general;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static managers.ConfigFileManager.DEFAULT_API_URI;
import static managers.ConfigFileManager.getPropertyValueByName;

public class AbstractApi {

    public RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    public RequestSender when() {
        return RestAssured.when();
    }

    public Response get(String uri) {
        return RestAssured.get(getPropertyValueByName(DEFAULT_API_URI) + uri);
    }

    public Response get(String uri, Map<String, String> params) {
        return RestAssured.get(uri, params);
    }
}
