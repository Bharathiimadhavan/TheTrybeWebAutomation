package stepDefinitions;

import context.TestContext;
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
}