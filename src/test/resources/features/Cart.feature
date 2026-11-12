@regression @cart
Feature: Shopping cart
  As a logged in customer
  I want to add and remove products in my cart
  So that I can buy only what I need

  Background:
    Given I am logged in

  @smoke
  Scenario: Add a single product to the cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart icon should show 1
    And the cart should contain "Sauce Labs Backpack"

  Scenario: Add several products to the cart
    When I add the following products to the cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Fleece Jacket |
    Then the cart icon should show 3

  Scenario: Remove a product from the products page
    Given I add "Sauce Labs Bike Light" to the cart
    When I remove "Sauce Labs Bike Light" from the cart
    Then the cart icon should show 0
