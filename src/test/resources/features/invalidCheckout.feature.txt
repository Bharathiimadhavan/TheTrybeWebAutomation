@regression
Feature: Invalid Checkout

  Scenario: Try placing order without filling billing form
    Given user has product in cart
    When user tries to place order without filling billing information
    Then validation errors must appear
