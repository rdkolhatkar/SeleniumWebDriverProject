package com.ratnakar.framework.PageObjects.ActualTest;

import com.ratnakar.framework.PageObjects.BaseTest.EcommerceWebBaseTest;
import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DynamicDataProviderTest extends EcommerceWebBaseTest {
    @DataProvider
    public Object[][] getData(){
        return new Object [][] { {"ratnakarkolhatkar@gmail.com", "Ratanlord@1409", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"} };
    }
    @DataProvider
    public Object[][] getMapData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("email", "ratnakarkolhatkar@gmail.com");
        map.put("password", "Ratanlord@1409");
        map.put("productName", "ZARA COAT 3");
        HashMap<String, String> mapOne = new HashMap<>();
        map.put("email", "anshika@gmail.com");
        map.put("password", "Iamking@000");
        map.put("productName", "ADIDAS ORIGINAL");
        return new Object [][] { {map}, {mapOne} };
    }
    @Test(dataProvider = "getData", groups = {"PurchaseOrder"})
    public void EcommerceWebApplicationTest(String email, String password, String productName) throws InterruptedException {
        EcommerceWebProductCatalogue ecommerceWebProductCatalogue = loginPage.loginToEcommerceWebApplication(email, password);
        System.out.println(driver.getTitle());
        ecommerceWebProductCatalogue.addProductToCart(productName);
        Thread.sleep(1000);
        EcommerceWebCartPage ecommerceWebCartPage = ecommerceWebProductCatalogue.goToCartPage();
        Boolean match = ecommerceWebCartPage.verifyTheDisplayedCartProducts(productName);
        Assert.assertTrue(match);
        EcommerceWebCheckoutPage ecommerceWebCheckoutPage = ecommerceWebCartPage.goToCheckOut();
        ecommerceWebCheckoutPage.selectCountry("India");
        EcommerceWebOrderConfirmationPage ecommerceWebOrderConfirmationPage = ecommerceWebCheckoutPage.submitOrder();
        String confirmMessage = ecommerceWebOrderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    @Test(dataProvider = "getMapData", groups = {"PurchaseOrder"})
    public void EcommerceWebApplicationHashMapTest(HashMap<String, String> input) throws InterruptedException {
        EcommerceWebProductCatalogue ecommerceWebProductCatalogue = loginPage.loginToEcommerceWebApplication(input.get("email"), input.get("password"));
        System.out.println(driver.getTitle());
        ecommerceWebProductCatalogue.addProductToCart(input.get("productName"));
        Thread.sleep(1000);
        EcommerceWebCartPage ecommerceWebCartPage = ecommerceWebProductCatalogue.goToCartPage();
        Boolean match = ecommerceWebCartPage.verifyTheDisplayedCartProducts(input.get("productName"));
        Assert.assertTrue(match);
        EcommerceWebCheckoutPage ecommerceWebCheckoutPage = ecommerceWebCartPage.goToCheckOut();
        ecommerceWebCheckoutPage.selectCountry("India");
        EcommerceWebOrderConfirmationPage ecommerceWebOrderConfirmationPage = ecommerceWebCheckoutPage.submitOrder();
        String confirmMessage = ecommerceWebOrderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

}
