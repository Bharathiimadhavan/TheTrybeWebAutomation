@regression
Feature: Product Display Page

  @pdp
  Scenario Outline: Search the product and go to PDP and select size and add to cart and verify in the cart
    Given user is on home page
    When user searches for "<productName>"
    Then clicks the first product
    And validate the PDP page is displayed
    And user selects size "<size>"
    And clicks on Add to Cart button
    And clicks on Go to Bag button
    And the product "<productName>" with size "<size>" should be in the cart

    Examples:
      | productName                 | size |
      | Copa Jnr Ap White Smooth    |  12  |