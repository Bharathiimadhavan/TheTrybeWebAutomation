package pages;

import framework.ConfigReader;
import framework.LocatorManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By emailField = LocatorManager.getLocator("login", "emailField");
    private By passwordField = LocatorManager.getLocator("login", "passwordField");
    private By loginButton = LocatorManager.getLocator("login", "loginButton");
    private By loggedInAccountName = LocatorManager.getLocator("login", "loggedInAccountName");
    private By logoutLink = LocatorManager.getLocator("login", "logoutLink");

    public void loginWithValidUser() {
        waitForElementToBeClickable(emailField);
        waitInSeconds(1);
        sendKeys(emailField, ConfigReader.get("username"));
        waitForElementToBeClickable(passwordField);
        sendKeys(passwordField, ConfigReader.get("password"));
        click(loginButton);
    }

    public boolean isLogoutVisible() {
        return driver.findElements(logoutLink).size() > 0;
    }

    public void logout() {
        click(logoutLink);
    }
}
