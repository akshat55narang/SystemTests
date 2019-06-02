package managers;

import org.openqa.selenium.WebDriver;
import pageobjects.ContactUsPage;
import pageobjects.HomePage;
import pageobjects.WebLoginPage;

public class PageManager {
    private WebDriver driver;
    private WebLoginPage webLoginPage;
    private ContactUsPage contactUsPage;
    private HomePage homePage;

    private long patientId;
    private boolean insured;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public WebLoginPage getWebLoginPage() {
        return (webLoginPage == null) ? webLoginPage = new WebLoginPage(driver) : webLoginPage;
    }

    public ContactUsPage getContactUsPage() {
        return (contactUsPage == null) ? contactUsPage = new ContactUsPage(driver) : contactUsPage;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }
}
