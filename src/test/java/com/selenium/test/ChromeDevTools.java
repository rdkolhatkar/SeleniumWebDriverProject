package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.emulation.Emulation;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.Request;
import org.openqa.selenium.devtools.v145.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeDevTools {
    @Test
    public void chromeDevToolsTest() throws InterruptedException {
        // To access the Chrome Browser Dev tools we have to explicitly create the Object of ChromeDriver class
        // We cannot use the Object of WebDriver class
        ChromeDriver driver = new ChromeDriver();
        // Initializing the Browser Dev Tools with ".getDevTools()" methods
        DevTools devTools = driver.getDevTools();
        // Instantiate chrome browser session
        devTools.createSession();
        // Now we can send the commands to the 'Chrome Dev Tools Protocol (CDP)'
        // By sending commands to the CDP Methods, We can invoke and get access to chrome dev tools
        // Reference(Official WebSite) : https://chromedevtools.github.io/devtools-protocol/
        // From the above official website of Chrome Dev Tools Protocol (CDP) you will get the commands which you want to send to the devTools
        // Important Note: In POM.xml you have to add selenium dev tool dependency which is compatible with chrome browser version.
        // In this case we have added 'selenium-devtools-v145' this dependency because we are using chrome version ad 'Version 145.0.7632.117 (Official Build) (64-bit)'
        devTools.send(Emulation.setDeviceMetricsOverride(
                600,
                1000,
                50,
                true,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty())
        );
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(30000);
        driver.findElement(By.linkText("Library")).click();
        Thread.sleep(30000);
        driver.close();
    }
    @Test
    public void networkMonitoringTest() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable Network
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        // Listen for API Responses
        devTools.addListener(Network.responseReceived(), response -> {

            if (response.getType().toString().equalsIgnoreCase("XHR") ||
                    response.getType().toString().equalsIgnoreCase("FETCH")) {

                System.out.println("========== API CALL ==========");
                System.out.println("URL: " + response.getResponse().getUrl());
                System.out.println("Status: " + response.getResponse().getStatus());
                System.out.println("==============================");
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait until page loads completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        // Click Library directly (no navbar dependency)
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Library"))).click();

        driver.quit();
    }

    @Test
    public void constructChromeDevToolsCommand() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 600);
        deviceMetrics.put("height", 1000);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Library")).click();
        driver.quit();
    }
}
