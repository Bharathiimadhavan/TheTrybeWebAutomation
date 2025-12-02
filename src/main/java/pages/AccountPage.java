package pages;

import org.openqa.selenium.By;

public class AccountPage extends BasePage {

    private By accountName = By.cssSelector(".account-name, .woocommerce-MyAccount-content");
    private By ordersLink = By.linkText("Orders");

    public boolean isLoggedIn() {
        return driver.findElements(accountName).size() > 0;
    }

    public void goToOrders() {
        driver.findElement(ordersLink).click();
    }
}
