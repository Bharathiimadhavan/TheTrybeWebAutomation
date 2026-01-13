@regression
Feature: Wishlist

  @wishlist @passed
  Scenario Outline: Wishlist_001 Add first searched product to wishlist from PLP
    Given user is on home page
    When user searches for "<productName>"
    And adds first product to wishlist from PLP
    And navigates to wishlist page
    Then product "<productName>" should appear in the wishlist page

    Examples:
      | productName              |
      | Copa Jnr Ap White Smooth |

  @wishlist
  Scenario Outline: Wishlist_002 Open the searched product and wishlist without size from PDP 
    Given user is on home page
    When user searches for "<productName>"
    Then clicks the first product
    And wishlist the product from PDP
    #And adds first product to wishlist from PLP
    And navigates to wishlist page
    And product "<productName>" should appear in the wishlist page

    Examples:
      | productName              |
      | Copa Jnr Ap White Smooth |

  @wishlist @size_selection
  Scenario Outline: Wishlist_003 Wishlist a product with a specific size
    Given user is on home page
    When user searches for "<productName>"
    Then clicks the first product
    And user selects size "<size>"
    And wishlist the product from PDP
    #And adds first product to wishlist from PLP
    And navigates to wishlist page
    Then product "<productName>" should appear in the wishlist page with size "<size>"

    Examples:
      | productName              | size |
      | Copa Jnr Ap White Smooth | 2    |
