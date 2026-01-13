package stepDefinitions;

import context.TestContext;
import framework.LoggerHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.WishlistPage;

public class WishlistSteps {

    private final Logger log = LoggerHelper.getLogger(WishlistSteps.class);
    private final TestContext testContext;

    public WishlistSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    /* @Given("user is on home page")
    public void user_is_on_home_page() {
        HomePage homePage = testContext.getHomePage();
        homePage.open();
    } */

    @When("adds first product to wishlist from PLP")
    public void adds_first_product_to_wishlist_from_plp() {
        SearchResultsPage searchResultsPage = testContext.getSearchResultsPage();
        searchResultsPage.addFirstProductToWishlist();
        log.info("Product added to wishlist");
    }

    @When("navigates to wishlist page")
    public void navigates_to_wishlist_page() {
        HomePage homePage = testContext.getHomePage();
        homePage.openWishlist();
        log.info("Navigated to wishlist page");
    }

    @Then("product should appear in wishlist")
    public void product_should_appear_in_wishlist() {
        WishlistPage wishlistPage = testContext.getWishlistPage();
        Assert.assertTrue(wishlistPage.isAnyItemPresent(), "Wishlist is empty");
        log.info("Product present in wishlist");
    }

    @Then("product {string} should appear in the wishlist page")
    public void wishlisted_product_should_appear_in_the_wishlist_page(String productName) {
        WishlistPage wishlistPage = testContext.getWishlistPage();
        log.info("Wishlisted product: " + productName);
        Assert.assertTrue(wishlistPage.isProductPresentByName(productName), productName + "product not appear in the Wishlist page");
        log.info("Product present in wishlist");
    }

    @Then("product {string} should appear in the wishlist page with size {string}")
    public void product_should_appear_in_the_wishlist_page_with_size(String productName, String expectedSize) {
        WishlistPage wishlistPage = testContext.getWishlistPage();

        // Validate product name
        Assert.assertTrue(wishlistPage.isProductPresentByName(productName),
                "Validation Failed: Product '" + productName + "' not found in wishlist.");
        log.info("Successfully verified product '" + productName + "' is present in the wishlist.");

        // Validate product size
        String actualSize = wishlistPage.getSelectedSizeForProduct(productName);
        Assert.assertEquals(actualSize, expectedSize,
                "Validation Failed: Size for '" + productName + "' does not match. Expected: " + expectedSize + ", Actual: " + actualSize);
        log.info("Successfully verified size '" + actualSize + "' for product '" + productName + "'.");
    }
}
