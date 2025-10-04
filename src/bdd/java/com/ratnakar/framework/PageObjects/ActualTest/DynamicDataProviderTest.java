package com.ratnakar.framework.PageObjects.ActualTest;

import com.ratnakar.framework.PageObjects.BaseTest.EcommerceWebBaseTest;
import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicDataProviderTest extends EcommerceWebBaseTest {

    @DataProvider
    public Object[][] getData(){
        return new Object [][] { {"ratnakarkolhatkar@gmail.com", "Ratanlord@1409", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"} };
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

}
