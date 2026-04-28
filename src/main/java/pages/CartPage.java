package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.LocatorManager;

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

    /**
     * Returns the colour value from the cart item.
     * The cart page renders colour as "Colour: White" — this method returns just "White".
     */
    public String getFirstProductColour() {
        By colourLocator = LocatorManager.getLocator("cart", "productColourInCart");
        waitForElementToBeVisible(colourLocator);
        String colourText = driver.findElement(colourLocator).getText();
        // colourText is "Colour: White" — extract value after ": "
        return colourText.contains(":") ? colourText.split(":", 2)[1].trim() : colourText.trim();
    }

    /**
     * Returns the text of the first product name element in the cart.
     */
    public String getFirstProductName() {
        List<WebElement> names = getProductNames();
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    /**
     * Returns the text of the first product size element in the cart.
     * Cart renders size as "Size: 2" — callers should use contains() for matching.
     */
    public String getFirstProductSize() {
        List<WebElement> sizes = getProductSizes();
        return sizes.isEmpty() ? "" : sizes.get(0).getText();
    }

    /**
     * Returns the original (pre-discount) strikethrough price from the first cart item.
     * The cart renders original price inside an <s> tag when a discount is applied.
     * Uses JS textContent to handle split text nodes. Returns empty string if no discount.
     */
    public String getFirstProductOriginalPrice() {
        By loc = LocatorManager.getLocator("cart", "cartProductOriginalPrice");
        List<WebElement> els = driver.findElements(loc);
        if (els.isEmpty()) return "";
        String raw = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].textContent;", els.get(0));
        return raw.replaceAll("\\s+", "").trim();
    }

    /**
     * Returns the discount/promo label text visible in the cart (e.g. "25% OFF SITEWIDE* APPLIED").
     * Returns empty string if no discount label is present.
     */
    public String getDiscountLabel() {
        By loc = LocatorManager.getLocator("cart", "cartDiscountLabel");
        List<WebElement> els = driver.findElements(loc);
        return els.isEmpty() ? "" : els.get(0).getText().trim();
    }
}
