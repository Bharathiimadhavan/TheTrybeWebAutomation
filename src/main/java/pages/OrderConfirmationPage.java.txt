package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmationPage extends BasePage {

    private By successMessage = By.cssSelector(".order-confirmation, .woocommerce-thankyou-order-received");
    private By orderNumber = By.cssSelector(".order-number, .woocommerce-order-overview__order strong");

    public boolean isOrderSuccessMessageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElements(successMessage).size() > 0;
    }

    public String getOrderNumber() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
        return driver.findElement(orderNumber).getText().trim();
    }
}
