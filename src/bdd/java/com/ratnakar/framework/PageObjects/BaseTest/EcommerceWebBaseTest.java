package com.ratnakar.framework.PageObjects.BaseTest;

import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class EcommerceWebBaseTest {

    public WebDriver driver;
    public EcommerceWebLoginPage loginPage;
    
    public WebDriver initializeDriver() throws IOException {
        // Here to create the Global Properties We have to use Java Class called Properties
        // Below Java code will helps us to read the configurations from the GlobalTestConfigurations.properties
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/bdd/resources/GlobalTestConfigurations.properties");
        properties.load(fileInputStream);
        // Now we can fetch the property based on the key value from the GlobalTestConfigurations.properties
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException(
                    "Browser Name is not defined in GlobalTestConfigurations.properties: " + browserName
            );
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public EcommerceWebLoginPage launchEcommerceWebApp() throws IOException {
        // Calling the Method initializeDriver()
        driver = initializeDriver();
        // Creating Object of LoginPage
        loginPage = new EcommerceWebLoginPage(driver);
        // Navigating to Ecommerce Web
        loginPage.navigateTo();
        return loginPage;
    }

    @AfterMethod
    public void tearDownBrowser(){
        // closing the browser after test is completed
        driver.quit();
    }
}
