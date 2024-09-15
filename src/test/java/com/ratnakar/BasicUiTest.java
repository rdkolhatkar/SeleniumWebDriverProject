package com.ratnakar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicUiTest {
    public static void main(String[] args) {
        // Creating a WebDriver Instance
        WebDriver driver =new ChromeDriver();
        // Invoking the URL
        driver.get("https://rahulshettyacademy.com");
        // Method to get the Title of the page
        driver.getTitle();
        // Method to get the Current Url of the WebPage that is displayed on the browser
        driver.getCurrentUrl();
        // Method to close the Current browser window
        driver.close();
        // Method to close all the associated browser windows opened by selenium code
        driver.quit();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
}
