package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.LocatorManager;

public class ProductListingPage extends BasePage {

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

        // private By filterPLPPage = LocatorManager.getLocator("plp", "filterPLPPage");
        private By breadcrumbsPLPPage = LocatorManager.getLocator("plp", "breadcrumbsPLPPage");

    public void waitForPLPPageLoad() {
        // waitForElementToBeVisible(filterPLPPage);
        waitForElementToBeVisible(breadcrumbsPLPPage);
    }

    public void clickCategoryLink(String category) {
        By categoryLink = framework.LocatorManager.getDynamicLocator("plp", "categoryLink", category);
        wait.until(ExpectedConditions.elementToBeClickable(categoryLink)).click();
    }

    public String getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(framework.LocatorManager.getLocator("plp", "pageHeading"))).getText();
    }
}