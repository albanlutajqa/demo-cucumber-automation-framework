@regression @checkout
Feature: Checkout
  As a logged in customer
  I want to complete a purchase
  So that I receive my products

  Background:
    Given I am logged in
    And I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bolt T-Shirt" to the cart
    And I go to the cart and click checkout

  @smoke @e2e
  Scenario: Complete a purchase end to end
    When I enter checkout information "Alban", "Lutaj" and "8001"
    Then I should see the "Checkout: Overview" page
    And the item total should equal the sum of the item prices
    And the total should equal the item total plus tax
    When I finish the order
    Then I should see the confirmation message "Thank you for your order!"

  Scenario Outline: Checkout information is required
    When I enter checkout information "<firstName>", "<lastName>" and "<postalCode>"
    Then I should see the checkout error message "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage                   |
      |           | Lutaj    | 8001       | Error: First Name is required  |
      | Alban     |          | 8001       | Error: Last Name is required   |
      | Alban     | Lutaj    |            | Error: Postal Code is required |
