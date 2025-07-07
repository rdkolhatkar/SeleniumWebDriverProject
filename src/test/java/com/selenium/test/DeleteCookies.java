package com.selenium.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteCookies {

    @Test
    public void deleteBrowserCookies(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies(); //.manage().deleteAllCookies() thia method will delete all existing cookies in the browser
        //driver.manage().deleteCookieNamed("browsAction"); //.manage().deleteCookieNamed("Name of the browser cookie") this method will delete only specific cookie with specific name

    }
}
