package framework;

import org.testng.Assert;

public final class AssertHelper {

    private AssertHelper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Asserts that the actual text contains the expected text. On failure, provides a
     * detailed message.
     *
     * @param actualText   The text retrieved from the application.
     * @param expectedText The text that should be contained within the actual text.
     */
    public static void assertContains(String actualText, String expectedText) {
        Assert.assertTrue(actualText.contains(expectedText),
                String.format("Assertion failed. Expected to contain: '%s', but Actual: '%s'", expectedText, actualText));
    }
    
    /**
     * Asserts that two strings are equal. On failure, provides a detailed message
     * with the same format as assertContains.
     *
     * @param actualText   The actual text retrieved from the application.
     * @param expectedText The expected text.
     */
    public static void assertEquals(String actualText, String expectedText) {
        Assert.assertEquals(actualText, expectedText,
                String.format("Assertion failed. Expected: '%s', but Actual: '%s'", expectedText, actualText));
    }
}