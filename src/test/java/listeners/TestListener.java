package listeners;

import framework.BaseDriver;
import framework.LoggerHelper;
import framework.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    private final Logger log = LoggerHelper.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("TEST FAILED → " + result.getMethod().getMethodName());
        try {
            byte[] screenshot = ((TakesScreenshot) BaseDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
            ScreenshotUtil.takeScreenshot(BaseDriver.getDriver(), result.getMethod().getMethodName());
        } catch (Exception e) {
            log.error("Screenshot failure: " + e.getMessage());
        }
    }
}
