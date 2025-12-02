package pages;

import framework.ConfigReader;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit'], button.login");
    private By logoutLink = By.xpath("//a[contains(.,'Logout') or contains(.,'Sign out')]");

    public void loginWithValidUser() {
        driver.findElement(emailField).sendKeys(ConfigReader.get("username"));
        driver.findElement(passwordField).sendKeys(ConfigReader.get("password"));
        driver.findElement(loginButton).click();
    }

    public boolean isLogoutVisible() {
        return driver.findElements(logoutLink).size() > 0;
    }

    public void logout() {
        driver.findElement(logoutLink).click();
    }
}
