package framework;

/**
 * Custom exception to represent a collection of assertion failures.
 * This exception is intended to be thrown at the end of a test scenario
 * to report all soft assertion errors that occurred.
 */
@SuppressWarnings("serial")
public class AssertionException extends RuntimeException {

    /**
     * Constructs a new AssertionException with the specified detail message.
     *
     * @param message The detail message, which should contain a summary of all assertion failures.
     */
    public AssertionException(String message) {
        super(message);
    }
}