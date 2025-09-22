package com.ratnakar.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class StandAloneEcommerceWebTest {
    public static void main(String[] args) {
        // Setup ChromeDriver automatically using WebDriverManager.
        // It downloads the right version if not available and configures system property internally.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Giving global timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Login to Ecommerce App
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("ratnakarkolhatkar@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ratanlord@1409");
        driver.findElement(By.id("login")).click();
        // Get title of the page after login is successful
        System.out.println(driver.getTitle());
        // Retrieve the list of all products present on the website
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


    }
}
