package com.selenium.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class GenerateExtentReports {

    // Create a global ExtentReports object to hold the entire test report instance
    ExtentReports extentReports;

    /**
     * This method runs once before any test (@BeforeTest).
     * It sets up and configures the Extent Reports system.
     */
    @BeforeTest
    public void preConfiguration() {

        // Specify the path where the Extent Report HTML file will be generated
        String reportsPath = "src/test/resources/TestReports/index.html";

        // Create an instance of ExtentSparkReporter (responsible for the HTML UI report)
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportsPath);

        // Configure basic information for the report
        reporter.config().setReportName("Ecommerce Web Application Test Results"); // Shown on the report page
        reporter.config().setDocumentTitle("Test Results"); // Title of the browser tab or HTML document

        // Create an ExtentReports object — the main class that controls the whole reporting system
        extentReports = new ExtentReports();

        // Attach the reporter (HTML generator) to the ExtentReports object
        extentReports.attachReporter(reporter);

        // Add environment or tester information (metadata) to the report
        extentReports.setSystemInfo("Tester", "Ratnakar Kolhatkar");
    }

    /**
     * This is your main test method where the Selenium automation runs.
     * The Extent Report captures its execution logs and results.
     */
    @Test
    public void ecomerceWebAppStandaloneTest() throws InterruptedException {

        // Create a test instance inside the report with a descriptive test name
        ExtentTest extentTest = extentReports.createTest("Ecommerce Web App Standalone Test");

        // Setup ChromeDriver automatically using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Implicit wait for 10 seconds and maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to the Ecommerce App
        driver.get("https://rahulshettyacademy.com/client");
        extentTest.info("Navigated to Ecommerce Web Application.");

        // Login to the application
        driver.findElement(By.id("userEmail")).sendKeys("ratnakarkolhatkar@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ratanlord@1409");
        driver.findElement(By.id("login")).click();
        extentTest.info("Logged into the application successfully.");
        Thread.sleep(3000);

        // Print and log the page title after login
        System.out.println(driver.getTitle());
        extentTest.info("Page title after login: " + driver.getTitle());

        // Select and add a product to the cart
        String productName = "ZARA COAT 3";
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        extentTest.info(productName + " added to cart.");

        // Use explicit wait for toast message visibility and invisibility
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // Open the shopping cart
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        extentTest.info("Navigated to Cart.");

        // Validate that the product is present in the cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        extentTest.pass(productName + " verified successfully in cart.");

        // Proceed to checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();

        // Select country from the dropdown
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        extentTest.info("Country selected: India");

        // Place the order
        driver.findElement(By.cssSelector(".action__submit")).click();
        Thread.sleep(2000);

        // Verify the confirmation message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        extentTest.pass("Order placed successfully. Confirmation message verified.");

        // Close the browser after the test completes
        driver.quit();
        extentTest.info("Browser closed.");

        // Generate (flush) the Extent Report
        extentReports.flush();
    }
}
