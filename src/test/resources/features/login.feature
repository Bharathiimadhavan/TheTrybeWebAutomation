@smoke @regression
Feature: Login

  @login @passed
  Scenario: Login with valid credentials 
    Given user is on home page
    When user opens login page
    And user logs in using valid credentials
    Then user should be logged in successfully
    And user logs out successfully
    # passed
