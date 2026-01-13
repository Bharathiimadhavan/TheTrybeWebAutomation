# Technical Context: TheTrybe Web Automation

## 1. Core Technologies
- **Programming Language:** Java (Version 21)
- **Build Tool:** Apache Maven
- **Test Runner:** TestNG

## 2. Key Dependencies
- **Browser Automation:**
  - `org.seleniumhq.selenium:selenium-java` (v4.25.0): The primary library for controlling web browsers.
- **BDD Framework:**
  - `io.cucumber:cucumber-java` (v7.15.0): For writing test steps in Java.
  - `io.cucumber:cucumber-testng` (v7.15.0): To run Cucumber scenarios with TestNG.
- **Reporting:**
  - `com.aventstack:extentreports` & `tech.grasshopper:extentreports-cucumber7-adapter`: For generating detailed HTML test reports.
  - `io.qameta.allure:allure-cucumber7-jvm`: For generating Allure reports.
- **Logging:**
  - `org.apache.logging.log4j:log4j-core` & `log4j-api`: For application logging.
- **Utilities:**
  - `com.fasterxml.jackson.core:jackson-databind`: For parsing JSON files (used for locators).
  - `commons-io:commons-io`: Provides general file I/O utilities.

## 3. Development Setup
- The project is a standard Maven project.
- Tests can be executed by running `mvn clean install` or by using the `testng.xml` suite file.
- The `maven-surefire-plugin` is configured to run the tests.
- Environment-specific configurations are managed in `src/main/resources/config.*.properties`.

## 4. Debugging
The framework supports multiple methods for running and debugging tests.

### 1. From the IDE (Recommended for Development)
- **To Run/Debug**: Open a `.feature` file and click the play icon next to a Scenario or Scenario Outline.
- **Use Case**: This is the most convenient method for developing and debugging individual scenarios.

### 2. From the Command Line (for Test Suites)
- **Command**: `mvn clean test -Dcucumber.filter.tags="@your_tag_here"`
- **Use Case**: Ideal for running specific test suites (e.g., `@smoke`, `@regression`) or for CI/CD pipelines.
- **PowerShell Note**: If using PowerShell, you must wrap the tags property in single quotes: `mvn clean test '-Dcucumber.filter.tags="@your_tag_here"'`

### 3. Terminal Debugging (for Advanced Scenarios)
This project uses a Maven profile for a robust terminal debugging experience.

- **Step 1: Start the Debug Server**
  Run your test with the `debug` profile activated. This command will pause execution and wait for a debugger to connect on port 5005.

  **Standard Terminal:**
  ```bash
  mvn clean test -Dcucumber.filter.tags="@your_tag_here" -Ddebug=true
  ```

  **PowerShell:**
  ```powershell
  mvn clean test '-Dcucumber.filter.tags="@your_tag_here"' -Ddebug=true
  ```

- **Step 2: Attach the Debugger**
  - In VS Code, go to the "Run and Debug" panel.
  - Select the **"Attach to Remote Debug Server"** configuration from the dropdown.
  - Click the green play button to attach the debugger. Your breakpoints will now be active.

- **Step 3: Stop the Session**
  - **In VS Code**: Click the red 'Stop' button on the floating debug toolbar.
  - **In the Terminal**: Press `Ctrl + C` to terminate the Maven process.

## 5. Technical Constraints
- The framework is currently set up to run tests only on Google Chrome.
- WebDriver management is handled by Selenium Manager, which simplifies setup.