package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private By productCards = By.cssSelector(".product-card, .product-item");
    private By productLink = By.cssSelector(".product-card a, .product-title a");
    private By wishlistIcon = By.cssSelector(".add-to-wishlist, .wishlist-icon");

    public int getResultsCount() {
        return driver.findElements(productCards).size();
    }

    public void openFirstProduct() {
        List<WebElement> links = driver.findElements(productLink);
        if (!links.isEmpty()) {
            links.get(0).click();
        }
    }

    public void addFirstProductToWishlist() {
        List<WebElement> icons = driver.findElements(wishlistIcon);
        if (!icons.isEmpty()) {
            icons.get(0).click();
        }
    }
}
