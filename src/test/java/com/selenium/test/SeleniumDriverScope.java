package com.selenium.test;


import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class SeleniumDriverScope {

    @Test
    public void seleniumWebDriverScope() throws InterruptedException {
        //Calculate the total number of count of Links present in the webpage
        WebDriver driver = new ChromeDriver();
        driver.get("http://qaclickacademy.com/practice.php");
        driver.findElements(By.tagName("a")).size();
        System.out.println(driver.findElements(By.tagName("a")).size());
        //Calculate the number of links present in the footer section of web page
        // For this above requirement we have to create the driver sub sets or mini driver, having limited scope to footer only
        WebElement footerDriver = driver.findElement(By.id("gf-BIG")); // limiting webdriver scope
        footerDriver.findElements(By.tagName("a")).size();
        System.out.println(footerDriver.findElements(By.tagName("a")).size());
        //Calculate the number of links on the first column of the footer
        WebElement columnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        columnDriver.findElements(By.tagName("a")).size();
        System.out.println(columnDriver.findElements(By.tagName("a")).size());
        //Click on each link in the first column and check if page is opening when we click on links and also get the title of each page
        for(int i=1; i<columnDriver.findElements(By.tagName("a")).size(); i++){
            // When we click on each link it will navigate to new web page in the same tab, we have to make sure all links are opening in the new tab and our base browser tab remains the same to click on next link
            // To solve this problem we have to press Ctrl + link to open link in new tab, for that we will use below method
            String clickOnLinkNewTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
            columnDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLinkNewTab); //.sendKeys(clickOnLinkNewTab) this method is used for initializing Keyboard events
            // As 4 links are opening in four new tabs we have to switch to every child window and get the tile by using WindowHandles()
            Thread.sleep(5000L);
            Set<String> childWindows = driver.getWindowHandles();
            Iterator<String> iterator = childWindows.iterator();
            while(iterator.hasNext()){
                driver.switchTo().window(iterator.next());
                System.out.println(driver.getTitle());
            }
        }

    }


}
