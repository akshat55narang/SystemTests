package pageobjects;

import general.AbstractPage;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }



}
