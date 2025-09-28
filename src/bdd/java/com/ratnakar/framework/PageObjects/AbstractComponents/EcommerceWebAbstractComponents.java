package com.ratnakar.framework.PageObjects.AbstractComponents;

import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EcommerceWebAbstractComponents {

    WebDriver driver;

    public EcommerceWebAbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    public void waitForElementToAppear(By findByElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findByElement));
    }

    public void waitForElementToDisappear(WebElement webElement) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));

    }

    public EcommerceWebCartPage goToCartPage(){
        cartHeader.click();

        // Creating object of cart page to return the same object
        EcommerceWebCartPage ecommerceWebCartPage = new EcommerceWebCartPage(driver);
        return ecommerceWebCartPage;
    }

}
