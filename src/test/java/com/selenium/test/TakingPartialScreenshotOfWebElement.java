package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class TakingPartialScreenshotOfWebElement {
    @Test
    public void takingPartialScreenShotOfWebElement() throws IOException {
        // Taking Partial ScreenShot of Web Element is a new feature introduced in selenium 4
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
        // Now we have to capture ScreenShot of the Partial Element called "elementName"
        // For taking Partial ScreenShot we will use below code
        File screenShotFile = elementName.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile, new File("src/main/resources/Screenshots/partialElementalScreenshot.png"));
        driver.quit();
    }
}
