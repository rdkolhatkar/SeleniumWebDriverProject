@EcommerceWebApp
Feature: Validate the Submit Order Functionality of the Ecommerce Web Application

  # 'Background' This cucumber keyword defines common setup steps that run before each scenario in a feature file
  Background:
    Given User is on the Ecommerce web application login page

  @Acceptance
  Scenario Outline: Verify Ecommerce Web Application is able to submit the order successfully
    Given User has logged into the Ecommerce web application with "<username>" and "<password>"
    When User adds the product "<productName>" to the cart
    And User proceeds to checkout
    And User submits the order
    Then User should see the order confirmation message "THANKYOU FOR THE ORDER."

    Examples:
      | username                    | password       | productName |
      | ratnakarkolhatkar@gmail.com | Ratanlord@1409 | ZARA COAT 3 |
