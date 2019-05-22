package general;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static managers.ConfigFileManager.DEFAULT_TIMEOUT;
import static managers.ConfigFileManager.getPropertyValueByName;

public class AbstractPage extends FluentWait {
    private WebDriver driver;

    public AbstractPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected FluentWait<WebDriver> newWait() {
        return new WebDriverWait(driver, Integer.parseInt(getPropertyValueByName(DEFAULT_TIMEOUT)))
                .ignoring(StaleElementReferenceException.class);
    }

    protected WebElement findElement(By by) {
        return driver.findElement(by);
    }

    protected WebElement waitForElement(By by) {
        return newWait().until(ExpectedConditions.visibilityOf(findElement(by)));
    }

    protected WebElement waitForElement(int timeout, By by) {
        return newWait().withTimeout(Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    protected List<WebElement> waitForElements(int timetoutInSeconds, By by) {
        return newWait().withTimeout(Duration.ofSeconds(timetoutInSeconds)).until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(by));
    }

    protected void waitForElementToBeClickable(int timeOutInSeconds, By by) {
        newWait().withTimeout(Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitForElementToBeClickable(By by) {
        newWait().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void clickButtonWithText(String text) {
        waitForElementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    protected void waituntilElementAppears(By by) {
        newWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


}
