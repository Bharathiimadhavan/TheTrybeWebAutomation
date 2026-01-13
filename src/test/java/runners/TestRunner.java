//


package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        // The 'features' path tells Cucumber where to find your .feature files.
        features = "src/test/resources/features",

        // The 'glue' path tells Cucumber where to find your step definition and hooks classes.
        // These should be package names.
        glue = {"stepDefinitions", "hooks"},

        // The 'plugin' configuration is now managed in pom.xml to allow dynamic report naming.
        // We only need to specify the Allure plugin here, as other reporting plugins
        // are configured through Maven.
        plugin = {
            "pretty", // Prints Gherkin steps to the console
            //"html:target/cucumber-report-${timestamp}.html",  // Use placeholder
            "html:target/cucumber-report.html", // Generates an HTML report
            "json:target/cucumber.json", // Generates a JSON report
            "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:src/test/resources/extent.properties"
            }, 

        // 'dryRun = false' ensures that the tests are actually executed.
        dryRun = false,

        // 'monochrome = true' makes the console output more readable.
        // We are adding the 'tags' attribute here temporarily to debug a specific scenario.
        monochrome = true
)


public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * This override enables parallel execution of scenarios with TestNG.
     * It provides the scenarios to TestNG's data provider.
     * @return A 2D array of scenarios to be executed.
     */
    static {
    String timestamp = java.time.LocalDateTime.now()
    .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
    System.setProperty("timestamp", timestamp);
    }
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        /* // Set the timestamp as a system property before running tests
        String timestamp = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                
        // Set the property so it can be used in the plugin path
        System.setProperty("timestamp", timestamp); */
        return super.scenarios();
    }
}
