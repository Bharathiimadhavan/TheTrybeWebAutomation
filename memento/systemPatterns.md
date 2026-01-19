# System Patterns: TheTrybe Web Automation

## 1. System Architecture
The framework follows a classic layered architecture for test automation:

```mermaid
graph TD
    A[Feature Files (Gherkin)] --> B[Step Definitions];
    B --> C[Page Objects];
    C --> D[Base Page/Framework Utilities];
    D --> E[Selenium WebDriver];
    E --> F[Web Browser];
```

- **Feature Files:** Define test scenarios in a human-readable format.
- **Step Definitions:** Implement the logic for each step in the Gherkin scenarios.
- **Page Objects:** Encapsulate the UI elements and interactions of each page, promoting reusability and maintainability.
- **Framework Utilities:** Provide core services like driver management, configuration reading, and logging.

## 2. Key Technical Decisions & Patterns
- **Page Object Model (POM):** This is the core design pattern. Each page of the application has a corresponding Java class that holds its locators and methods to interact with it. This separates test logic from UI interaction logic.
- **Locator Strategy:** Locators are stored in external `.json` files (`src/main/resources/locators`). This decouples them from the page object code, making them easier to update. A `LocatorManager` utility is used to read these files.
- **Driver Management:** A `BaseDriver` class uses a `ThreadLocal` `WebDriver` instance. This ensures thread safety, allowing for parallel test execution in the future.
- **Behavior-Driven Development (BDD):** Cucumber is used to write tests in a Gherkin `Given-When-Then` format, making tests more descriptive and business-focused.
- **Configuration Management:** A `ConfigReader` class loads environment-specific settings (like URLs) from `.properties` files.
- **Robust Interaction Model:** Core interaction methods (`click`, `jsClick`, `waitForElement...`) are centralized in `BasePage.java`. These methods include automatic scrolling and a resilient, just-in-time popup handler (`handleJoinPopup`) to ensure test stability against intermittent UI elements.
- **Soft Assertion Framework:** The project uses a custom `AssertHelper` built around TestNG's `SoftAssert`. All validations should use `AssertHelper` methods (`assertTrue`, `assertEquals`, etc.) to prevent silent failures and ensure all test steps are executed, with a consolidated error report at the end of the scenario.