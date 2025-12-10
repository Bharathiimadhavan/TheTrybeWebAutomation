package pages;

import org.openqa.selenium.By;

import framework.LocatorManager;

public class AccountPage extends BasePage {

    private By accountName = By.cssSelector(".account-name, .woocommerce-MyAccount-content");
    private By ordersLink = By.linkText("Orders");
    private By loggedInAccountName = LocatorManager.getLocator("login", "loggedInAccountName");

    public boolean isLoggedIn() {
        //return driver.findElements(accountName).size() > 0;
        waitForElementToBeVisible(loggedInAccountName);
        String name = driver.findElement(loggedInAccountName).getText();
        if (name.contains("HELLO")) {
            return true;
            } else {
            return false;
            } 
    }

    public void goToOrders() {
        driver.findElement(ordersLink).click();
    }
}
