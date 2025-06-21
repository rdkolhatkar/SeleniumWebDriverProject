package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SeleniumWaits {

    public static void main(String[] args) {

        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // ********* IMPLICIT WAIT DEMO *********
        // Set an implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open the automation practice website
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Find the element that is available instantly using implicit wait
        WebElement radioButton = driver.findElement(By.cssSelector("input[value='radio1']"));
        radioButton.click(); // Click the radio button

        // ********* EXPLICIT WAIT DEMO *********

        // Click the "Display" button to make a hidden element visible after a delay
        WebElement displayButton = driver.findElement(By.id("display-button"));
        displayButton.click();

        // Create an explicit wait object with a max timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the text box with id 'displayed-text' is visible
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("displayed-text")));

        // Once it's visible, enter some text
        textBox.sendKeys("Hello, I waited for you!");

        // Optional: Pause to see the result
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ********* FLUENT WAIT DEMO *********
        // This section demonstrates FluentWait without affecting the existing code

        // Click the "Hide" button to hide the text box and then "Display" again to re-show it
        WebElement hideButton = driver.findElement(By.id("hide-button"));
        hideButton.click();

        // Wait a bit and click Display again to make it reappear with delay
        try {
            Thread.sleep(1000);  // Just to simulate some action delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayButton.click();

        // Create a FluentWait instance with custom polling and timeout
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))          // Total timeout of 15 seconds
                .pollingEvery(Duration.ofSeconds(2))          // Poll every 2 seconds
                .ignoring(NoSuchElementException.class);      // Ignore NoSuchElementException

        // Wait until the text box becomes visible again using FluentWait
        WebElement fluentWaitTextBox = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.id("displayed-text"));
                if (element.isDisplayed()) {
                    System.out.println("Element is visible now!");
                    return element;
                } else {
                    System.out.println("Waiting for element to become visible...");
                    return null;
                }
            }
        });

        // Interact with the element after FluentWait finds it
        fluentWaitTextBox.clear();
        fluentWaitTextBox.sendKeys("Hello from FluentWait!");

        // Optional: Pause to see the result
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

}
