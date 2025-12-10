package hooks;

import framework.BaseDriver;
import framework.ConfigReader;
import framework.LoggerHelper;
import framework.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private final Logger log = LoggerHelper.getLogger(Hooks.class);

    static {
        ConfigReader.loadConfig();
    }

    @Before
    public void setUp() {
        BaseDriver.getDriver();
        log.info("Browser launched for scenario");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                WebDriver driver = BaseDriver.getDriver();
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Screenshot");
                ScreenshotUtil.takeScreenshot(driver, scenario.getName());
                log.error("Scenario FAILED → Screenshot captured");
            } catch (Exception e) {
                log.error("Screenshot error: " + e.getMessage());
            }
        }
        BaseDriver.quitDriver();
        log.info("Browser closed");
    }
}
