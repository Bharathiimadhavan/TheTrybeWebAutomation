package pages;

import framework.ConfigReader;
import framework.LocatorManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By searchBox = LocatorManager.getLocator("home", "searchBox");
    private By searchButton = LocatorManager.getLocator("home", "searchButton");
    private By loginLink = LocatorManager.getLocator("home", "loginLink");
    private By wishlistLink = LocatorManager.getLocator("home", "wishlistLink");
    private By menShoesCategory = LocatorManager.getLocator("home", "menShoesCategory");
    private By loggedInAccountName = LocatorManager.getLocator("login", "loggedInAccountName");
    private By searchResults = LocatorManager.getLocator("home", "searchResults");
    private By wishlistPageHeading = LocatorManager.getLocator("home", "wishlistPageHeading");
    
    public void open() {
        driver.get(ConfigReader.get("url"));
        // Use the new, more reliable popup handler with a 3-second timeout.
        closePopupIfPresent(By.id("closeButton"), 3);
    }

    public void searchFor(String keyword) {
        // With the popup reliably handled, a standard click should now work.
        waitForElementToBeVisible(searchButton);
        click(searchButton);
        waitForElementToBeVisible(searchBox);
        driver.findElement(searchBox).clear();
        sendKeys(searchBox, keyword);
        enter(searchBox);
        // Add a short, explicit wait to allow search results to load via AJAX.
        waitInSeconds(2);
        waitForElementToBeVisible(searchResults);
    }

    public void openLogin() {
        click(loginLink);
        waitInSeconds(1);
    }

    public void openWishlist() {
        click(wishlistLink);
    }

    public void openMenShoesCategory() {
        click(menShoesCategory);
    }

    public String wishlistHeading() {
        String heading = driver.findElement(wishlistPageHeading).getText();
        return heading;
    }
}
