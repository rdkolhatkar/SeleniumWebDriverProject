package com.ratnakar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicUiTest {
    public static void main(String[] args) {
        /*
        // Creating a WebDriver Instance for Chrome Driver
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
        // driver.quit();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
         */

        /*
        // Creating a WebDriver Instance for Firefox Driver
        WebDriver driver = new FirefoxDriver();
        driver.get("https://rahulshettyacademy.com");
        driver.getTitle();
        driver.close();
        */

        // Creating a WebDriver Instance for Edge Driver
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com");
        driver.getTitle();
        driver.close();

    }
}
