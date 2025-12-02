package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WishlistPage extends BasePage {

    private By wishlistItems = By.cssSelector(".wishlist-item, .wishlist_table tr");
    private By itemNames = By.cssSelector(".wishlist-item .product-name, .wishlist_table .product-name");

    public int getWishlistCount() {
        return driver.findElements(wishlistItems).size();
    }

    public boolean isAnyItemPresent() {
        return getWishlistCount() > 0;
    }

    public boolean isProductPresentByName(String name) {
        List<WebElement> names = driver.findElements(itemNames);
        return names.stream().anyMatch(e -> e.getText().toLowerCase().contains(name.toLowerCase()));
    }
}
