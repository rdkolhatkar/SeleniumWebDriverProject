package com.ratnakar.framework.PageObjects.CartPage;

import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EcommerceWebCartPage {
    WebDriver driver;

    public EcommerceWebCartPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".totalRow button")
    WebElement checkOutElement;

    @FindBy(css=".cartSection h3")
    private List<WebElement> cartProductTitles;

    public Boolean verifyTheDisplayedCartProducts(String productName){
        return cartProductTitles.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
    }

    public void goToCheckOut(){
        checkOutElement.click();
    }


}
