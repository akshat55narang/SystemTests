package general;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static managers.ConfigFileManager.DEFAULT_TIMEOUT;
import static managers.ConfigFileManager.getPropertyValueByName;

public class AbstractPage {
    private WebDriver driver;

    public AbstractPage(WebDriver driver) {
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

    protected WebElement waitForElementToBeClickable(By by) {
        return newWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickButtonWithText(String text) {
        waitForElementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]")).click();
    }

    protected WebElement waituntilElementAppears(By by) {
        return newWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void sendTextToFieldBy(By by, String email) {
        WebElement element = waitForElementToBeClickable(by);
        element.click();
        element.sendKeys(email);
        element.getAttribute("value").equals(email);
    }

    public void moveToAnElement(WebElement element) {
        new Actions(driver).moveToElement(element);
    }

    public void moveToAnElement(Actions actions, WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void moveToMultipleElementsAndPerformAction(WebElement ...elements) {
        moveToMultipleElementsAndPerformAction(actions -> actions.click().build().perform(), elements);
    }

    public void moveToMultipleElementsAndPerformAction(Consumer<Actions> actions, WebElement ...elements) {
        Actions action = new Actions(driver);
        Arrays.asList(elements).forEach(
                element -> {
                    moveToAnElement(action, element);
                });
        actions.accept(action);
    }

}
