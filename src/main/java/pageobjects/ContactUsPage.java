package pageobjects;

import general.AbstractPage;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends AbstractPage {
    private WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public static void main(String []args) {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("test", "tes");
        requestSpecification.log().all();
        requestSpecification.get("https://reqres.in/api/users?page=2");
    }
}
