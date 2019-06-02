package pageobjects;

import general.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static managers.ConfigFileManager.DEFAULT_WEB_URL;
import static managers.ConfigFileManager.getPropertyValueByName;
import static org.junit.Assert.assertTrue;

public class ContactUsPage extends AbstractPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

    public ContactUsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verify() {
        driver.get(getPropertyValueByName(DEFAULT_WEB_URL) + "?controller=contact");
        logger.info("Opening Contact Us Page !!!!");
        assertTrue("Current Url is not Contact Us", getCurrentUrl().endsWith("controller=contact"));
    }

    public void selectMessageHeading(String visibleText) {
        WebElement element = findElement(By.id("id_contact"));
        selectByVisibleText(element, visibleText);
    }
}
