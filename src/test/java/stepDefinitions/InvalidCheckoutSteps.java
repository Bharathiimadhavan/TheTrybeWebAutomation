
```java
package stepDefinitions;

import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.*;

public class InvalidCheckoutSteps {

    private final Logger log = LoggerHelper.getLogger(InvalidCheckoutSteps.class);
    private HomePage homePage = new HomePage();
    private SearchResultsPage searchResultsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("user has product in cart")
    public void user_has_product_in_cart() {
        homePage.open();
        homePage.searchFor("shoes");
        searchResultsPage = new SearchResultsPage();
        Assert.assertTrue(searchResultsPage.getResultsCount() > 0, "No products found");
        searchResultsPage.openFirstProduct();
        productDetailPage = new ProductDetailPage();
        productDetailPage.addToCart();
        cartPage = new CartPage();
        Assert.assertTrue(cartPage.getItemCount() > 0, "Cart is empty");
        log.info("Product added to cart for invalid checkout test");
    }

    @When("user tries to place order without filling billing information")
    public void user_tries_to_place_order_without_filling_billing_information() {
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage();
        checkoutPage.placeOrder();
        log.info("Order placed without entering billing details");
    }

    @Then("validation errors must appear")
    public void validation_errors_must_appear() {
        Assert.assertTrue(checkoutPage.isValidationErrorDisplayed(), "Validation messages not displayed");
        log.info("Validation messages successfully displayed");
    }
}
