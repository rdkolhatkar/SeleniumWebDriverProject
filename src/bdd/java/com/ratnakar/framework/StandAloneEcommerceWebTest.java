package com.ratnakar.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

public class StandAloneEcommerceWebTest {
    @Test
    public void EcommerceWebAppTest() throws InterruptedException {
        // Setup ChromeDriver automatically using WebDriverManager.
        // It downloads the right version if not available and configures system property internally.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Giving global timeouts
        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Maximizing the window
        driver.manage().window().maximize();
        // Login to Ecommerce App
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("ratnakarkolhatkar@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ratanlord@1409");
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);
        // Get title of the page after login is successful
        System.out.println(driver.getTitle());
        // Retrieve the list of all products present on the website
        // Then find the product with name as "ZARA COAT 3"
        String productName = "ZARA COAT 3";
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream().filter(
                s -> s.findElement(By.cssSelector("b")).getText().equals(productName)
        ).findFirst().orElse(null);
        // Now we have to click on add to cart button
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        // Now after add to cart is completed on toast message will be displayed on the bottom of the page
        // After adding item to cart we have to wait until that toast message is showing up on the screen
        // Explicit Wait
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        // Wait till toast message disappears
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        // now click on the cart button
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        // Now validate if product is present in the cart items
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        /*
        In Java Stream API, anyMatch() is a terminal operation that tests whether at least one element in the stream matches a given predicate (condition).
        Return type → boolean
        Stops early → As soon as it finds the first matching element, it short-circuits and returns true.
        If no element matches → Returns false.
        */
        boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        // Now we have to click on checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();
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
