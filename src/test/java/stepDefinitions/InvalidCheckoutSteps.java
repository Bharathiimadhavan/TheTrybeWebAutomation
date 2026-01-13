package stepDefinitions;

import context.TestContext;
import framework.LoggerHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.*;

public class InvalidCheckoutSteps {

    private final Logger log = LoggerHelper.getLogger(InvalidCheckoutSteps.class);
    private final TestContext testContext;

    public InvalidCheckoutSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("user has product in cart")
    public void user_has_product_in_cart() {
        HomePage homePage = testContext.getHomePage();
        homePage.open();
        homePage.searchFor("shoes");
        SearchResultsPage searchResultsPage = testContext.getSearchResultsPage();
        Assert.assertTrue(searchResultsPage.getResultsCount() > 0, "No products found");
        searchResultsPage.openFirstProduct();
        ProductDetailPage productDetailPage = testContext.getProductDetailPage();
        productDetailPage.addToCart();
        CartPage cartPage = testContext.getCartPage();
        Assert.assertTrue(cartPage.getItemCount() > 0, "Cart is empty");
        log.info("Product added to cart for invalid checkout test");
    }

    @When("user tries to place order without filling billing information")
    public void user_tries_to_place_order_without_filling_billing_information() {
        CartPage cartPage = testContext.getCartPage();
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = testContext.getCheckoutPage();
        checkoutPage.placeOrder();
        log.info("Order placed without entering billing details");
    }

    @Then("validation errors must appear")
    public void validation_errors_must_appear() {
        CheckoutPage checkoutPage = testContext.getCheckoutPage();
        Assert.assertTrue(checkoutPage.isValidationErrorDisplayed(), "Validation messages not displayed");
        log.info("Validation messages successfully displayed");
    }
}
