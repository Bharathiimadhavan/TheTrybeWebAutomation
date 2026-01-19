@plp
Feature: Product Listing Page
  As a user, I want to navigate to different product categories and verify the page content.

  @plp-categories
  Scenario Outline: PLP_001 Validate category navigation and page heading
    Given user is on home page
    When user click on the "<category>" category link
    Then user should be on the "<category>" page with the URL containing "<url>"
    And the page heading should be "<heading>"

    Examples:
      | category    | url                                   | heading                |
      | New         | /kids-new-arrivals.html               | Kids New Arrivals      |
      | Sport       | /kids-shoes/sport.html                | Sport                  |
      | School      | /kids-shoes/kids-school-shoes.html    | Kids School Shoes      |
      | Style       | /kids-shoes/style.html                | Style                  |
      | Size        | /kids-shoes.html                      | Kids Shoes             |
      | Accessories | /accessories.html                     | Accessories            |
      | Brands      | /our-brands                           | Brands                 |
      | Sale        | /trybe-sale.html                      | Sale                   |

@plp-categories-single-session
  Scenario: PLP_002 Validate category navigation and page heading in a single session
    Given user is on home page
    When user clicks on each category and validates the page content
      | category    | url                                   | heading                |
      | New         | /kids-new-arrivals.html               | Kids New Arrivals      |
      | Sport       | /kids-shoes/sport.html                | Sport                  |
      | school      | /kids-shoes/kids-school-shoes.html    | Kids School Shoes      |
      | Style       | /kids-shoes/style.html                | Style                  |
      | Size        | /kids-shoes.html                      | Kids Shoes             |
      | Accessories | /accessories.html                     | Accessories            |
      | brands      | /our-brands                           | Brands                 |
      | Sale        | /trybe-sale.html                      | Sale                   |