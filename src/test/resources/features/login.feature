Feature: Login functionality
  As a user of the SauceDemo store
  I want to log in with my credentials
  So that I can access the product inventory

  Background:
    Given I am on the login page

  Scenario: Successful login with valid credentials
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page
    And the page title should be "Products"

  Scenario: Login fails for a locked-out user
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message containing "locked out"
    
     Scenario Outline: Login fails with invalid credential combinations
    When I login with username "<username>" and password "<password>"
    Then I should see an error message containing "<error>"

    Examples:
      | username      | password       | error     |
      | invalid_user  | wrong_password | username  |