package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ScrollingOnWebPage {
    @Test
    public void scrollingWebElements() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        // For scrolling the web page, we have to execute the JavaScript through the selenium code.
        // To execute the JavaScript we have t use the JavaScript Executor
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        // window.scrollBy(0,500) -> this is the JavaScript command for scrolling the window by using co-ordinates, here {x = 0 and y = 500(down)}
        javascriptExecutor.executeScript("window.scrollBy(0,500)");
        // Now after scrolling the window, we have one more table inside the browser that we have to scroll
        // To scroll a specific elemental table we are using DOM(Document Object Mapping) object command in Java Script
        //document.querySelector("cssSelector").scrollTop=5000
        Thread.sleep(3000);
        javascriptExecutor.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
        // Table validation
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
       int sum = 0;
        for(int i = 0; i < values.size(); i++){
            System.out.println(Integer.parseInt(values.get(i).getText()));
            System.out.println("Addition of all the retrieved values from the table is ");
            sum = sum + Integer.parseInt(values.get(i).getText());
        }
        System.out.println(sum);
        int total = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
        Assert.assertEquals(sum, total);
    }

}
