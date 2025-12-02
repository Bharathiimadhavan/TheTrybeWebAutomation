package stepDefinitions;

import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.CategoryPage;
import pages.HomePage;

public class FiltersSteps {

    private final Logger log = LoggerHelper.getLogger(FiltersSteps.class);
    private HomePage homePage = new HomePage();
    private CategoryPage categoryPage = new CategoryPage();

    @Given("user is on category page {string}")
    public void user_is_on_category_page(String categoryName) {
        homePage.open();
        homePage.openMenShoesCategory();
        Assert.assertTrue(categoryPage.getProductCount() > 0, "Category has no products");
        log.info("Category page opened");
    }

    @When("user applies minimum price filter {string}")
    public void user_applies_minimum_price_filter(String minPrice) {
        categoryPage.applyMinPrice(minPrice);
        log.info("Filter applied: " + minPrice);
    }

    @Then("filtered results should match price {int}")
    public void filtered_results_should_match_price(Integer minPrice) {
        Assert.assertTrue(categoryPage.verifyAllPricesGreaterThanOrEqual(minPrice), "Prices not filtered correctly");
        log.info("Filters validated successfully");
    }
}
