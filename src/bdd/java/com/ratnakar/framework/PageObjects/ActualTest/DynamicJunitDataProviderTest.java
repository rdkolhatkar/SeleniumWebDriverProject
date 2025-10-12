package com.ratnakar.framework.PageObjects.ActualTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DynamicJunitDataProviderTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    // 🧩 Equivalent of TestNG DataProvider — supplies multiple login/product sets with Junit annotations
    static Stream<Map<String, String>> getMapData() {
        Map<String, String> map1 = Map.of(
                "email", "ratnakarkolhatkar@gmail.com",
                "password", "Ratanlord@1409",
                "productName", "ZARA COAT 3"
        );

        Map<String, String> map2 = Map.of(
                "email", "anshika@gmail.com",
                "password", "Iamking@000",
                "productName", "ADIDAS ORIGINAL"
        );

        return Stream.of(map1, map2);
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("getMapData")
    @Tag("PurchaseOrder")
    public void ecommerceWebAppParameterizedTest(Map<String, String> input) throws InterruptedException {

        // Launch the app
        driver.get("https://rahulshettyacademy.com/client");

        // Login
        driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
        driver.findElement(By.id("userPassword")).sendKeys(input.get("password"));
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        // Verify login title
        System.out.println("Page Title after login: " + driver.getTitle());

        // Find product
        String productName = input.get("productName");
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + productName));

        // Add to cart
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        // Wait for toast and animation
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        webDriverWait.until(ExpectedConditions.invisibilityOf(
                driver.findElement(By.cssSelector(".ng-animating"))));

        // Go to cart
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        // Validate product in cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        Assertions.assertTrue(match, "Product not found in cart: " + productName);

        // Checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();

        // Select country
        Actions actions = new Actions(driver);
        actions.sendKeys(
                driver.findElement(By.cssSelector("[placeholder='Select Country']")),
                "India"
        ).build().perform();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();

        // Place order
        driver.findElement(By.cssSelector(".action__submit")).click();
        Thread.sleep(2000);

        // Verify confirmation
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assertions.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."),
                "Order confirmation message mismatch.");
    }
}
