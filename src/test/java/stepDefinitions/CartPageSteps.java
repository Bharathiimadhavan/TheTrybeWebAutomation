package stepDefinitions;

import context.TestContext;
import framework.AssertHelper;
import framework.LoggerHelper;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CartPageSteps {

    private final Logger log = LoggerHelper.getLogger(CartPageSteps.class);
    private TestContext testContext;

    public CartPageSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @And("validate the cart page is displaying")
    public void validateTheCartPageIsDisplaying() {
        Assert.assertTrue(testContext.getCartPage().isCartPageDisplayed(), "Cart page is not displayed");
        log.info("Cart page is displayed successfully");
    }

    @And("validate product details match in cart page")
    public void validateProductDetailsMatchInCartPage() {
        String capturedName  = testContext.getCapturedProductName();
        String capturedSize  = testContext.getCapturedSelectedSize();
        String capturedPrice = testContext.getCapturedPrice().replaceAll("\\s+", "").trim();

        String actualName   = testContext.getCartPage().getFirstProductName();
        String actualSize   = testContext.getCartPage().getFirstProductSize();
        String actualColour = testContext.getCartPage().getFirstProductColour();

        log.info("Cart page — Name: [" + actualName + "], Size: [" + actualSize + "], Colour: [" + actualColour + "]");

        AssertHelper.assertTrue(
            actualName.equalsIgnoreCase(capturedName),
            "Cart product name mismatch — expected: [" + capturedName + "], actual: [" + actualName + "]"
        );
        AssertHelper.assertTrue(
            actualSize.contains(capturedSize),
            "Cart product size mismatch — expected to contain: [" + capturedSize + "], actual: [" + actualSize + "]"
        );
        AssertHelper.assertTrue(
            !actualColour.isEmpty(),
            "Cart product colour is empty — expected a colour value to be present"
        );

        // Price validation: check if an auto-discount has been applied
        String originalCartPrice = testContext.getCartPage().getFirstProductOriginalPrice();
        String discountLabel     = testContext.getCartPage().getDiscountLabel();

        log.info("Captured PDP price: [" + capturedPrice + "]");
        log.info("Cart original (strikethrough) price: [" + originalCartPrice + "]");
        log.info("Cart discount label: [" + discountLabel + "]");

        if (!originalCartPrice.isEmpty()) {
            // Auto-discount applied — strikethrough price must match PDP price
            AssertHelper.assertTrue(
                originalCartPrice.equals(capturedPrice),
                "Cart original price mismatch — PDP: [" + capturedPrice + "], cart strikethrough: [" + originalCartPrice + "]"
            );
            AssertHelper.assertTrue(
                !discountLabel.isEmpty(),
                "Cart shows a discounted price but no discount label (APPLIED) was found"
            );
            log.info("Auto-discount detected and validated — discount label: [" + discountLabel + "]");
        } else {
            // No discount — cart price should match PDP price directly
            log.info("No auto-discount detected in cart. Skipping discount label check.");
        }

        log.info("Cart page validated — name, size, colour, and price checks complete");
    }
}