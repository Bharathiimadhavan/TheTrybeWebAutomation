package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ProductListingPage;
import context.TestContext;
import framework.AssertHelper;
import framework.ConfigReader;
import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ProductListPageSteps {

    private final Logger log = LoggerHelper.getLogger(ProductDetailPageSteps.class);
    private TestContext testContext;
    ProductListingPage productListingPage;

    public ProductListPageSteps(TestContext context) {
        testContext = context;
        productListingPage = new ProductListingPage(testContext.getDriver());
    }

    @When("user click on the {string} category link")
    public void user_click_on_the_category_link(String category) {
        productListingPage.clickCategoryLink(category);
    }

    @Then("user should be on the {string} page with the URL containing {string}")
    public void user_should_be_on_the_page_with_the_url_containing(String category, String url) {
        testContext.getProductListPage().waitForPLPPageLoad();
        log.info("Navigated to the "+category+" category page successfully");
        String actualUrl = productListingPage.getCurrentUrl();
        AssertHelper.assertContains(actualUrl, url);
    }

    @Then("the page heading should be {string}")
    public void the_page_heading_should_be(String heading) {
        AssertHelper.assertEquals(productListingPage.getPageHeading(), heading);
    }
    @When("user clicks on each category and validates the page content")
    public void user_clicks_on_each_category_and_validates_the_page_content(DataTable dataTable) {
        List<Map<String, String>> categories = dataTable.asMaps(String.class, String.class);
        String baseUrl = ConfigReader.get("url");

        for (Map<String, String> categoryData : categories) {
            String category = categoryData.get("category");
            String expectedUrlFragment = categoryData.get("url");
            String expectedHeading = categoryData.get("heading");

            log.info("--- Validating Category: " + category + " ---");

            productListingPage.clickCategoryLink(category);
            log.info("Clicked on '" + category + "' category link.");

            productListingPage.waitForPLPPageLoad();
            String actualUrl = productListingPage.getCurrentUrl();
            AssertHelper.assertContains(actualUrl, expectedUrlFragment);
            log.info("Category's URL validation passed. Expected URL: " +expectedUrlFragment+". Actual URL: " + actualUrl);

            String actualHeading = productListingPage.getPageHeading();
            AssertHelper.assertEquals(actualHeading, expectedHeading);
            log.info("Category's Heading validation passed. Expected Heading: "+expectedHeading+". Actual Heading: " + actualHeading);

            testContext.getDriver().navigate().to(baseUrl);
            log.info("Navigated back to home page.");
        }
    }
}