package com.cucumber.test.repository.CheckOutPage;

import com.cucumber.test.repository.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EcommerceWebCheckoutPage extends EcommerceWebAbstractComponents {

    WebDriver driver;

    public EcommerceWebCheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement countrySelection;
    @FindBy(css=".action__submit")
    WebElement submitActionButton;
    @FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
    WebElement selectCountry;

    By result = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions actions = new Actions(driver);
        actions.sendKeys(
                countrySelection,
                countryName
        ).build().perform();
        // After sending keys we have to wait till our dropdown list is visible
        waitForElementToAppear(result);
        selectCountry.click();
    }

    public EcommerceWebOrderConfirmationPage submitOrder(){
        // Now place the order by clicking on place order button
        submitActionButton.click();

        // Creating object of confirmation page and returning it
        EcommerceWebOrderConfirmationPage ecommerceWebOrderConfirmationPage = new EcommerceWebOrderConfirmationPage(driver);

        return ecommerceWebOrderConfirmationPage;
    }


}
