package com.ratnakar.framework.PageObjects.TestUtils;

import com.ratnakar.framework.PageObjects.BaseTest.EcommerceWebBaseTest;
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

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        String fileDestination = "src/bdd/resources/TestScreenshots/"+testCaseName+".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(fileDestination);
        FileUtils.copyFile(source, file);
        return fileDestination;
    }
}
