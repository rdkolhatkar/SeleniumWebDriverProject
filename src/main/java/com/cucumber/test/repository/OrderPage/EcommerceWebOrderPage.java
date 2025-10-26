package com.cucumber.test.repository.OrderPage;

import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EcommerceWebOrderPage extends EcommerceWebAbstractComponents {

    WebDriver driver;
    public EcommerceWebOrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public Boolean verifyTheDisplayedOrders(String productName){
        return productNames.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
    }
}
