package com.ratnakar.framework.PageObjects.ProductPage;

import com.ratnakar.framework.PageObjects.AbstractComponents.EcommerceWebAbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EcommerceWebProductCatalogue extends EcommerceWebAbstractComponents {

    WebDriver driver;

    // CSS Selectors
    private static final By PRODUCTS_BY = By.cssSelector(".mb-3");
    private static final By ADD_TO_CART = By.cssSelector(".card-body button:last-of-type");
    private static final By TOAST_MESSAGE = By.cssSelector("#toast-container");

    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    public EcommerceWebProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductsList() {
        waitForElementToAppear(PRODUCTS_BY);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductsList().stream()
                .filter(s -> s.findElement(By.cssSelector("b")).getText()
                        .equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        if (product != null) {
            waitForElementToDisappear(spinner);
            product.findElement(ADD_TO_CART).click();
            waitForElementToAppear(TOAST_MESSAGE);
            waitForElementToDisappear(spinner);
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }
}
