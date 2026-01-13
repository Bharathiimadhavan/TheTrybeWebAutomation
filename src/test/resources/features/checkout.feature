@smoke @regression
Feature: Checkout flow

  @addtocart
  Scenario: Successful checkout order placement
    Given user is on home page
    When user searches for "Copa Jnr Ap White Smooth"
    And clicks the first product
    And adds first product to cart
    # below things need to validate
    And navigates to wishlist page
    And product "Copa Jnr Ap White Smooth" should appear in the wishlist page
    # And adds first product to cart
    # And proceeds to checkout
    # Then order should be placed successfully
