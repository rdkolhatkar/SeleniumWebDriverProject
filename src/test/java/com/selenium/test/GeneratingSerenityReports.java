package com.selenium.test;

import io.cucumber.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.annotations.findby.By;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
@RunWith(Cucumber.class)
public class GeneratingSerenityReports {

    // Serenity automatically manages the WebDriver instance
    @Managed(driver = "chrome")
    WebDriver driver;

    @Test
    public void verifyEcommerceOrderPlacement() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("ratnakarkolhatkar@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ratanlord@1409");
        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        String productName = "ZARA COAT 3";
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        Thread.sleep(2000);
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.quit();
    }

}
