package stepDefinitions;

import context.TestContext;
import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
//import pages.*;

public class CheckoutSteps {

    private final Logger log = LoggerHelper.getLogger(CheckoutSteps.class);
    private TestContext testContext;

    public CheckoutSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("user is on home page")
    public void user_is_on_home_page() {
        testContext.getHomePage().open();
        log.info("User navigated to home page");
    }

    @When("user searches for {string}")
    public void user_searches_for(String keyword) {
        testContext.getHomePage().searchFor(keyword);
        log.info("User searched for product: " + keyword);
        Assert.assertTrue(testContext.getSearchResultsPage().getResultsCount() > 0, "No products found for: " + keyword);
    }

    @When("clicks the first product")
    public void clicks_the_first_product() {
        testContext.getSearchResultsPage().openFirstProduct();
        testContext.getProductDetailPage().waitForPDPPageLoad();
        log.info("Product clicked from search results page and navigated to PDP");
        Assert.assertTrue(testContext.getProductDetailPage().afterPayLogoDisplayed(), "Error - PDP page not displayed");
    }

    @When("adds first product to cart")
    public void adds_first_product_to_cart() {
        testContext.getProductDetailPage().addToCart();
        log.info("Product added to cart");
    }

    @When("wishlist the product from PDP")
    public void wishlist_the_product_from_pdp() {
        testContext.getProductDetailPage().waitForPDPPageLoad();
        testContext.getProductDetailPage().wishlistFromPDP();
        log.info("Product added to Wishlist from the PDP");
        Assert.assertTrue(testContext.getProductDetailPage().removeWishlistDisplayed(), "Error - Product is not wishlisted in the PDP");
    }

    @When("proceeds to checkout")
    public void proceeds_to_checkout() {
        Assert.assertTrue(testContext.getCartPage().getItemCount() > 0, "Cart is empty before checkout");
        testContext.getCartPage().proceedToCheckout();
        testContext.getCheckoutPage().fillBillingDetails(
                "Bharathi",
                "Madhavan",
                "Test Street 1",
                "Chennai",
                "600001",
                "9000000000",
                "bharathi.madhavan@munrofg.com"
        );
        testContext.getCheckoutPage().placeOrder();
        log.info("Checkout form submitted");
    }

    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
        Assert.assertTrue(testContext.getOrderConfirmationPage().isOrderSuccessMessageDisplayed(), "Order success message not visible");
        String orderNo = testContext.getOrderConfirmationPage().getOrderNumber();
        log.info("Order number: " + orderNo);
        Assert.assertFalse(orderNo.isEmpty(), "Order number is empty");
    }
}
