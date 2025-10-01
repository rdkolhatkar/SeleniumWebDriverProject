package com.ratnakar.framework.PageObjects.ActualTest;


import com.ratnakar.framework.PageObjects.BaseTest.EcommerceWebBaseTest;
import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class EcommerceWebTest extends EcommerceWebBaseTest {

    @Test
    public void EcommerceWebApplicationTest() throws InterruptedException, IOException {

        // Launch The EcommerceWeb APP
        EcommerceWebLoginPage loginPage = launchEcommerceWebApp();
        // Calling the Methods from LoginPage for accessing Ecommerce Web Application
        EcommerceWebProductCatalogue ecommerceWebProductCatalogue = loginPage.loginToEcommerceWebApplication("ratnakarkolhatkar@gmail.com", "Ratanlord@1409");

        // Get title of the page after login is successful
        System.out.println(driver.getTitle());
        // Retrieve the list of all products present on the website
        // Then find the product with name as "ZARA COAT 3"
        String productName = "ZARA COAT 3";

        // Now we have to click on add to cart button
        ecommerceWebProductCatalogue.addProductToCart(productName);

        // now click on the cart button
        // As per inheritance concept in java child class can also access all parent class methods directly
        // As EcommerceWebProductCatalogue is child of EcommerceWebAbstractComponents
        Thread.sleep(1000);
        EcommerceWebCartPage ecommerceWebCartPage = ecommerceWebProductCatalogue.goToCartPage();

        // Cart Page Verification
        Boolean match = ecommerceWebCartPage.verifyTheDisplayedCartProducts(productName);
        Assert.assertTrue(match);

        // Now validate if product is present in the cart items
        EcommerceWebCheckoutPage ecommerceWebCheckoutPage = ecommerceWebCartPage.goToCheckOut();

        // Now we have to fill the card details form for submitting the order
        // Select the country dropdown
        ecommerceWebCheckoutPage.selectCountry("India");

        // Now we have to submit the order
        EcommerceWebOrderConfirmationPage ecommerceWebOrderConfirmationPage = ecommerceWebCheckoutPage.submitOrder();

        // After submitting order you will see the thank you page
        String confirmMessage = ecommerceWebOrderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        // closing the browser
        driver.quit();
    }
}