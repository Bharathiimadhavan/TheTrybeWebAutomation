package hooks;

import context.TestContext;
import framework.ConfigReader;
import framework.LoggerHelper;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private final Logger log = LoggerHelper.getLogger(Hooks.class);
    private TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    static {
        ConfigReader.loadConfig();
    }

    @Before
    public void setUp() {
        testContext.getDriver();
        log.info("Browser launched for scenario");
    }

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                WebDriver driver = testContext.getDriver();
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
                log.info("Screenshot taken and attached for failed step.");
            } catch (Exception e) {
                log.error("Error taking or attaching the screenshot: " + e.getMessage());
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        testContext.quitDriver();
        log.info("Browser closed for scenario: " + scenario.getName());
    }
}
