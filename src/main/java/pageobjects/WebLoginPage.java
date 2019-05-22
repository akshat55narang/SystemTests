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
        waituntilElementAppears(By.xpath("//h1[text()='Authentication']"));
    }


}
