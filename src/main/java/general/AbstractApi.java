package general;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static managers.ConfigFileManager.DEFAULT_API_URI;
import static managers.ConfigFileManager.getPropertyValueByName;

public class AbstractApi {

    private static final Logger logger = LogManager.getLogger(AbstractApi.class);
    protected RequestSpecification givenWithLogs() {
        return RestAssured.given().log().all();
    }

    protected RequestSpecification given() {
        return RestAssured.given()
                .filter(ResponseLoggingFilter.logResponseIfStatusCodeIs(302));
    }

    protected RequestSpecification baseRequestSpecification() {
        return given().contentType("application/json");
    }

    protected RequestSender when() {
        return RestAssured.when();
    }

    protected Response get(String uri) {
        return RestAssured.get(getPropertyValueByName(DEFAULT_API_URI) + uri);
    }

    protected Response get(String uri, Map<String, String> params) {
        return RestAssured.get(uri, params);
    }


}
