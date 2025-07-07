package com.selenium.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HandlingBrokenLinks {
    @Test
    public void handlingBrokenLink() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        // Broken link means, a link present on the UI which gives Page Not Found Error when user clicks on it to navigate to that functionality.
        // There are some Java methods that will be calling all the existing routes present on the webpage which are linked with HTML Tag href, for Example: <a href="https://rahulshettyacademy.com/brokenlink">Broken Link</a>
        // Step-1: Get all urls tied up with link attribute href using selenium
        // If http status code of the url is greater than 400 then we call that link as broken
        String url = driver.findElement(By.cssSelector("a[href*='brokenlink']")).getAttribute("href");
        // Java method to invoke url or call any http link
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("HEAD"); //.setRequestMethod("HEAD") is used to post the http or https links with their endpoints
        connection.connect(); //.connect() method is used to invoke the route on the browser to establish the connection
        int httpResponseCode = connection.getResponseCode(); //.getResponseCode() method is used to check the http response of a route
        System.out.println(httpResponseCode);
    }

    @Test
    public void handlingMultipleLinksToSearchBrokenLink() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        List<WebElement> allLinks = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        for(WebElement link : allLinks){
            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int httpResponseCode = connection.getResponseCode();
            System.out.println(httpResponseCode);
            if(httpResponseCode > 400){
                System.out.println("The Link With Text "+link.getText()+" is broken with code "+httpResponseCode);
                Assert.assertTrue(false);
            }
        }
    }

    @Test
    public void validatingBrokenLinksWithHardAssertions() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        List<WebElement> allLinks = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        for(WebElement link : allLinks){
            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int httpResponseCode = connection.getResponseCode();
            System.out.println("Url : "+link.getText()+" HTTP Status Code: "+httpResponseCode);
            // Hard Assertion -> In this test will fail immediately after it found any broken link or assertion conditions fail, test will no go further to check the remaining links
            Assert.assertTrue("The Link With Text "+link.getText()+" is broken with code "+httpResponseCode,httpResponseCode < 400);
        }
    }

    @Test
    public void validatingBrokenLinksWithSoftAssertions() throws IOException {
        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        List<WebElement> allLinks = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        for(WebElement link : allLinks){
            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int httpResponseCode = connection.getResponseCode();
            System.out.println("Url : "+link.getText()+" HTTP Status Code: "+httpResponseCode);
            // Soft Assertions -> in this test will not fail immediately after finding the broken link but it will go on to check the remaining links as well and then after all links are checked it will fail at very last
            softAssert.assertTrue(httpResponseCode < 400,"The Link With Text "+link.getText()+" is broken with code "+httpResponseCode);
        }
        // To continue our assertions for remaining links we have to use below code at the end of for loop
        // .assertAll() will catch all the assertion reports for every link
        softAssert.assertAll();
    }
}
