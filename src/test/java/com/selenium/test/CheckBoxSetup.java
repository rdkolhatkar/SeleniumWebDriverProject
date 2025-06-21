package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBoxSetup {
    WebDriver driver = new ChromeDriver();

    public void checkbox(){
        driver.get("https://www.spicejet.com/");
        /*
        {id*='seniorCitizenDiscount'} in this CSS expression, as 'id' value is very long
        we have given '*' after id so that it will pick css value as 'id*' means id which contains
        characters 'seniorCitizenDiscount'
         */
        System.out.println(driver.findElement(By.cssSelector("input[id*='seniorCitizenDiscount']")).isSelected());
        driver.findElement(By.cssSelector("input[id*='seniorCitizenDiscount']")).click();
        System.out.println(driver.findElement(By.cssSelector("input[id*='seniorCitizenDiscount']")).isSelected());

        /*
        To find number of checkboxes present in the web page which is displayed on the screen,
        you need to find the common attribute which is present in all checkboxes html code.
        For Example: type="checkbox"
        CSS => input[type='checkbox']
        "size()" function in selenium gives the size of the elements that is count.
         */
        driver.findElements(By.cssSelector("input[type='checkbox']")).size();
    }
}
