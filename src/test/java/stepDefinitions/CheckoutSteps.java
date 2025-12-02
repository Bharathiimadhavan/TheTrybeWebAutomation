package stepDefinitions;

import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.*;

public class CheckoutSteps {

    private final Logger log = LoggerHelper.getLogger(CheckoutSteps.class);
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderConfirmationPage confirmationPage;

    @Given("user is on home page")
    public void user_is_on_home_page() {
        homePage = new HomePage();
        homePage.open();
        log.info("User navigated to home page");
    }

    @When("user searches for {string}")
    public void user_searches_for(String keyword) {
        homePage.searchFor(keyword);
        searchResultsPage = new SearchResultsPage();
        log.info("User searched for product: " + keyword);
        Assert.assertTrue(searchResultsPage.getResultsCount() > 0, "No products found for: " + keyword);
    }

    @When("adds first product to cart")
    public void adds_first_product_to_cart() {
        searchResultsPage.openFirstProduct();
        productDetailPage = new ProductDetailPage();
        productDetailPage.addToCart();
        log.info("Product added to cart");
    }

    @When("proceeds to checkout")
    public void proceeds_to_checkout() {
        cartPage = new CartPage();
        Assert.assertTrue(cartPage.getItemCount() > 0, "Cart is empty before checkout");
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage();
        checkoutPage.fillBillingDetails(
                "Bharathi",
                "Madhavan",
                "Test Street 1",
                "Chennai",
                "600001",
                "9000000000",
                "bharathi.madhavan@munrofg.com"
        );
        checkoutPage.placeOrder();
        log.info("Checkout form submitted");
    }

    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
        confirmationPage = new OrderConfirmationPage();
        Assert.assertTrue(confirmationPage.isOrderSuccessMessageDisplayed(), "Order success message not visible");
        String orderNo = confirmationPage.getOrderNumber();
        log.info("Order number: " + orderNo);
        Assert.assertFalse(orderNo.isEmpty(), "Order number is empty");
    }
}
