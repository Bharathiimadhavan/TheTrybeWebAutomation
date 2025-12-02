package pages;

import org.openqa.selenium.By;

public class ProductDetailPage extends BasePage {

    private By addToCartButton = By.cssSelector("button.add-to-cart, button.single_add_to_cart_button");
    private By productName = By.cssSelector("h1.product-title, h1.product_name");
    private By price = By.cssSelector(".price, .summary .amount");

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getPrice() {
        return driver.findElement(price).getText();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
