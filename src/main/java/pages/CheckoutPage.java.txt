package pages;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private By billingFirstName = By.id("billing_first_name");
    private By billingLastName = By.id("billing_last_name");
    private By billingAddress1 = By.id("billing_address_1");
    private By billingCity = By.id("billing_city");
    private By billingPostcode = By.id("billing_postcode");
    private By billingPhone = By.id("billing_phone");
    private By billingEmail = By.id("billing_email");
    private By placeOrderButton = By.cssSelector("#place_order, button.place-order");
    private By validationError = By.cssSelector(".woocommerce-error, .error, .validation-error");

    public void fillBillingDetails(String first, String last, String address, String city, String postcode, String phone, String email) {
        driver.findElement(billingFirstName).sendKeys(first);
        driver.findElement(billingLastName).sendKeys(last);
        driver.findElement(billingAddress1).sendKeys(address);
        driver.findElement(billingCity).sendKeys(city);
        driver.findElement(billingPostcode).sendKeys(postcode);
        driver.findElement(billingPhone).sendKeys(phone);
        driver.findElement(billingEmail).sendKeys(email);
    }

    public void placeOrder() {
        driver.findElement(placeOrderButton).click();
    }

    public boolean isValidationErrorDisplayed() {
        return driver.findElements(validationError).size() > 0;
    }
}
