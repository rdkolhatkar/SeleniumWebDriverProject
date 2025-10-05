package com.ratnakar.framework.PageObjects.TestUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {
    WebDriver driver;

    public CaptureScreenshot(WebDriver driver) {
        this.driver = driver;
    }

    public File getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File("src/bdd/resources/TestScreenshots/"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return file;
    }
}
