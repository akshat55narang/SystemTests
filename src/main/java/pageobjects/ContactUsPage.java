package pageobjects;

import general.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static general.CustomWaits.assertEventually;
import static managers.ConfigFileManager.*;
import static org.junit.Assert.assertTrue;

public class ContactUsPage extends AbstractPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

    public ContactUsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void open() {
        driver.get(getDefaulftWebUrl() + "?controller=contact");
        logger.info("Opening Contact Us Page !!!!");
    }

    public void verify() {
        assertEventually(3, 500, () -> {
            assertTrue("Contact Us page is not open", getCurrentUrl().endsWith("controller=contact"));
        });
    }

    public void selectMessageHeading(String visibleText) {
        WebElement element = findElement(By.id("id_contact"));
        selectByVisibleText(element, visibleText);
    }

    public void enterEmailAddress(String email) {
        sendTextToFieldBy(By.xpath("//input[@id='email']"), email);
    }

    public void enterOrderReference(String orderReference) {
        sendTextToFieldBy(By.xpath("//input[@id='id_order']"), orderReference);
    }

    public void attachFileToContactForm(String fileName) {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/test.png");
        String absolutePath = file.getAbsolutePath();
        uploadFileFromLocalDrive(absolutePath);
    }

    public void enterMessage(String message) {
        sendTextToFieldBy(By.cssSelector("#message"), message);
    }

    public void verifySuccessMessage() {
        waitForElement(5, By.xpath("//p[text()='Your message has been successfully sent to our team.']"));
    }
}
