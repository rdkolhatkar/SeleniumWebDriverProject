package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitImplementation {

    @Test
    public void explicitFluentWaitTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("[id='start'] button")).click();

        // As FluentWait cannot understand the interface we have to explicitly mention the WebDriver interface in the Syntax of FluentWait
        //Syntax of defining the FluentWait is as below
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                //[id='finish'] h4 -> Ths is a static HTML Attribute and When we click on start button it is in disabled mode but after some time it will get enabled. So in this case our assertions will fail
                //To fix this disabled element issue in our code we have to apply condition to wait until HTML element is enabled
                if( driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()){
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }
                else {
                    return null;
                }
            }
        });

        System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).getText());

    }

}
