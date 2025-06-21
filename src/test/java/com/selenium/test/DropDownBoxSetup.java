package com.selenium.test;

import net.serenitybdd.annotations.Step;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropDownBoxSetup {
    WebDriver driver = new ChromeDriver();
    WebElement staticDropDown;
    @Step
    public void selectStaticDropDownOfCurrency(String appBaseUrl){
        driver.get(appBaseUrl);
        staticDropDown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropDown);
        //Select By INDEX
        dropdown.selectByIndex(3);
        //Select By Visible Text
        dropdown.selectByVisibleText("AED");
        System.out.println(dropdown.getFirstSelectedOption().getText());
    }
    @Step
    public void selectStaticDropDownOfPassengers() throws InterruptedException {
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000L);
        System.out.println("Initial Value of Passengers = "+driver.findElement(By.id("divpaxinfo")).getText());
        int i =1;
        while(i<5){
            driver.findElement(By.id("hrefIncAdt")).click();
            i++;
        }
        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println("Final Value of Passengers = "+driver.findElement(By.id("divpaxinfo")).getText());

    }
    @Step
    public void selectDynamicDropDownOfLocation() throws InterruptedException {
        driver.get("http://spicejet.com"); //URL in the browser
        //a[@value='MAA']  - Xpath for chennai
        //a[@value='BLR']
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
        // Parent-Child Xpath
        // Parent X-PATH -> //div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']
        // Child X-PATH -> //a[@value='MAA']
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
    }

    @Step
    public void selectAutoSuggestiveDropDown() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(3000);
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for(WebElement option :options){
            if (option.getText().equalsIgnoreCase("India")) {
                option.click();
                break;
            }
        }

    }


}
