package com.selenium.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarDateSelectorSetup {

    WebDriver driver = new ChromeDriver();
    @Test
    public void checkIfCalendarIsEnabled(){
        /*
         The getDomAttribute() method in Selenium WebDriver retrieves the raw DOM attribute value of a web element as defined in the HTML source code.
         When an element in disabled in UI Web Page then DOM Attribute will have some different value. Like in our case,
         When Date element is disabled then "style" Attribute is displaying value indicator as 0.5 in HTML code.
         And when Date element is enabled then "style" Attribute is displaying value indicator as 1 in HTML code.
         By verifying the "style" Attribute's Indicator value we can verify whether element is enabled or disabled.
         It was introduced in Selenium 4 and is useful when you want to extract the attribute exactly as it appears in the Document Object Model (DOM),
         without any modifications applied by JavaScript.
         Example of a DOM Attribute in HTML

         <input id="username" type="text" placeholder="Enter your username">

         In this example, the attributes are:
         id="username"
         type="text"
         placeholder="Enter your username"
         These attributes define the initial state of the element in the HTML source code.

         Feature	                                                   | getDomAttribute() | getAttribute()
         ------------------------------------------------------------------------------------------------------
         Fetches raw attribute value from HTML	                       |      ✅ Yes	   |   ❌ No
         Returns computed properties (e.g., JavaScript-modified values)|      ❌ No	       |   ✅ Yes
         Reflects JavaScript changes to attributes	                   |      ❌ No	       |   ✅ Yes
         -------------------------------------------------------------------------------------------------------
        */

        driver.get("http://spicejet.com");
        System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
        if(driver.findElement(By.id("Div1")).getDomAttribute("style").contains("1"))
        {
            System.out.println("its enabled");
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

}
