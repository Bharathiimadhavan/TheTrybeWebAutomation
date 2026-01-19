@regression
Feature: As a user, I want to validate the cart feature.

  
  
  @cart
  Scenario Outline: Cart_001 Add a product to cart with a specific size
    Given user is on home page
    When user searches for "<productName>"
    Then clicks the first product
    And user selects size "<size>"
    And user clicks on Add to Bag button
    And validate mini-cart is displaying
    And user clicks on Go to Bag button
    And validate the cart page is displaying

    Examples:
      | productName              | size |
      | Copa Jnr Ap White Smooth | 2    |
