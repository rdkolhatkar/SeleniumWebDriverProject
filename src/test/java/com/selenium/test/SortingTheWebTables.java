package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SortingTheWebTables {
    @Test
    public void sortWebTable(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        // Click on the column to sort the existing Web table
        driver.findElement(By.xpath("//tr//th[1]")).click();
        // Select the elements from the 1st row of the table and get the text value of all the elements in the 1st Row
        List<WebElement> elementsList = driver.findElements(By.xpath("//tr//td[1]"));
        // Now we will convert the elementList into a Java Stream
        List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
        // Now we will again apply Java Stream to the originalList and Sort it in order and compare the newOriginalList with previous originalList
        List<String> newSortedOriginalList = originalList.stream().sorted().collect(Collectors.toList());
        Assert.assertTrue(originalList.equals(newSortedOriginalList));
        driver.quit();

    }
}
