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

        // The 'plugin' configuration sets up reporting.
        plugin = {
                "pretty", // Prints Gherkin steps to the console
                "html:target/cucumber-report.html", // Generates an HTML report
                "json:target/cucumber.json", // Generates a JSON report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Integrates with Allure Reporting
        },

        // 'dryRun = false' ensures that the tests are actually executed.
        dryRun = false,

        // 'monochrome = true' makes the console output more readable.
        // The 'tags' attribute is intentionally omitted to allow for dynamic filtering
        // from the Maven command line using -Dcucumber.filter.tags
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * This override enables parallel execution of scenarios with TestNG.
     * It provides the scenarios to TestNG's data provider.
     * @return A 2D array of scenarios to be executed.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
