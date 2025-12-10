package pages;

import framework.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = BaseDriver.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        try {
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            // Re-find the element and try again
            driver.findElement(locator).click();
        }
    }

    protected void sendKeys(By locator, String text) {
        try {
            waitForElementToBeVisible(locator);
            driver.findElement(locator).sendKeys(text);
        } catch (StaleElementReferenceException e) {
            // Re-find the element and try again
            driver.findElement(locator).sendKeys(text);
        }
    }

    protected void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
