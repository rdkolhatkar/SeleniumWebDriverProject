package com.ratnakar.framework.pageobjects.loginpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
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

    public void loginToEcommerceWebApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
    }

    public void navigateTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

}
