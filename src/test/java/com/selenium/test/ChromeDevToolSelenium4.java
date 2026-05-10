package com.selenium.test;

import com.google.common.collect.ImmutableList;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.chromium.HasNetworkConditions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v145.emulation.Emulation;
import org.openqa.selenium.devtools.v145.fetch.Fetch;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

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

        driver.close();

    }
    /**
     * ============================================================
     * 🔹 Test Case: Mock Network Failure for API Call using CDP
     * ============================================================
     *
     * 📌 Purpose:
     * This test simulates a backend API failure using Chrome DevTools Protocol (CDP)
     * by intentionally blocking a specific network request.
     *
     * 📌 What this test does:
     * - Intercepts outgoing network requests using Fetch domain
     * - Filters API calls based on URL pattern (*GetBook*)
     * - Forces the matched request to fail instead of reaching the server
     * - Simulates real-world backend failure scenarios
     *
     * 📌 Key CDP Features Used:
     * 1. Fetch.enable
     *    → Enables request interception with specific URL pattern
     *
     * 2. RequestPattern
     *    → Defines which API requests should be intercepted
     *    → Here: Any request containing "GetBook"
     *
     * 3. Fetch.requestPaused (Event Listener)
     *    → Triggered when matching request is intercepted
     *
     * 4. Fetch.failRequest
     *    → Aborts the request and simulates network failure
     *
     * 📌 Mocking Logic:
     * - Target API:
     *   https://rahulshettyacademy.com/Library/GetBook.php
     *
     * - Behavior:
     *   Instead of sending request to server,
     *   the request is FAILED using:
     *   ErrorReason.FAILED
     *
     * 📌 Why we use it:
     * - Test application behavior when backend API fails
     * - Validate error handling and fallback UI
     * - Ensure proper error messages are displayed
     *
     * 📌 Real-world usage:
     * - Network outage simulation
     * - API downtime testing
     * - Resilience and retry mechanism validation
     * - Negative testing scenarios
     *
     * ⚠️ Important Notes:
     * - UI should handle failure gracefully (no crashes)
     * - Always validate error messages or fallback components
     * - Use waits to ensure UI reacts after failure
     *
     * - Overuse of Thread.sleep() is not recommended
     *   → Prefer WebDriverWait for better stability
     *
     * 📚 Reference (Official Website):
     * Fetch Domain:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/
     *
     * failRequest:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#method-failRequest
     *
     * RequestPattern:
     * https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#type-RequestPattern
     */
    @Test
    public void mockingNetworkFailureApiCallOnWebPage() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Optional<List<RequestPattern>> patterns = Optional.of(List.of(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
        devTools.send(Fetch.enable(patterns, Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();
        driver.quit();
    }
    /**
     * ============================================================
     * 🔹 Test Case: Block UI Resource Requests using CDP
     * ============================================================
     *
     * 📌 Purpose:
     * This test improves UI automation performance by blocking
     * unnecessary frontend resource requests such as Images and CSS
     * using Chrome DevTools Protocol (CDP).
     *
     * 📌 What this test does:
     * - Enables Network domain using CDP
     * - Blocks specific resource types based on URL patterns
     * - Prevents Image (.jpg) and CSS (.css) files from loading
     * - Loads the application with reduced network overhead
     * - Measures total page interaction execution time
     *
     * 📌 Key CDP Features Used:
     *
     * 1. Network.enable
     *    → Activates Network domain in Chrome DevTools
     *    → Required before applying network-related configurations
     *
     * 2. Network.setBlockedURLs
     *    → Blocks specific network requests before they are sent
     *    → Here we block:
     *      - *.jpg
     *      - *.css
     *
     * 📌 Blocking Logic:
     * - Blocked Resources:
     *      *.jpg  → Image files
     *      *.css  → Stylesheet files
     *
     * - Effect:
     *   Browser skips downloading these resources
     *   which reduces page load time and improves test speed
     *
     * 📌 Why we use it:
     * - Faster UI test execution
     * - Reduce dependency on slow/static assets
     * - Avoid failures caused by delayed image or CSS loading
     * - Improve stability in unstable network environments
     * - Useful for backend/API-focused UI testing
     *
     * 📌 Real-world usage:
     * - Performance optimization in automation suites
     * - Running tests in low bandwidth environments
     * - CI/CD pipeline execution speed improvement
     * - Isolating UI logic from heavy frontend assets
     *
     * ⚠️ Important Notes:
     * - Blocking CSS may affect UI rendering/layout
     * - Some elements may become invisible or shift position
     * - Use cautiously when validating UI styling
     *
     * - Blocking Images is generally safe for:
     *      ✔ Functional testing
     *      ✔ API validation
     *      ✔ Navigation testing
     *
     * - Thread.sleep() is used only for demo purposes
     *   → Prefer WebDriverWait for production automation
     *
     * 📌 Execution Time Calculation:
     * - startTime → Captured before page load
     * - endTime   → Captured after test completion
     * - Difference indicates total execution duration
     *
     * ⚠️ Correction:
     * Current calculation:
     *      (startTime - endTime)
     *
     * Should be:
     *      (endTime - startTime)
     *
     * 📚 Reference (Official Website):
     * Network Domain:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/
     *
     * setBlockedURLs:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-setBlockedURLs
     */
    @Test
    public void blockUiNetworkRequests() throws InterruptedException {
        // Here we are blocking the backend api calls, If any Image or CSS is taking much more time to load due to unstable network, then to avoid test failure we can block those api calls
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(Optional.empty(),
                Optional.of(List.of("*.jpg", "*.css"))));
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Enable/Disable Buying']")).click();
        driver.quit();
        long endTime = System.currentTimeMillis();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println("Total Time taken"+(startTime - endTime));
    }
    /**
     * ============================================================
     * 🔹 Test Case: Emulate Slow Network using CDP (Old Deprecated Method)
     * ============================================================
     *
     * 📌 Purpose:
     * This test simulates slow or unstable internet conditions
     * using the old Chrome DevTools Protocol (CDP) API:
     *
     *      Network.emulateNetworkConditions()
     *
     * This helps validate how the application behaves under:
     * - Slow internet
     * - High latency
     * - Poor bandwidth
     * - Network inconsistency
     *
     * 📌 What this test does:
     * - Launches Chrome browser
     * - Creates DevTools session
     * - Enables Network domain
     * - Simulates slow network speed
     * - Opens application under throttled network
     * - Measures total execution time
     * - Performs UI interaction
     *
     * 📌 Key CDP Features Used:
     *
     * 1. Network.enable
     *    → Activates Network domain in Chrome DevTools
     *    → Required before applying network configurations
     *
     * 2. Network.emulateNetworkConditions
     *    → Simulates custom internet speed and latency
     *    → Allows testing application behavior under
     *      different network environments
     *
     * 📌 Network Configuration Used:
     *
     * - Offline Mode:
     *      false
     *      → Browser remains online
     *
     * - Latency:
     *      3000 ms
     *      → Simulates 3 seconds network delay
     *
     * - Download Speed:
     *      20000 bytes/sec
     *
     * - Upload Speed:
     *      10000 bytes/sec
     *
     * - Connection Type:
     *      ETHERNET
     *
     * 📌 Why we use it:
     * - Validate application performance on slow networks
     * - Test loading spinners and lazy loading
     * - Detect timeout-related UI issues
     * - Verify retry mechanism behavior
     * - Reproduce real-world unstable internet conditions
     *
     * 📌 Real-world usage:
     * - Performance testing
     * - Mobile/internet simulation
     * - CI/CD environment validation
     * - Testing in low bandwidth environments
     * - Validating user experience under network delay
     *
     * ⚠️ Important Notes:
     * - This method is DEPRECATED in Selenium 4 newer CDP versions
     * - Deprecated in:
     *      org.openqa.selenium.devtools.v145
     *
     * - Recommended Replacement:
     *      ChromiumNetworkConditions
     *      HasNetworkConditions
     *
     * - New Selenium-native API should be preferred
     *   over raw CDP commands
     *
     * ⚠️ Thread.sleep():
     * - Used only for demo purposes
     * - Replace with WebDriverWait in production
     *
     * 📌 Execution Time Calculation:
     * - startTime → Captured before application load
     * - endTime   → Captured after test completion
     * - Difference indicates total execution duration
     *
     * Correct Formula:
     *      (endTime - startTime)
     *
     * 📚 Reference (Official Website):
     * Network Domain:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/
     *
     * emulateNetworkConditions:
     * https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-emulateNetworkConditions
     */
    @Test
    public void emulateNetworkSpeedWithChromeDevToolsOldMethod() throws InterruptedException {
        // This is used for fixing the network inconsistency and latency issues.
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 10000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();
        driver.quit();
        long endTime = System.currentTimeMillis();
    }
    /**
     * ============================================================
     * 🔹 Test Case: Emulate Slow Network using Selenium Native API
     * ============================================================
     *
     * 📌 Purpose:
     * This test simulates slow internet conditions using
     * Selenium 4 Chromium Network APIs instead of deprecated
     * raw Chrome DevTools Protocol (CDP) methods.
     *
     * This is the modern and recommended approach for:
     * - Network throttling
     * - Latency simulation
     * - Bandwidth limitation
     * - Slow internet testing
     *
     * 📌 What this test does:
     * - Launches Chrome browser
     * - Configures slow network conditions
     * - Applies custom latency
     * - Limits upload/download bandwidth
     * - Opens application under throttled network
     * - Performs UI interaction
     * - Waits for dynamic content
     * - Measures total execution time
     * - Validates application response using assertions
     * - Resets network conditions
     *
     * 📌 Selenium APIs Used:
     *
     * 1. ChromiumNetworkConditions
     *    → Selenium native class used for configuring:
     *      - Latency
     *      - Download speed
     *      - Upload speed
     *      - Offline mode
     *
     * 2. HasNetworkConditions
     *    → Interface used to:
     *      - Apply network conditions
     *      - Reset network conditions
     *
     * 📌 Network Configuration Used:
     *
     * - Offline Mode:
     *      false
     *      → Browser remains online
     *
     * - Latency:
     *      200 ms
     *      → Simulates network response delay
     *
     * - Download Throughput:
     *      50 KB/sec
     *
     * - Upload Throughput:
     *      20 KB/sec
     *
     * 📌 Why we use it:
     * - Validate application behavior under slow internet
     * - Test loading indicators/spinners
     * - Identify timeout-related failures
     * - Verify application stability
     * - Reproduce real-world mobile/slow network scenarios
     *
     * 📌 Real-world usage:
     * - Performance testing
     * - UI responsiveness validation
     * - CI/CD network simulation
     * - Low bandwidth testing
     * - Dynamic content loading validation
     * - Lazy loading verification
     *
     * 📌 Dynamic Content Validation:
     * This test validates dynamically loaded content:
     *
     * Expected Text:
     *      "Hello World!"
     *
     * The test continuously checks until:
     * - Element becomes visible
     * - Text becomes available
     * - Timeout occurs
     *
     * 📌 Waiting Logic:
     * - Uses polling loop for demo purposes
     * - Poll interval:
     *      500 ms
     *
     * - Safety Timeout:
     *      30 seconds
     *
     * ⚠️ Production Recommendation:
     * Replace manual polling with:
     *      WebDriverWait
     *      ExpectedConditions
     *
     * 📌 Assertions:
     * - Validates actual text against expected text
     * - Throws AssertionError on mismatch
     * - Logs assertion status in console
     *
     * 📌 Execution Time Calculation:
     * - startTime → Captured before page load
     * - endTime   → Captured after validation
     * - Difference indicates total execution duration
     *
     * Formula Used:
     *      Duration.between(startTime, endTime)
     *
     * 📌 Why New API is Better:
     * ✔ Cleaner implementation
     * ✔ Selenium-native abstraction
     * ✔ Easier maintenance
     * ✔ Better readability
     * ✔ Avoids deprecated CDP methods
     * ✔ Future-proof approach
     *
     * ⚠️ Important Notes:
     * - Works only on Chromium-based browsers:
     *      ✔ Chrome
     *      ✔ Edge
     *      ✔ Chromium
     *
     * - Throughput values are in:
     *      bytes/sec
     *
     * - Thread.sleep() used only for demo purposes
     *
     * 📚 Reference (Official Website):
     * Selenium HasNetworkConditions:
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/chromium/HasNetworkConditions.html
     *
     * Selenium ChromiumNetworkConditions:
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/chromium/ChromiumNetworkConditions.html
     */
    @Test
    public void emulateNetworkSpeedWithChromeDevTools() throws InterruptedException {
        // This is used for fixing the network inconsistency and latency issues.
        // ============================================================
        // 🔹 Launch Chrome Browser
        // ============================================================

        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ============================================================
        // 🔹 Setup Network Throttling Conditions
        // ============================================================

        ChromiumNetworkConditions networkConditions =
                new ChromiumNetworkConditions();

        // Simulate Slow 3G Network

        // Latency = delay between request & response
        networkConditions.setLatency(Duration.ofMillis(200));

        // Download speed in bytes/sec
        networkConditions.setDownloadThroughput(50 * 1024);

        // Upload speed in bytes/sec
        networkConditions.setUploadThroughput(20 * 1024);

        // Online mode
        networkConditions.setOffline(false);

        // Apply network conditions
        ((HasNetworkConditions) driver)
                .setNetworkConditions(networkConditions);

        System.out.println("=================================================");
        System.out.println("✅ Network Throttling Applied Successfully");
        System.out.println("Latency          : 200 ms");
        System.out.println("Download Speed   : 50 KB/s");
        System.out.println("Upload Speed     : 20 KB/s");
        System.out.println("=================================================");

        // ============================================================
        // 🔹 Start Time Logging
        // ============================================================

        Instant startTime = Instant.now();

        System.out.println("🚀 Opening application...");
        System.out.println("Start Time : " + startTime);

        // Open website
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // ============================================================
        // 🔹 Perform Action
        // ============================================================

        WebElement startButton =
                driver.findElement(By.cssSelector("#start button"));

        startButton.click();

        // ============================================================
        // 🔹 Wait Until Result Appears
        // ============================================================

        WebElement finishText;

        long waitStart = System.currentTimeMillis();

        while (true) {

            finishText = driver.findElement(By.id("finish"));

            if (finishText.isDisplayed()
                    && finishText.getText().trim().length() > 0) {
                break;
            }

            // Safety timeout after 30 seconds
            long currentTime = System.currentTimeMillis();

            if ((currentTime - waitStart) > 30000) {
                throw new RuntimeException(
                        "❌ Timeout: Element did not appear within 30 seconds");
            }

            Thread.sleep(500);
        }

        // ============================================================
        // 🔹 End Time Logging
        // ============================================================

        Instant endTime = Instant.now();

        long totalTime =
                Duration.between(startTime, endTime).toMillis();

        System.out.println("=================================================");
        System.out.println("✅ Page Loaded Successfully");
        System.out.println("End Time   : " + endTime);
        System.out.println("Total Time : " + totalTime + " ms");
        System.out.println("=================================================");

        // ============================================================
        // 🔹 Assertions
        // ============================================================

        String actualText = finishText.getText().trim();
        String expectedText = "Hello World!";

        System.out.println("=================================================");
        System.out.println("🔍 Performing Assertions");
        System.out.println("Expected : " + expectedText);
        System.out.println("Actual   : " + actualText);
        System.out.println("=================================================");

        if (actualText.equals(expectedText)) {

            System.out.println("✅ ASSERTION PASSED");
            System.out.println("Text matched successfully");

        } else {

            System.out.println("❌ ASSERTION FAILED");

            throw new AssertionError(
                    "Expected: "
                            + expectedText
                            + " but found: "
                            + actualText);
        }

        // ============================================================
        // 🔹 Reset Network Conditions
        // ============================================================

        ((HasNetworkConditions) driver)
                .deleteNetworkConditions();

        System.out.println("=================================================");
        System.out.println("✅ Network Conditions Reset");
        System.out.println("=================================================");

        // ============================================================
        // 🔹 Close Browser
        // ============================================================

        driver.quit();

        System.out.println("=================================================");
        System.out.println("✅ Test Completed Successfully");
        System.out.println("=================================================");

    }
    @Test
    public void logJavaScriptErrorsInSeleniumScript(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
        // Now due to the JavaScript Bug we will receive the error message on JavaScript DOM
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER); // Get LogEntries Object
        List<LogEntry> logs = logEntries.getAll(); // .getAll() method returns all the logs in List format
        for (LogEntry e : logs){
            System.out.println(e.getMessage()); // To extract message in Log file we can use log4j
        }

    }
}
