package com.selenium.test;

import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.emulation.Emulation;
import org.openqa.selenium.devtools.v145.fetch.Fetch;
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

public class ChromeDevToolSelenium4 {
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
    /**
     * ============================================================
     * 🔹 Test Case: Mobile Emulation using Chrome DevTools Protocol
     * ============================================================
     *
     * 📌 Purpose:
     * This test demonstrates how to simulate a mobile device viewport
     * using Selenium 4's Chrome DevTools Protocol (CDP).
     *
     * 📌 What this test does:
     * - Overrides device screen resolution (width & height)
     * - Sets device scale factor (pixel density)
     * - Enables mobile view (responsive UI)
     * - Opens a website and interacts with mobile UI elements
     *
     * 📌 Key CDP Command Used:
     * Emulation.setDeviceMetricsOverride
     *
     * 📌 Why we use it:
     * This allows us to test how a website behaves on mobile devices
     * without using a real device or emulator.
     *
     * 📌 Real-world usage:
     * - Responsive UI testing
     * - Mobile-first application validation
     * - Cross-device compatibility testing
     *
     * 📚 Reference (Official Website):
     * https://chromedevtools.github.io/devtools-protocol/tot/Emulation/#method-setDeviceMetricsOverride
     *
     * 📚 Selenium DevTools Docs:
     * https://www.selenium.dev/documentation/webdriver/bidirectional/chrome_devtools/
     */
    @Test
    public void constructChromeDevToolsCommand() throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        // Create DevTools session (required to send CDP commands)
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Define device metrics for mobile simulation
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 600);               // Screen width
        deviceMetrics.put("height", 1000);             // Screen height
        deviceMetrics.put("deviceScaleFactor", 50);    // Pixel density
        deviceMetrics.put("mobile", true);             // Enable mobile mode

        // Execute CDP command to override device metrics
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        // Open application
        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        // Interact with mobile hamburger menu
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);

        // Navigate to Library section
        driver.findElement(By.linkText("Library")).click();

        driver.quit();
    }


    /**
     * ============================================================
     * 🔹 Test Case: Geolocation & Language Simulation using CDP
     * ============================================================
     *
     * 📌 Purpose:
     * This test simulates a user's geographic location (Tokyo, Japan)
     * and browser language using Chrome DevTools Protocol.
     *
     * 📌 What this test does:
     * - Overrides browser geolocation (latitude & longitude)
     * - Sets Accept-Language HTTP header
     * - Opens a website and prints its title
     *
     * 📌 Key CDP Commands Used:
     * 1. Emulation.setGeolocationOverride
     * 2. Network.setExtraHTTPHeaders
     * 3. Network.enable
     *
     * 📌 Important Note:
     * - Geolocation alone DOES NOT change language
     * - Accept-Language header influences content localization
     * - Some websites (like :contentReference[oaicite:0]{index=0})
     *   may ignore these settings due to cookies/account preferences
     *
     * 📌 Real-world usage:
     * - Location-based testing (maps, delivery apps)
     * - Localization testing
     * - Geo-restricted content validation
     *
     * 📚 Reference (Official Website):
     * Geolocation:
     * https://chromedevtools.github.io/devtools-protocol/tot/Emulation/#method-setGeolocationOverride
     *
     * Headers:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-setExtraHTTPHeaders
     *
     * Network Enable:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-enable
     */
    @Test
    public void localizationTestWithSetGeoLocation() throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // 🌍 Set Geolocation (Tokyo, Japan)
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 35.6895);
        coordinates.put("longitude", 139.6917);
        coordinates.put("accuracy", 1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        // 🌐 Set browser language using Accept-Language header
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept-Language", "ja-JP");

        driver.executeCdpCommand("Network.setExtraHTTPHeaders",
                Map.of("headers", headers));

        // Enable network tracking (required before setting headers)
        driver.executeCdpCommand("Network.enable", new HashMap<>());

        // Open website
        driver.get("https://www.google.com");

        // Print page title
        System.out.println("Title: " + driver.getTitle());

        Thread.sleep(5000);
        driver.quit();
    }


    /**
     * ============================================================
     * 🔹 Test Case: Capture Network Requests & Responses using CDP
     * ============================================================
     *
     * 📌 Purpose:
     * This test captures and logs all network requests and responses
     * made by the browser using Chrome DevTools Protocol.
     *
     * 📌 What this test does:
     * - Listens to outgoing HTTP requests
     * - Captures request URL, headers, and method
     * - Listens to incoming responses
     * - Captures response status, headers, and timing
     *
     * 📌 Key CDP Features Used:
     * 1. Network.enable
     * 2. Network.requestWillBeSent (Event Listener)
     * 3. Network.responseReceived (Event Listener)
     *
     * 📌 Why we use it:
     * - Debug API calls
     * - Validate backend responses
     * - Monitor network traffic during UI actions
     *
     * 📌 Real-world usage:
     * - API validation in UI automation
     * - Performance testing insights
     * - Security testing (headers, cookies)
     *
     * 📚 Reference (Official Website):
     * Network Domain:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/
     *
     * requestWillBeSent:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#event-requestWillBeSent
     *
     * responseReceived:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#event-responseReceived
     */
    @Test
    public void extractNetworkResponsesAndStatusCodesWithSeleniumCDP() throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network tracking to capture requests/responses
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

        // 🔹 Event listener for outgoing requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request networkRequest = request.getRequest();
            System.out.println(networkRequest.getUrl());       // Request URL
            System.out.println(networkRequest.getHeaders());   // Request headers
            System.out.println(networkRequest.getMethod());    // HTTP method (GET/POST)
        });

        System.out.println("****************************************************************");

        // 🔹 Event listener for incoming responses
        devTools.addListener(Network.responseReceived(), response -> {
            Response networkResponse = response.getResponse();
            System.out.println(networkResponse.getUrl());         // Response URL
            System.out.println(networkResponse.getStatus());      // Status code (200, 404, etc.)
            System.out.println(networkResponse.getResponseTime());// Response time
            System.out.println(networkResponse.getHeaders());     // Response headers
        });

        // Open application
        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        Thread.sleep(2000);

        // Trigger network calls by clicking button
        driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();

        Thread.sleep(2000);

        driver.close();
    }
    /**
     * ============================================================
     * 🔹 Test Case: Mock & Intercept Network API Request using CDP
     * ============================================================
     *
     * 📌 Purpose:
     * This test intercepts a backend API request using Chrome DevTools Protocol
     * and modifies the request URL before it is sent to the server.
     *
     * 📌 What this test does:
     * - Pauses outgoing network requests using Fetch domain
     * - Identifies specific API calls based on URL pattern
     * - Modifies query parameter value in the request (AuthorName)
     * - Continues the request with modified data
     * - Allows all other requests to pass through unchanged
     *
     * 📌 Key CDP Features Used:
     * 1. Fetch.enable
     *    → Enables request interception
     *
     * 2. Fetch.requestPaused (Event Listener)
     *    → Triggered whenever a network request is paused
     *
     * 3. Fetch.continueRequest
     *    → Used to modify and resume the intercepted request
     *
     * 📌 Mocking Logic:
     * - Original API:
     *   https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty
     *
     * - Modified API:
     *   https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=BadGuy
     *
     * - The test replaces "shetty" with "BadGuy" dynamically
     *
     * 📌 Why we use it:
     * - Simulate different backend responses without changing server data
     * - Test UI behavior under manipulated API conditions
     * - Validate application handling of unexpected or edge-case data
     *
     * 📌 Real-world usage:
     * - Negative testing (invalid user, no data scenarios)
     * - Security testing (tampered request validation)
     * - API behavior simulation during UI automation
     *
     * ⚠️ Important Notes:
     * - Request body (postData) is deprecated in newer Selenium versions
     *   → Use Optional.empty() if not required (especially for GET requests)
     *
     * - If API returns no data, UI elements may not render
     *   → Always validate elements safely using findElements() or waits
     *
     * - CDP version mismatch warning may occur if Chrome version is newer
     *   → Keep Selenium and ChromeDriver versions aligned
     *
     * 📚 Reference (Official Website):
     * Fetch Domain:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/
     *
     * requestPaused:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#event-requestPaused
     *
     * continueRequest:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#method-continueRequest
     */
    @Test
    public void mockInterceptNetworkBackendApiResponseWithCDP() throws InterruptedException {
        // Chrome Dev Tools Reference : https://chromedevtools.github.io/devtools-protocol/tot/Fetch/
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            if(requestPaused.getRequest().getUrl().contains("shetty")){
                String newMockedUrl = requestPaused.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(newMockedUrl);
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(newMockedUrl), Optional.of(requestPaused.getRequest().getMethod()), requestPaused.getRequest().getPostData(), requestPaused.getResponseHeaders(), Optional.empty()));
                // devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(newMockedUrl), Optional.of(requestPaused.getRequest().getMethod()), requestPaused.getRequest().getPostData(), Optional.empty(), Optional.empty()));
            }else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()), Optional.of(requestPaused.getRequest().getMethod()), requestPaused.getRequest().getPostData(), Optional.empty(), Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        Thread.sleep(2000);

        // Trigger network calls by clicking button
        driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();

        Thread.sleep(2000);

        //System.out.println(driver.findElement(By.cssSelector("p")).getText());

        driver.close();

    }
}
