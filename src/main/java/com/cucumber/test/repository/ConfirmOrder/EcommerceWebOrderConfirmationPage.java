package com.cucumber.test.repository.ConfirmOrder;

import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EcommerceWebOrderConfirmationPage extends EcommerceWebAbstractComponents {

    WebDriver driver;
    public EcommerceWebOrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmationMessage;

    public String getOrderConfirmationMessage(){
        return confirmationMessage.getText();
    }

}
