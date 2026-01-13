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
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            handleModalOverlay();
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    protected void waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            handleModalOverlay();
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }

    protected void click(By locator) {
        try {
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            // Re-find the element and try again
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            // If a click is intercepted, handle the overlay and retry with a jsClick.
            handleModalOverlay();
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
        try {
            waitForElementToBeVisible(locator);
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            // If the element becomes stale, re-find it and attempt the click again.
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
    private void handleModalOverlay() {
        try {
            // Use a very short wait to quickly check for the modal overlay's presence
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By modalOverlayLocator = LocatorManager.getLocator("popups", "modalOverlay");
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlayLocator));

            // If the overlay is found, assume the close button is available and click it.
            // Using jsClick to prevent further interceptions.
            By closeButtonLocator = LocatorManager.getLocator("popups", "joinTheTrybePopupCloseButton");
            WebElement closeButton = driver.findElement(closeButtonLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);

        } catch (TimeoutException e) {
            // Overlay did not appear within the short timeout, which is the expected case.
            // Do nothing and let the test continue.
        } catch (Exception e) {
            // Catch any other exception during popup handling to ensure test stability.
            // We print the stack trace for debugging but don't re-throw, so the main test can proceed.
            e.printStackTrace();
        }
    }
}
