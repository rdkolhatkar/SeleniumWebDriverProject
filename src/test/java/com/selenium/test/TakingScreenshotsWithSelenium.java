package com.selenium.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class TakingScreenshotsWithSelenium {

    @Test
    public void captureScreenshots() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        // Below code will allow us to take the Screenshot of the Web Page
        // Casting the driver to take the screenshot with this method ((TakesScreenshot)driver).getScreenshotAs()
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now we have to copy the screenshot from source object to our local machine
        // For that we will use the FileUtils
        FileUtils.copyFile(screenShot, new File("src/main/resources/Screenshots/GreenKart.png"));
    }
}
