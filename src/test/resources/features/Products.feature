@regression @products
Feature: Products page
  As a logged in customer
  I want to browse and sort the products
  So that I can find what I want to buy

  Background:
    Given I am logged in

  @smoke
  Scenario: All products are displayed
    Then I should see 6 products
    And I should see the following products
      | Sauce Labs Backpack               |
      | Sauce Labs Bike Light             |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Fleece Jacket          |
      | Sauce Labs Onesie                 |
      | Test.allTheThings() T-Shirt (Red) |

  Scenario: Sort products by price from low to high
    When I sort the products by "Price (low to high)"
    Then the products should be sorted by price from low to high

  Scenario: Sort products by price from high to low
    When I sort the products by "Price (high to low)"
    Then the products should be sorted by price from high to low

  Scenario: Sort products by name from Z to A
    When I sort the products by "Name (Z to A)"
    Then the products should be sorted by name from Z to A
