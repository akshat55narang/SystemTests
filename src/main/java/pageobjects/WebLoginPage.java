package pageobjects;

import general.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebLoginPage extends AbstractPage {
    private WebDriver driver;

    public WebLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignInButton(String buttonText) {
        clickButtonWithText(buttonText);
    }

    public boolean isAuthenticationPageLoaded() {
        return waituntilElementAppears(By.xpath("//h1[text()='Authentication']")).isDisplayed();
    }

    public void enterEmailInAccountCreation(String email) {
        sendTextToFieldBy(By.id("email_create"), email);
    }
}
