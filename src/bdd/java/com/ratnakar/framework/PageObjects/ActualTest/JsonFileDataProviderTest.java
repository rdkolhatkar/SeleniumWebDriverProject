package com.ratnakar.framework.PageObjects.ActualTest;

import com.ratnakar.framework.PageObjects.BaseTest.EcommerceWebBaseTest;
import com.ratnakar.framework.PageObjects.CartPage.EcommerceWebCartPage;
import com.ratnakar.framework.PageObjects.CheckOutPage.EcommerceWebCheckoutPage;
import com.ratnakar.framework.PageObjects.ConfirmOrder.EcommerceWebOrderConfirmationPage;
import com.ratnakar.framework.PageObjects.ProductPage.EcommerceWebProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonFileDataProviderTest extends EcommerceWebBaseTest {

    @DataProvider
    public Object[][] getDataFromJsonFile() throws IOException {
        // As our JsonFileDataProviderTest extends EcommerceWebBaseTest and EcommerceWebBaseTest extends JsonFileReader
        // We can directly call the getJsonData() method
        List<HashMap<String, String>> testData = getJsonData("src/bdd/resources/EcommerceWebAppData.json");
        return new Object[][] { {testData.get(0)}, {testData.get(1)} };
    }

    @Test(dataProvider = "getDataFromJsonFile", groups = {"PurchaseOrder"})
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
