package com.ratnakar.framework.PageObjects.BaseTest;


import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


public class EcommerceWebTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver automatically using WebDriverManager.
        // It downloads the right version if not available and configures system property internally.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Giving global timeouts
        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Maximizing the window
        driver.manage().window().maximize();

        // Creating Object of LoginPage
        EcommerceWebLoginPage loginPage = new EcommerceWebLoginPage(driver);

        // Navigating to Ecommerce Web
        loginPage.navigateTo();

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