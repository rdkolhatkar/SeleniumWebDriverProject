package com.ratnakar.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAloneEcommerceWebTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver automatically using WebDriverManager.
        // It downloads the right version if not available and configures system property internally.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Giving global timeouts
        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream().filter(
                s -> s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")
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

        // closing the browser
        driver.quit();
    }
}
