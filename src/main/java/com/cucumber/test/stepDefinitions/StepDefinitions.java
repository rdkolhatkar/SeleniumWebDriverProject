package com.cucumber.test.stepDefinitions;

import com.cucumber.test.repository.BaseTest.EcommerceWebBaseTest;
import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitions extends EcommerceWebBaseTest {
    public EcommerceWebLoginPage landingPage;
    public EcommerceWebProductCatalogue productCataloguePage;
    public EcommerceWebCartPage cartPage;
    public  EcommerceWebCheckoutPage checkoutPage;
    public  EcommerceWebOrderConfirmationPage orderConfirmationPage;

    @Given("User is on the Ecommerce web application login page")
    public void user_is_on_the_ecommerce_web_application_login_page() throws IOException {
        landingPage = launchEcommerceWebApp();
    }

    // In the Cucumber step definition, {.+} is a regular expression (regex) pattern that means, one or more of any character, used to capture any non-empty text from the step.
    // ^ in the regex marks the start of the line or string, ensuring the step matches only if it begins exactly with that text.
    // $ in the regex marks the end of the line or string, ensuring the step matches only if it ends exactly after the second {.+} part.
    @Given("User has logged into the Ecommerce web application with {string} and {string}")
    public void user_has_logged_into_the_ecommerce_web_application_with_and(String username, String password) {
        productCataloguePage = loginPage.loginToEcommerceWebApplication(username, password);
    }

    @When("User adds the product {string} to the cart")
    public void user_adds_the_product_to_the_cart(String productName) throws InterruptedException {
        System.out.println(driver.getTitle());
        productCataloguePage.addProductToCart(productName);
        Thread.sleep(3000);
        cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.verifyTheDisplayedCartProducts(productName);
        Assert.assertTrue(match);
    }

    @And("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        checkoutPage = cartPage.goToCheckOut();
    }

    @And("User submits the order")
    public void user_submits_the_order() {
        checkoutPage.selectCountry("India");
        orderConfirmationPage = checkoutPage.submitOrder();
    }

    // Do not use (regex) pattern {.+} if your data is not coming from the Examples section of the feature file
    @Then("User should see the order confirmation message {string}")
    public void user_should_see_the_order_confirmation_message(String message) {
        String confirmMessage = orderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
        driver.quit();
    }
}
