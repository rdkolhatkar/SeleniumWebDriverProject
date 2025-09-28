package com.ratnakar.framework.PageObjects.LoginPage;

import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EcommerceWebLoginPage extends EcommerceWebAbstractComponents {

    WebDriver driver;

    public EcommerceWebLoginPage(WebDriver driver) {
        // As EcommerceWebLoginPage is child class of EcommerceWebAbstractComponents
        // We have to send the driver instance coming from EcommerceWebTest towards the EcommerceWebAbstractComponents
        // For this we have to use the super() keyword in java
        super(driver);
        // Here we have to initialize driver which is defined in EcommerceWebTest
        this.driver = driver;
        // We will define the PageFactory elements in the constructor

        PageFactory.initElements(driver, this);
    }

    // Here we are going to use the PageFactory for code optimization from "org.openqa.selenium.support"
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submitButton;

    // Now we have to write the Action methods for Entering userEmail, userPassword and clicking on submitButton

    public EcommerceWebProductCatalogue loginToEcommerceWebApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();

        // Defining object of EcommerceWebProductCatalogue
        EcommerceWebProductCatalogue ecommerceWebProductCatalogue = new EcommerceWebProductCatalogue(driver);
        return ecommerceWebProductCatalogue;
    }

    public void navigateTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

}
