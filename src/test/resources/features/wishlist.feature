@regression
Feature: Wishlist

  Scenario: Add first searched product to wishlist
    Given user is on home page
    When user searches for "shoes"
    And adds first product to wishlist
    Then product should appear in wishlist
