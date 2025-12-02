package pages;

import framework.ConfigReader;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By searchBox = By.cssSelector("input[type='search'], input[placeholder*='Search'], input[name*='search']");
    private By searchButton = By.cssSelector("button[type='submit'], button.search-btn");
    private By loginLink = By.xpath("//a[contains(.,'Login') or contains(.,'Sign in')]");
    private By wishlistLink = By.xpath("//a[contains(.,'Wishlist')]");
    private By menShoesCategory = By.xpath("//a[contains(.,'Men') and contains(.,'Shoes')]");

    public void open() {
        driver.get(ConfigReader.get("url"));
    }

    public void searchFor(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    public void openLogin() {
        driver.findElement(loginLink).click();
    }

    public void openWishlist() {
        driver.findElement(wishlistLink).click();
    }

    public void openMenShoesCategory() {
        driver.findElement(menShoesCategory).click();
    }
}
