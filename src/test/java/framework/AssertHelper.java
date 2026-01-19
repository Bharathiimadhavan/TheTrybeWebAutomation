package framework;

import org.testng.asserts.SoftAssert;

public final class AssertHelper {

    // Use ThreadLocal to ensure thread safety for SoftAssert in parallel execution
    private static final ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

    private AssertHelper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Initializes a new SoftAssert instance for the current thread.
     * This should be called at the beginning of each test scenario.
     */
    public static void initialize() {
        softAssert.set(new SoftAssert());
    }

    /**
     * Executes all collected assertions and clears the SoftAssert object.
     * If any assertion failed, it throws an AssertionException with a consolidated report.
     * This should be called at the end of each test scenario.
     */
    public static void assertAll() {
        try {
            softAssert.get().assertAll();
        } catch (AssertionError e) {
            // Wrap the TestNG assertion error in our custom exception
            throw new AssertionException(e.getMessage());
        } finally {
            // Ensure the ThreadLocal is cleaned up
            softAssert.remove();
        }
    }

    /**
     * Asserts that the actual text contains the expected text using a soft assertion.
     *
     * @param actualText   The text retrieved from the application.
     * @param expectedText The text that should be contained within the actual text.
     */
    public static void assertContains(String actualText, String expectedText) {
        softAssert.get().assertTrue(actualText.contains(expectedText),
                String.format("Assertion failed. Expected to contain: '%s', but Actual: '%s'", expectedText, actualText));
    }
    
    /**
     * Asserts that two strings are equal using a soft assertion.
     *
     * @param actualText   The actual text retrieved from the application.
     * @param expectedText The expected text.
     */
    public static void assertEquals(String actualText, String expectedText) {
        softAssert.get().assertEquals(actualText, expectedText,
                String.format("Assertion failed. Expected: '%s', but Actual: '%s'", expectedText, actualText));
    }
/**
     * Asserts that a condition is true using a soft assertion.
     *
     * @param condition The condition to evaluate.
     * @param message   The message to display if the assertion fails.
     */
    public static void assertTrue(boolean condition, String message) {
        softAssert.get().assertTrue(condition, message);
    }
}