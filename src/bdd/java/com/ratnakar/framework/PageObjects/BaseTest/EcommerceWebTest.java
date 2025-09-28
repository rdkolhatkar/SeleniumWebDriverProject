package com.ratnakar.framework.PageObjects.BaseTest;


import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

        // Defining explicit wait
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

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

        // Calling the ProductCatalogue class

        List<WebElement> products = ecommerceWebProductCatalogue.getProductsList();

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
        ecommerceWebCartPage.goToCheckOut();

        // Now we have to fill the card details form for submitting the order
        // Select the country dropdown
        Actions actions = new Actions(driver);
        actions.sendKeys(
                driver.findElement(By.cssSelector("[placeholder='Select Country']")),
                "India"
        ).build().perform();
        // After sending keys we have to wait till our dropdown list is visible
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        // Now place the order by clicking on place order button
        driver.findElement(By.cssSelector(".action__submit")).click();
        Thread.sleep(2000);
        // After submitting order you will see the thank you page
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        // closing the browser
        driver.quit();
    }
}
