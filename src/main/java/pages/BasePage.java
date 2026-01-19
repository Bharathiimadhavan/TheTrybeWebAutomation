package pages;

import framework.LocatorManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
        handleJoinPopup();
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToBeVisible(By locator) {
        handleJoinPopup();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        handleJoinPopup();
        try {
            waitForElementToBeClickable(locator);
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (StaleElementReferenceException e) {
            // Re-find the element and try again
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (ElementClickInterceptedException e) {
            // If a click is intercepted, try a jsClick as a fallback.
            jsClick(locator);
        }
    }

    /**
     * Performs a click using JavaScript. This is a workaround for situations where a normal
     * click is intercepted by another element (ElementClickInterceptedException).
     *
     * @param locator The locator of the element to be clicked.
     */
    protected void jsClick(By locator) {
        handleJoinPopup();
        try {
            waitForElementToBeVisible(locator);
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            // If the element becomes stale, re-find it and attempt the click again.
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
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

    protected void enter(By locator) {
        try {
            waitForElementToBeVisible(locator);
            driver.findElement(locator).sendKeys(Keys.ENTER);
        } catch (StaleElementReferenceException e) {
            // Re-find the element and try again
            driver.findElement(locator).sendKeys(Keys.ENTER);
        }
    }

    protected void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Checks for a popup and closes it if it's present and clickable within a given timeout.
     *
     * @param locator The locator of the popup's close button.
     * @param timeoutInSeconds The maximum time to wait for the popup.
     * @return true if the popup was found and closed, false otherwise.
     */
    protected boolean closePopupIfPresent(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            return true;
        } catch (Exception e) {
            // Popup not found or not clickable within the timeout
            return false;
        }
    }
    private void handleJoinPopup() {
        try {
            // Use a very short wait to see if the popup's close button becomes clickable.
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Increased wait for reliability
            By closeButtonLocator = LocatorManager.getLocator("popups", "joinTheTrybePopupCloseButton");

            // Wait for the button to be clickable, which is the most reliable state.
            WebElement closeButton = shortWait.until(ExpectedConditions.elementToBeClickable(closeButtonLocator));
            
            // Use a resilient JavaScript click to bypass any potential overlays.
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);

        } catch (TimeoutException e) {
            // This is the normal, expected outcome when the popup does not appear.
            // Silently continue.
        } catch (Exception e) {
            // Catch any other unexpected exception to prevent the test from crashing.
            System.out.println("An unexpected error occurred while trying to close the 'Join The Trybe' popup: " + e.getMessage());
        }
    }
}
