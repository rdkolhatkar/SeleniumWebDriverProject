package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {

    @Test
    public void mouseActions(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        Actions actions = new Actions(driver); // in selenium Actions class is used for mouse actions
        WebElement move = driver.findElement(By.cssSelector("a[id='nav-link-accountList']"));
        actions.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform(); //.click().keyDown(Keys.SHIFT).sendKeys() this method is used for press and hold the keyboard SHIFT Key, we can use same function for other keyboard keys
        //.doubleClick() method will double click on the test "HELLO" and select that text
                // Move to specific element
         actions.moveToElement(move).contextClick().build().perform(); //contextClick() method is used for doing the right click on the targeted element


    }
}
