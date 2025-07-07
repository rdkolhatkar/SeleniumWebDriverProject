package com.selenium.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SslCertificationHandling {
    @Test
    public void badSslCertificationErrorHandling(){
        // To handle bad ssl certification error we have to use below code to bypass the ssl error and open the actual website
        // For Google Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addExtensions("Extension File Path"); // With this method we cn pass the extensions to our website
        // Proxy settings
        /*
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("Proxt Value containing ipAddress:PortNumber");
        chromeOptions.setCapability("proxy", proxy);
        */
        chromeOptions.setAcceptInsecureCerts(true); // This code will allow us to accept the bad ssl warning and bypass the ssl error and open the actual website
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://expired.badssl.com/");
        System.out.println(driver.getTitle());

        // For Firefox browser
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);

        // For Microsoft Edge browser
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setAcceptInsecureCerts(true);

    }
}
