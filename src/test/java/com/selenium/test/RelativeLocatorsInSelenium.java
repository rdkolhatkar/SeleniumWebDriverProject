package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsInSelenium {

    @Test
    public void relativeLocatorsInSeleniumFour(){
        // Relative locators are introduced in the selenium 4
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        // Finding element with relative locator
        // To use relative locators in our project we have to manually import one static package called {import static org.openqa.selenium.support.locators.RelativeLocator.*;}
        String elementName = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
        System.out.println(elementName);
        WebElement dateOfBirth = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dateOfBirth)).click();
        // relative locators do not support flex html elements
        WebElement iceCreamLabel = driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();
        WebElement radioBtn = driver.findElement(By.id("inlineRadio1"));
        String radioBtnText = driver.findElement(with(By.tagName("label")).toRightOf(radioBtn)).getText();
        System.out.println(radioBtnText);
        driver.close();
    }
}
