@regression @login
Feature: Login functionality
  As a customer of SauceDemo
  I want to log in with my credentials
  So that I can see the products

  @smoke
  Scenario: Successful login with valid credentials
    When I log in with valid credentials
    Then I should see the "Products" page

  Scenario: Locked out user cannot log in
    When I log in with username "locked_out_user" and password "secret_sauce"
    Then I should see the error message "Epic sadface: Sorry, this user has been locked out."

  Scenario Outline: Login fails with invalid or missing credentials
    When I log in with username "<username>" and password "<password>"
    Then I should see the error message "<errorMessage>"

    Examples:
      | username      | password     | errorMessage                                                              |
      | wrong_user    | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standard_user | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
      |               | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user |              | Epic sadface: Password is required                                        |

  Scenario: User can log out
    Given I am logged in
    When I log out
    Then I should be on the login page
