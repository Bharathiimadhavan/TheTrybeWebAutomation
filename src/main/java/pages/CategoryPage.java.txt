package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CategoryPage extends BasePage {

    private By productCards = By.cssSelector(".product-card, .product-item");
    private By productPrices = By.cssSelector(".price, .amount");
    private By minPriceField = By.cssSelector("input[name*='min_price'], input#min_price");
    private By filterButton = By.cssSelector("button.filter, button[type='submit'].price_slider_amount_button");

    public int getProductCount() {
        return driver.findElements(productCards).size();
    }

    public void applyMinPrice(String min) {
        driver.findElement(minPriceField).clear();
        driver.findElement(minPriceField).sendKeys(min);
        driver.findElement(filterButton).click();
    }

    public boolean verifyAllPricesGreaterThanOrEqual(int minPrice) {
        List<WebElement> prices = driver.findElements(productPrices);
        for (WebElement priceEl : prices) {
            String text = priceEl.getText().replaceAll("[^0-9]", "");
            if (text.isEmpty()) continue;
            int value = Integer.parseInt(text);
            if (value < minPrice) return false;
        }
        return true;
    }
}
