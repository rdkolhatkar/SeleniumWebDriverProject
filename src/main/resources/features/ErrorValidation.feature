@ErrorValidations
Feature: Ecommerce Login Error Validation

  @EcommerceErrorChecks
  Scenario Outline: Validate error message for incorrect login credentials
    Given I am on the login page
    When I login with invalid credentials "<userEmail>" and "<password>"
    Then I should see an error message "Incorrect email or password."

    Examples:
      | userEmail                      | password    |
      | ratnakarkolhatkar@gmail.com    | Abcdefg1234 |
      | testuser@example.com           | wrongpass   |