@smoke @regression
Feature: Checkout flow

  Scenario: Successful checkout order placement
    Given user is on home page
    When user searches for "shoes"
    And adds first product to cart
    And proceeds to checkout
    Then order should be placed successfully
