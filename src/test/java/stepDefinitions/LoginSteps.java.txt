package stepDefinitions;

import framework.LoggerHelper;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {

    private final Logger log = LoggerHelper.getLogger(LoginSteps.class);
    private HomePage homePage = new HomePage();
    private LoginPage loginPage;
    private AccountPage accountPage;

    @When("user opens login page")
    public void user_opens_login_page() {
        homePage.openLogin();
        loginPage = new LoginPage();
        log.info("Opened login page");
    }

    @When("user logs in using valid credentials")
    public void user_logs_in_using_valid_credentials() {
        loginPage.loginWithValidUser();
        accountPage = new AccountPage();
        log.info("User submitted login form");
    }

    @Then("user should be logged in")
    public void user_should_be_logged_in() {
        Assert.assertTrue(accountPage.isLoggedIn(), "User is not logged in");
        log.info("Login successful");
    }

    @Then("user logs out successfully")
    public void user_logs_out_successfully() {
        loginPage.logout();
        log.info("User logged out successfully");
    }
}
