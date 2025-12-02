package pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private By cartItems = By.cssSelector(".cart-item, .woocommerce-cart-form__cart-item");
    private By checkoutButton = By.cssSelector("a.checkout-button, a.button.checkout");

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
