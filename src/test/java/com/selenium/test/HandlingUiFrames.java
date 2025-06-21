package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HandlingUiFrames {

    @Test
    public void handlingUiFrames(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://jqueryui.com/droppable/");
        // Frames are sub-windows or sections within a single browser window. Frames are the part of the website which is not an Integral part of Web Element but they are hosted in our website as a part of external service.
        // To handle frames in the selenium below syntax is used
        System.out.println(driver.findElements(By.tagName("iframe")).size()); // Counting the number of iframes present in the WebPage
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));
        driver.findElement(By.id("draggable")).click();

        //Drag and Drop Concept in the UI Web page and IFrames
        Actions uiActions = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        uiActions.dragAndDrop(source, target).build().perform();

        //Now we have to go out of the frame and come back to default web page. For this we use the below method
        driver.switchTo().defaultContent();


    }
}
