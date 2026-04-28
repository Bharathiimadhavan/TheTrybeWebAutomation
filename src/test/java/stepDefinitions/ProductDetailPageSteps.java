package stepDefinitions;

import context.TestContext;
import framework.LoggerHelper;
import framework.AssertHelper;
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
        testContext.setCapturedSelectedSize(size);
        log.info("Selected size: " + size);
    }

    @When("validate the PDP page is displayed")
    public void validate_the_pdp_page_is_displayed(){
        testContext.getProductDetailPage().waitForPDPPageLoad();
        log.info("Navigated to PDP page successfully");
        AssertHelper.assertTrue(testContext.getProductDetailPage().findInStoreDisplayed(), "Error - PDP page not displayed");
    }
    

    @Then("the product {string} with size {string} should be in the cart")
    public void the_product_with_size_should_be_in_the_cart(String expectedProductName, String expectedSize) {
        // Get the parallel lists of names and sizes
        List<WebElement> productNameElements = testContext.getCartPage().getProductNames();
        List<WebElement> productSizeElements = testContext.getCartPage().getProductSizes();

        // Ensure the lists are not empty and have the same size
        AssertHelper.assertTrue(!productNameElements.isEmpty(), "The cart is empty. No products found.");
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
        AssertHelper.assertTrue(isProductFoundAndVerified,
                "Verification failed. Product '" + expectedProductName + "' with size containing '" + expectedSize + "' was not found in the cart.");

        log.info("Successfully verified product '" + expectedProductName + "' with size '" + expectedSize + "' is in the cart.");
    }

    @And("user captures product details from PDP")
    public void userCapturesProductDetailsFromPDP() {
        String name = testContext.getProductDetailPage().getProductName();
        String price = testContext.getProductDetailPage().getPrice();
        testContext.setCapturedProductName(name);
        testContext.setCapturedPrice(price);
        log.info("Captured PDP details — Name: [" + name + "], Price: [" + price + "], Size: [" + testContext.getCapturedSelectedSize() + "]");
    }

    @And("validate mini-cart contains correct product details")
    public void validateMiniCartContainsCorrectProductDetails() {
        String capturedName  = testContext.getCapturedProductName();
        String capturedSize  = testContext.getCapturedSelectedSize();
        // Normalise captured price: strip whitespace so "$79.99" matches regardless of spacing
        String capturedPrice = testContext.getCapturedPrice().replaceAll("\\s+", "").trim();

        String actualName  = testContext.getProductDetailPage().getMiniCartProductName();
        String actualPrice = testContext.getProductDetailPage().getMiniCartProductPrice();
        // Use full sidebar text only for size check (size is plain text, no split-node issue)
        String sidebarText = testContext.getProductDetailPage().getSidebarText();

        log.info("Mini-cart — actualName: [" + actualName + "], actualPrice: [" + actualPrice + "], sidebarText: [" + sidebarText + "]");
        log.info("Captured  — name: [" + capturedName + "], size: [" + capturedSize + "], price: [" + capturedPrice + "]");

        AssertHelper.assertTrue(
            actualName.equalsIgnoreCase(capturedName),
            "Mini-cart name mismatch — expected: [" + capturedName + "], actual: [" + actualName + "]"
        );
        AssertHelper.assertTrue(
            sidebarText.contains(capturedSize),
            "Mini-cart does not contain selected size: [" + capturedSize + "]"
        );
        AssertHelper.assertTrue(
            actualPrice.equals(capturedPrice),
            "Mini-cart price mismatch — expected: [" + capturedPrice + "], actual: [" + actualPrice + "]"
        );
        log.info("Mini-cart validated — name, size, and price match captured PDP details");
    }

    @And("user clicks on Add to Bag button")
    public void userClicksOnAddToBagButton() {
        testContext.getProductDetailPage().addToCart();
        log.info("Clicked on Add to Bag button");
    }

    @And("validate mini-cart is displaying")
    public void validateMiniCartIsDisplaying() {
        AssertHelper.assertTrue(testContext.getProductDetailPage().isMiniCartDisplayed(), "Mini-cart is not displayed");
        log.info("Mini-cart is displayed");
    }

    @And("user clicks on Go to Bag button")
    public void userClicksOnGoToBagButton() {
        testContext.getProductDetailPage().clickGoToBag();
        log.info("Clicked on Go to Bag button");
    }
}
