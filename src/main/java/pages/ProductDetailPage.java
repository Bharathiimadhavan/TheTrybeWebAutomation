package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.LocatorManager;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    //private By addToCartButton = By.cssSelector("button.add-to-cart, button.single_add_to_cart_button");
    private By productName = By.cssSelector("h1.product-title, h1.product_name");
    //private By price = By.cssSelector(".price, .summary .amount");

    private By afterPayLogoInPDP = LocatorManager.getLocator("pdp", "afterPayLogoInPDP");
    private By sizeToggles = LocatorManager.getLocator("pdp", "sizeToggles");
    private By addToCartButton = LocatorManager.getLocator("pdp", "addToCartButton");
    private By findInStoreButton = LocatorManager.getLocator("pdp", "findInStoreButton");
    private By productTitleInPDP = LocatorManager.getLocator("pdp", "productTitleInPDP");
    private By price = LocatorManager.getLocator("pdp", "price");
    private By wishlistFromPDP = LocatorManager.getLocator("pdp", "wishlistPDP");
    private By removeWishlistFromPDP = LocatorManager.getLocator("pdp", "removeFromWishlistPDP");
    public void waitForPDPPageLoad() {
        waitForElementToBeVisible(afterPayLogoInPDP);
    }

    public void clickGoToBag() {
        By notificationSidebar = LocatorManager.getLocator("pdp", "addToBagNotification");
        By goToBagButton = LocatorManager.getLocator("pdp", "goToBagButton");
        waitForElementToBeVisible(notificationSidebar);
        waitForElementToBeClickable(goToBagButton);
        click(goToBagButton);
    }
    public String getProductName() {
        return driver.findElement(productTitleInPDP).getText();
    }

    public String getPrice() {
        return driver.findElement(price).getText();
    }

    public void addToCart() {
        // Using jsClick to avoid potential element interception issues by overlays or sticky headers.
        jsClick(addToCartButton);
    }

    public void wishlistFromPDP() {
        // Using jsClick to avoid potential element interception issues by overlays or sticky headers.
        waitInSeconds(1);
        jsClick(wishlistFromPDP);
        waitInSeconds(1);
    }

    public void removeWishlistFromPDP() {
        // Using jsClick to avoid potential element interception issues by overlays or sticky headers.
        waitInSeconds(1);
        jsClick(removeWishlistFromPDP);
        waitInSeconds(1);
    }

    public void selectSize(String size) {
        By sizeLocator = LocatorManager.getDynamicLocator("pdp", "sizeButton", size);
        waitForElementToBeVisible(sizeLocator);
        waitForElementToBeClickable(sizeLocator);
        click(sizeLocator);
    }

    public boolean afterPayLogoDisplayed()    {
        return driver.findElement(afterPayLogoInPDP).isDisplayed();
    }

    public boolean removeWishlistDisplayed()
    {
        return driver.findElement(removeWishlistFromPDP).isDisplayed();
    }

    public boolean findInStoreDisplayed()    {
        waitForElementToBeClickable(findInStoreButton);
        return driver.findElement(findInStoreButton).isDisplayed();
    }
}
