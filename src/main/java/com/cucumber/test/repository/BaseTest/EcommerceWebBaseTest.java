package com.cucumber.test.repository.BaseTest;

import com.ratnakar.framework.PageObjects.LoginPage.EcommerceWebLoginPage;
import com.ratnakar.framework.PageObjects.TestUtils.JsonFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class EcommerceWebBaseTest extends JsonFileReader {

    public WebDriver driver;
    public EcommerceWebLoginPage loginPage;
    
    public WebDriver initializeDriver() throws IOException {
        // Here to create the Global Properties We have to use Java Class called Properties
        // Below Java code will helps us to read the configurations from the GlobalTestConfigurations.properties
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/bdd/resources/GlobalTestConfigurations.properties");
        properties.load(fileInputStream);
        // Now we can fetch the property based on the key value from the GlobalTestConfigurations.properties
        // 🧩 Ternary operator syntax:
        // condition ? valueIfTrue : valueIfFalse
        // Read browser name: either from system property or from properties file
        String browserName = System.getProperty("browserName") != null
                ? System.getProperty("browserName")
                : properties.getProperty("browser");

        // Read headless mode flag (default = false)
        String headlessProperty = System.getProperty("headless") != null
                ? System.getProperty("headless")
                : properties.getProperty("headless", "false");

        boolean isHeadless = Boolean.parseBoolean(headlessProperty.trim());

        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name is not provided in configuration file or system property.");
        }

        browserName = browserName.trim().toLowerCase();
        System.out.println("Launching browser: " + browserName + " | Headless: " + isHeadless);

        // ================================
        // Chrome Browser
        // ================================
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);

            // ================================
            // Firefox Browser
            // ================================
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);

            // ================================
            // Edge Browser
            // ================================
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver(options);

        } else {
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        // Common setup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1440, 900));
        if (!isHeadless) {
            driver.manage().window().maximize();
        }

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
