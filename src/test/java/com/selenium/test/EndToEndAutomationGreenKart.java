package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EndToEndAutomationGreenKart {
    @Test
    public void greenKartTestAutomation() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS); // Implicit wait

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofMillis(2500)); //Explicit Wait

        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        /*
        Use the below method when there are multiple elements with same class name, id and css text name present in the Web Page
        And among them you want to retrieve the unique element for UI Validation
        */
        // Retrive the multiple products

       String[] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot", "Brinjal", "Tomato"};
       List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
       for(int i=0; i< products.size(); i++){
           // Logic to remove the extra string from the name displayed in the webpage like converting "Brocolli - 1Kg" to "Brocolli"
           // String name = products.get(i).getText();
           // name[0] = "Brocolli" & name[1] = "1kg"
           //remove all the spaces after "Brocolli"
           String[] name = products.get(i).getText().split("-");
           String formattedName = name[0].trim();
           List vegetables = Arrays.asList(itemsNeeded);
           int j =0;
           if(vegetables.contains(formattedName)){
               j++;
//             driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click(); // This is dynamic x path which changes after first click
               driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click(); // This is static x path for add to cart button which never changes
               System.out.println(j);
               if(j == Arrays.stream(itemsNeeded).count()){
                   break;
               }
           }

           driver.findElement(By.cssSelector("img[alt='Cart']")).click(); // clicking on cart button
           driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click(); // clicking on PROCEED TO CHECKOUT button
           //Explicit Wait
           explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
           driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
           driver.findElement(By.cssSelector("button.promoBtn")).click();
           // Unlike implicit wait we cannot define explicit wait globally. It should only be defined for specific webElement which is taking time to load, Explicit wait does not affect any other steps, it will only affect the targeted step or element
           // Explicit wait
           explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
           System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
           // Fluent Wait finds the web elements on the regular intervals of time until the timeout or object gets found.
           // Fluent Wait -> timeout (10 Seconds) polling (2 Seconds) for this case Fluent wait will search the Web elements for 5 times after every 2 seconds for the duration of 10 seconds


       }
       Thread.sleep(5000);
       driver.close();
       driver.quit();
    }
}
