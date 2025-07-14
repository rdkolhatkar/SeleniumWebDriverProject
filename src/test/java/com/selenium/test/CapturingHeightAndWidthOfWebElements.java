package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class CapturingHeightAndWidthOfWebElements {
    @Test
    public void dimensionsOfWebElementsForUxHeightWidthValidation(){
        // From selenium 4 we can calculate the width and height of any web element like TextBox, Button, CheckBox, Alerts etc.
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        // From selenium 4 we can open different links in multiple windows and tabs
        // Below code is used for opening the new Window for different Url
        driver.switchTo().newWindow(WindowType.WINDOW);
        // Now we have to change our driver scope to the new Window
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> windowIterator = windowHandles.iterator();
        String parentWindowId = windowIterator.next();
        String childWindowId = windowIterator.next();
        driver.switchTo().window(childWindowId);
        // Now our selenium driver focus on the new window let's initialize new Url
        driver.get("https://rahulshettyacademy.com/");
        // Now we will grab the course title for First course from this new link "https://rahulshettyacademy.com/"
        String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']")).get(1).getText();
        // Now we need to switch the scope of the driver to the original parent window
        driver.switchTo().window(parentWindowId);
        WebElement elementName = driver.findElement(By.cssSelector("[name='name']"));
        elementName.sendKeys(courseName);
        //To get Height & Width of a web element we use the below code
        System.out.println("Height of the name Text Box is "+elementName.getRect().getDimension().getHeight());
        System.out.println("Width of the name Text Box is "+elementName.getRect().getDimension().getWidth());
        driver.quit();
    }
}
