package com.selenium.test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadAndUploadWithSelenium {
    WebDriver driverOne;
    @BeforeMethod
    public void beforeSpecificTest(Method method) throws InterruptedException {
        if (!method.getName().equals("DownloadFileAtSpecificLocationAndUpdateTheFileForUpload")) {
            return;
        }
        System.out.println("Running setup only for DownloadFileAtSpecificLocationAndUpdateTheFileForUpload");
        // Step 1: Absolute download path (VERY IMPORTANT)
        String downloadDir = Paths.get(
                System.getProperty("user.dir"),
                "src", "test", "resources", "DownloadFiles"
        ).toAbsolutePath().toString();
        String excelPath = downloadDir + File.separator + "download.xlsx";
        // Step 2: Clean old file
        File oldFile = new File(excelPath);
        if (oldFile.exists()) {
            oldFile.delete();
        }
        // Step 3: Chrome download preferences
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadDir);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put(
                "profile.content_settings.exceptions.automatic_downloads.*.setting", 1
        );
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driverOne = new ChromeDriver(options);
        driverOne.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Step 4: Trigger download
        driverOne.get("https://rahulshettyacademy.com/upload-download-test/index.html");
        driverOne.findElement(By.id("downloadButton")).click();
        // Step 5: Wait until file exists
        File file = new File(excelPath);
        int waitTime = 0;
        while (!file.exists() && waitTime < 20) {
            Thread.sleep(1000);
            waitTime++;
        }
        if (!file.exists()) {
            throw new RuntimeException("Download failed: Excel file not found");
        }
        System.out.println("Excel file downloaded successfully!");
        // Step 6: Update Excel (Apple → price = 400)
        try (FileInputStream fis = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            Row headerRow = sheet.getRow(0);
            int fruitCol = -1;
            int priceCol = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                String header = headerRow.getCell(i).getStringCellValue();
                if (header.equalsIgnoreCase("fruit_name")) fruitCol = i;
                if (header.equalsIgnoreCase("price")) priceCol = i;
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(fruitCol).getStringCellValue().equalsIgnoreCase("Apple")) {
                    row.getCell(priceCol).setCellValue(400);
                    System.out.println("Updated Apple price to 400");
                    break;
                }
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
//        String toastMessageText = driver.findElement(toastLocator).getText();
//        System.out.println(toastMessageText);
//        Assert.assertEquals(toastMessageText, "Updated Excel Data Successfully.");
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
    @Test
    public void DownloadFileAtSpecificLocationAndUpdateTheFileForUpload() throws InterruptedException {
        // Upload updated file
        WebElement uploadButton = driverOne.findElement(By.cssSelector("input[type='file']"));
        uploadButton.sendKeys(
                "D:/selenium/SeleniumWebDriverProject/src/test/resources/UploadFiles/UpdatedFile.xlsx"
        );
        Thread.sleep(1000);
        String fruitName = "Apple";
        String priceColumnValue = driverOne.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
        String UpdatedPrice = driverOne.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@id='cell-"+priceColumnValue+"-undefined']")).getText();
        Assert.assertEquals(UpdatedPrice, "400");
        driverOne.quit();
    }

}
