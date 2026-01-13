package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.LocatorManager;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private By productCards = By.cssSelector(".product-card, .product-item");
    private By productLink = By.cssSelector(".product-card a, .product-title a");
    private By wishlistIcon = By.cssSelector(".add-to-wishlist, .wishlist-icon");
    private By plpAllProductTiles = LocatorManager.getLocator("plp", "plpAllProductTiles");
    private By productNameInThePLPTile = LocatorManager.getLocator("plp", "productNameInThePLPTile");
    private By firstProductInSearchResults = LocatorManager.getLocator("plp", "firstProductInSearchResults");
    private By wishlistinPLP = LocatorManager.getLocator("plp", "wishlistinPLP");
    private By afterPayLogoInPDP = LocatorManager.getLocator("pdp", "afterPayLogoInPDP");
    
    public int getResultsCount() {
        return driver.findElements(plpAllProductTiles).size();
    }

    public void openFirstProduct() {
        List<WebElement> links = driver.findElements(firstProductInSearchResults);
        if (!links.isEmpty()) {
            links.get(0).click();
            waitForElementToBeVisible(afterPayLogoInPDP);
        }
    }

    public void addFirstProductToWishlist() {
        WebElement ele = driver.findElement(wishlistinPLP);
        JavascriptExecutor js = (JavascriptExecutor) driver;
         //js.executeScript("arguments[0].scrollIntoView(true);", ele);
         js.executeScript("window.scrollBy(0, 300);");
         waitInSeconds(1);
         ele.click();
        //jsClick(wishlistinPLP);
        waitInSeconds(1);
        // List<WebElement> icons = driver.findElements(wishlistinPLP);
        // if (!icons.isEmpty()) {
        //    icons.get(0).click();
        // }
    }
}
