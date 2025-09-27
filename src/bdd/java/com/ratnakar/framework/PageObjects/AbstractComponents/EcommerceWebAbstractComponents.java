package com.ratnakar.framework.PageObjects.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcommerceWebAbstractComponents {

    WebDriver driver;

    public EcommerceWebAbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findByElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findByElement));
    }

    public void waitForElementToDisappear(WebElement webElement) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));

    }

}
