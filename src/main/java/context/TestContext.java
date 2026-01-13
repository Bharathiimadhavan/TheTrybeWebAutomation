package context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

public class TestContext {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderConfirmationPage orderConfirmationPage;
    private CategoryPage categoryPage;
    private AccountPage accountPage;
    private LoginPage loginPage;
    private WishlistPage wishlistPage;

    public WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            //homePage = new HomePage();
            homePage = new HomePage(getDriver()); //  ✅ Pass driver here
        }
        return homePage;
    }

    public SearchResultsPage getSearchResultsPage() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage(getDriver());
        }
        return searchResultsPage;
    }

    public ProductDetailPage getProductDetailPage() {
        if (productDetailPage == null) {
            productDetailPage = new ProductDetailPage(getDriver());
        }
        return productDetailPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(getDriver());
        }
        return cartPage;
    }

    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(getDriver());
        }
        return checkoutPage;
    }

    public OrderConfirmationPage getOrderConfirmationPage() {
        if (orderConfirmationPage == null) {
            orderConfirmationPage = new OrderConfirmationPage(getDriver());
        }
        return orderConfirmationPage;
    }

    public CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new CategoryPage(getDriver());
        }
        return categoryPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(getDriver());
        }
        return loginPage;
    }

    public AccountPage getAccountPage() {
        if (accountPage == null) {
        accountPage = new AccountPage(getDriver()); //  ✅ Pass driver here
        }
        return accountPage;
    }

    public WishlistPage getWishlistPage() {
        if (wishlistPage == null) {
            wishlistPage = new WishlistPage(getDriver());
        }
        return wishlistPage;
    }

}