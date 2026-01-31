package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FileDownloadAndUploadWithSelenium {
    @Test
    public void FileDownloadAndUpload() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        // Apply implicit wait so that our scripts can wait till download finishes
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
        // First we have to download the Excel Sheet from this website by clicking on download button
        driver.findElement(By.cssSelector("#downloadButton")).click();
        // Second we have to Edit the excel data
        // Third we have to upload the updated Excel Sheet with updated data
        WebElement uploadButton = driver.findElement(By.cssSelector("input[type='file']"));
        uploadButton.sendKeys("D:/selenium/SeleniumWebDriverProject/src/test/resources/UploadFiles/UpdatedFile.xlsx");
        // Fourth We have to wait for success message to show up and when it disappears then we can confirm Upload is Successful
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By toastLocator = By.cssSelector(".Toastify_toast-body div:nth-child(2)");
        // wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        Thread.sleep(1000);
        String toastMessageText = driver.findElement(toastLocator).getText();
        System.out.println(toastMessageText);
        Assert.assertEquals(toastMessageText, "Updated Excel Data Successfully.");
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
        // Fifth we have to verify that updated data is successfully published into the UI or not
        // Here We are updating the Price of an Apple
        String fruitName = "Apple";
        String priceColumnValue = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
        String UpdatedPrice = driver.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@id='cell-"+priceColumnValue+"-undefined']")).getText();
        Assert.assertEquals(UpdatedPrice, "400");
        driver.close();

    }
}
