package com.selenium.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsHandlingSetup {
    WebDriver driver = new ChromeDriver();
    @Test
    public void checkWindowsAlerts() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        String text = "Rahul";
        driver.findElement(By.id("name")).sendKeys(text);
        driver.findElement(By.cssSelector("[id='alertbtn']")).click();
        /*
        driver.switchTo().alert() is a selenium method used for Handling the Alerts on Web Page
        */
        driver.switchTo().alert().getText();
        Thread.sleep(1000);

        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept(); // This method is used for clicking on OK button on the Alert

        driver.findElement(By.id("confirmbtn")).click();
        Thread.sleep(1000);

        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss(); // This method is used for Cancels and NO conditions present in the alerts

        Thread.sleep(1000);
        driver.close();

    }
}
