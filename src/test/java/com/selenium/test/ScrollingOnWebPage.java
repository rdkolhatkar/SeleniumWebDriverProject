package com.selenium.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    }

}
