package pages;

import framework.ConfigReader;
import framework.LocatorManager;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By searchBox = LocatorManager.getLocator("home", "searchBox");
    private By searchButton = LocatorManager.getLocator("home", "searchButton");
    private By loginLink = LocatorManager.getLocator("home", "loginLink");
    private By wishlistLink = LocatorManager.getLocator("home", "wishlistLink");
    private By menShoesCategory = LocatorManager.getLocator("home", "menShoesCategory");

    public void open() {
        driver.get(ConfigReader.get("url"));
        handleWelcomePopup();
    }

    private void handleWelcomePopup() {
        try {
            By popupCloseButton = By.id("closeButton");
            waitInSeconds(1);
            waitForElementToBeVisible(popupCloseButton);
            waitForElementToBeClickable(popupCloseButton);
            click(popupCloseButton);
        } catch (Exception e) {
            // Popup may not appear, so we can ignore the exception
        }
    }

    public void searchFor(String keyword) {
        waitForElementToBeVisible(searchBox);
        driver.findElement(searchBox).clear();
        sendKeys(searchBox, keyword);
        click(searchButton);
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
}
