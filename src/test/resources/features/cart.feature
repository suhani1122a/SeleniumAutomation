Feature: Shopping cart functionality
  As a logged-in user
  I want to add products to my cart
  So that I can purchase them later

  Background:
    Given I am on the login page
    And I login with username "standard_user" and password "secret_sauce"

  Scenario: Adding a single item updates the cart count
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"

  Scenario: Adding multiple items accumulates the cart count
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart badge should show "2"