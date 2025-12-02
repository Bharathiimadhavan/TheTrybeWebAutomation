package stepDefinitions;

import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.WishlistPage;

public class WishlistSteps {

    private final Logger log = LoggerHelper.getLogger(WishlistSteps.class);
    private HomePage homePage = new HomePage();
    private SearchResultsPage searchResultsPage;
    private WishlistPage wishlistPage;

    @When("adds first product to wishlist")
    public void adds_first_product_to_wishlist() {
        searchResultsPage = new SearchResultsPage();
        searchResultsPage.addFirstProductToWishlist();
        log.info("Product added to wishlist");
    }

    @Then("product should appear in wishlist")
    public void product_should_appear_in_wishlist() {
        homePage.openWishlist();
        wishlistPage = new WishlistPage();
        Assert.assertTrue(wishlistPage.isAnyItemPresent(), "Wishlist is empty");
        log.info("Product present in wishlist");
    }
}
