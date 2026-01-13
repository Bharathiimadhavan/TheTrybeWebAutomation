package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import framework.LocatorManager;

import java.util.List;

public class WishlistPage extends BasePage {

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    private By wishlistItems = By.cssSelector(".wishlist-item, .wishlist_table tr");
    private By itemNames = By.cssSelector(".wishlist-item .product-name, .wishlist_table .product-name");
    private By productNamesInTheWishlist = LocatorManager.getLocator("wishlist", "productNamesInTheWishlist");
    private By wishlistPageHeading = LocatorManager.getLocator("home", "wishlistPageHeading");

    public int getWishlistCount() {
        return driver.findElements(wishlistItems).size();
    }

    public boolean isAnyItemPresent() {
        return getWishlistCount() > 0;
    }

    public boolean isProductPresentByName(String name) {
        waitForElementToBeVisible(wishlistPageHeading);
        List<WebElement> names = driver.findElements(productNamesInTheWishlist);
        return names.stream().anyMatch(e -> e.getText().toLowerCase().contains(name.toLowerCase()));
    }

    public String getSelectedSizeForProduct(String productName) {
        By inputLocator = LocatorManager.getDynamicLocator("wishlist", "productSizeInputInWishlist", productName.toLowerCase());
        WebElement inputElement = driver.findElement(inputLocator);
        return inputElement.getAttribute("value");
    }
}
