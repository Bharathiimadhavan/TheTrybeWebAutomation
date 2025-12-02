@regression
Feature: Filters

  Scenario: Apply price filter
    Given user is on category page "Men Shoes"
    When user applies minimum price filter "200"
    Then filtered results should match price 200
