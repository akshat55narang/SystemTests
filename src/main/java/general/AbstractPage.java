package general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static managers.ConfigFileManager.*;

public class AbstractPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected FluentWait<WebDriver> newWait() {
        return new WebDriverWait(driver, Integer.parseInt(getDefaultTimeout()))
                .ignoring(StaleElementReferenceException.class);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected WebElement findElement(By by) {
        return driver.findElement(by);
    }

    protected WebElement waitForElement(By by) {
        return newWait().until(ExpectedConditions.visibilityOfElementLocated(by));
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

    protected WebElement waitForElementToBeClickable(By by) {
        return newWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void clickButtonWithText(String text) {
        waitForElementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]")).click();
    }

    protected WebElement waituntilElementAppears(By by) {
        return newWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void sendTextToFieldBy(By by, String inputText) {
        WebElement element = waitForElementToBeClickable(by);
        element.clear();
        element.click();
        element.sendKeys(inputText);
    }

    protected void moveToAnElement(WebElement element) {
        new Actions(driver).moveToElement(element);
    }

    protected void moveToAnElement(Actions actions, WebElement element) {
        actions.moveToElement(element).perform();
    }

    protected void moveToMultipleElementsAndPerformAction(WebElement... elements) {
        moveToMultipleElementsAndPerformAction(actions -> actions.click().build().perform(), elements);
    }

    protected void moveToMultipleElementsAndPerformAction(Consumer<Actions> actions, WebElement... elements) {
        Actions action = new Actions(driver);
        Arrays.asList(elements).forEach(
                element -> {
                    moveToAnElement(action, element);
                });
        actions.accept(action);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByVisibleText(WebElement element, String visibleText) {
        new Select(element).selectByVisibleText(visibleText);
        waitForElement(3, By.xpath("//span[text()='" + visibleText + "']"));
    }

    protected void uploadFileFromLocalDrive(String path) {
        WebElement element = findElement(By.xpath("//input[@id='fileUpload']"));
        element.sendKeys(path);
    }

}
