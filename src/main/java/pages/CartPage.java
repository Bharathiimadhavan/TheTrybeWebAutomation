package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.LocatorManager;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProductNames() {
        waitForElementToBeVisible(LocatorManager.getLocator("cart", "productNameInCart"));
        return driver.findElements(LocatorManager.getLocator("cart", "productNameInCart"));
    }

    public List<WebElement> getProductSizes() {
        waitForElementToBeVisible(LocatorManager.getLocator("cart", "productSizeInCart"));
        return driver.findElements(LocatorManager.getLocator("cart", "productSizeInCart"));
    }

    public int getItemCount() {
        return getProductNames().size();
    }

    public void proceedToCheckout() {
        driver.findElement(LocatorManager.getLocator("cart", "checkoutButton")).click();
    }

    public boolean isCartPageDisplayed() {
        By summaryTitle = LocatorManager.getLocator("cart", "summaryTitle");
        waitForElementToBeVisible(summaryTitle);
        return driver.findElement(summaryTitle).isDisplayed();
    }
}
