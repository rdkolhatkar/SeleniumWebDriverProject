package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class InvokingMultipleWindowsAndTabs {

    @Test
    public void invokingMultipleTabs() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        // From selenium 4 we can open different links in multiple windows and tabs
        // Below code is used for opening the new Tab for different Url
        driver.switchTo().newWindow(WindowType.TAB);
        // Now we have to change our driver scope to the new tab
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> tabIterator = windowHandles.iterator();
        String parentTabId = tabIterator.next();
        String childTabId = tabIterator.next();
        driver.switchTo().window(childTabId);
        // Now our selenium driver focus on the new tab let's initialize new Url
        driver.get("https://rahulshettyacademy.com/");
        // Now we will grab the course title for First course from this new link "https://rahulshettyacademy.com/"
        String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']")).get(1).getText();
        // Now we need to switch the scope of the driver to the original parent tab
        driver.switchTo().window(parentTabId);
        driver.findElement(By.cssSelector("[name='name']")).sendKeys(courseName);
        driver.quit();
    }

    @Test
    public void invokingMultipleWindows() {
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
        driver.findElement(By.cssSelector("[name='name']")).sendKeys(courseName);
        driver.quit();
    }
}
