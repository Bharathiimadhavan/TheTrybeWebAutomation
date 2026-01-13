package stepDefinitions;

import context.TestContext;
import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductDetailPageSteps {

    private final Logger log = LoggerHelper.getLogger(ProductDetailPageSteps.class);
    private TestContext testContext;

    public ProductDetailPageSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("user selects size {string}")
    public void user_selects_size(String size) {
        testContext.getProductDetailPage().waitForPDPPageLoad();
        testContext.getProductDetailPage().selectSize(size);
        log.info("Selected size: " + size);
    }

    @When("validate the PDP page is displayed")
    public void validate_the_pdp_page_is_displayed(){
        testContext.getProductDetailPage().waitForPDPPageLoad();
        log.info("Navigated to PDP page successfully");
        Assert.assertTrue(testContext.getProductDetailPage().findInStoreDisplayed(), "Error - PDP page not displayed");
    }
    
    @When("clicks on Add to Cart button")
    public void clicks_on_add_to_cart_button() {
        testContext.getProductDetailPage().addToCart();
        log.info("Clicked on Add to Cart button");
    }

    @And("clicks on Go to Bag button")
    public void clicksOnGoToBagButton() {
        testContext.getProductDetailPage().clickGoToBag();
        log.info("Clicked on Go to Bag button");
    }

    @Then("the product {string} with size {string} should be in the cart")
    public void the_product_with_size_should_be_in_the_cart(String expectedProductName, String expectedSize) {
        // Get the parallel lists of names and sizes
        List<WebElement> productNameElements = testContext.getCartPage().getProductNames();
        List<WebElement> productSizeElements = testContext.getCartPage().getProductSizes();

        // Ensure the lists are not empty and have the same size
        Assert.assertFalse(productNameElements.isEmpty(), "The cart is empty. No products found.");
        Assert.assertEquals(productNameElements.size(), productSizeElements.size(),
                "Mismatch between the number of product names and sizes in the cart.");

        boolean isProductFoundAndVerified = false;

        // Loop through the lists by index
        for (int i = 0; i < productNameElements.size(); i++) {
            String actualProductName = productNameElements.get(i).getText();
            String actualSize = productSizeElements.get(i).getText();

            log.info("Checking cart item #" + (i + 1) + ": " + actualProductName + " | Size: " + actualSize);

            // Compare the i-th name and i-th size to the expected values
            if (actualProductName.equalsIgnoreCase(expectedProductName) && actualSize.contains(expectedSize)) {
                log.info("SUCCESS: Found matching product in cart at index " + i);
                isProductFoundAndVerified = true;
                break; // Exit the loop as we've found our match
            }
        }

        // After checking all items, assert that a match was found
        Assert.assertTrue(isProductFoundAndVerified,
                "Verification failed. Product '" + expectedProductName + "' with size containing '" + expectedSize + "' was not found in the cart.");

        log.info("Successfully verified product '" + expectedProductName + "' with size '" + expectedSize + "' is in the cart.");
    }
}
