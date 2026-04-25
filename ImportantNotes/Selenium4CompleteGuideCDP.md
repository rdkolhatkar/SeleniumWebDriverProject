# 🚀 Selenium 4 & Chrome DevTools Protocol (CDP) — Complete Integration Guide

> **From Zero to Advanced** — A structured, example-rich guide for freshers and experienced engineers alike.

---

## 📋 Table of Contents

1. [What Are Chrome DevTools & Why Do We Need Them for Selenium Testing?](#1-what-are-chrome-devtools--why-do-we-need-them-for-selenium-testing)
2. [Understanding Device Metrics Override — Simulating Mobile Browsers](#2-understanding-device-metrics-override--simulating-mobile-browsers)
3. [Importance of `executeCdpCommand` — Building Custom CDP Functions](#3-importance-of-executeсdpcommand--building-custom-cdp-functions)
4. [Localization Testing with Selenium 4 Using CDP](#4-localization-testing-with-selenium-4-using-cdp)
5. [Extracting Network Responses and Status Codes with CDP Listeners](#5-extracting-network-responses-and-status-codes-with-cdp-listeners)
6. [Intercept Network / API Responses with Selenium Chrome DevTools](#6-intercept-network--api-responses-with-selenium-chrome-devtools)
7. [Testing Failed Network Request Calls with Selenium CDP Commands](#7-testing-failed-network-request-calls-with-selenium-cdp-commands)
8. [Blocking Unwanted Network Requests to Speed Up Execution](#8-blocking-unwanted-network-requests-to-speed-up-execution)
9. [Emulating Network Speed with Selenium Chrome DevTools](#9-emulating-network-speed-with-selenium-chrome-devtools)
10. [Working with Basic Authentication Using `uriPredicate`](#10-working-with-basic-authentication-using-uripredicate)
11. [Logging JavaScript Errors to Console from Selenium Scripts](#11-logging-javascript-errors-to-console-from-selenium-scripts)

---

## 🧰 Prerequisites & Setup

Before diving in, ensure your environment is ready.

### Required Versions

| Tool/Library          | Minimum Version |
|-----------------------|-----------------|
| Java                  | 11+             |
| Selenium WebDriver    | 4.0.0+          |
| ChromeDriver          | Match your Chrome browser version |
| Chrome Browser        | 90+             |
| Maven / Gradle        | Any recent version |

### Maven `pom.xml` Dependencies

```xml
<dependencies>
    <!-- Selenium Java -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.18.1</version>
    </dependency>

    <!-- Selenium DevTools v85+ (version matches Chrome major version) -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-devtools-v130</artifactId>
        <version>4.18.1</version>
    </dependency>

    <!-- TestNG (optional, for test structure) -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

> ⚠️ **Version Note:** The `selenium-devtools-vXXX` artifact must match your installed Chrome major version.
> For example, Chrome 130 → `selenium-devtools-v130`. Always keep ChromeDriver and Chrome in sync.

### Base Setup — ChromeDriver + DevTools Session

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

public class BaseTest {

    protected ChromeDriver driver;
    protected DevTools devTools;

    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // uncomment for headless
        driver = new ChromeDriver(options);

        // Open a DevTools session
        devTools = driver.getDevTools();
        devTools.createSession();

        driver.manage().window().maximize();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

---

## 1. What Are Chrome DevTools & Why Do We Need Them for Selenium Testing?

### 🔍 What Are Chrome DevTools?

Chrome DevTools is a set of web developer tools built directly into the Google Chrome browser. You can open them by pressing **F12** or **Ctrl+Shift+I** (Windows/Linux) or **Cmd+Option+I** (Mac) in any Chrome window.

DevTools exposes a powerful set of panels:

| Panel       | Purpose |
|-------------|---------|
| Elements    | Inspect and modify HTML/CSS live |
| Console     | Run JavaScript, view logs and errors |
| Network     | Monitor HTTP requests, responses, headers, payloads |
| Performance | Analyze page load and rendering |
| Application | Inspect cookies, localStorage, service workers |
| Security    | Check HTTPS certificates and mixed content |
| Sources     | Debug JavaScript with breakpoints |

### 🤔 Why Do We Need DevTools in Selenium Testing?

Traditional Selenium WebDriver can interact with DOM elements and navigate pages. However, it **cannot** natively:

- Simulate a mobile device (different screen size, device pixel ratio)
- Mock or intercept network responses
- Simulate slow network conditions (3G, offline)
- Handle HTTP Basic Authentication elegantly
- Capture JavaScript console errors
- Listen to real-time network events
- Block specific network calls (ads, analytics, etc.)

This is where **Chrome DevTools Protocol (CDP)** comes in.

### 💡 What Is Chrome DevTools Protocol (CDP)?

CDP is a **communication protocol** that allows external tools to communicate with Chrome's internal DevTools engine. It uses **WebSockets** and works with **JSON-RPC** messages.

```
Your Test Script  ←→  CDP (WebSocket)  ←→  Chrome Browser Internals
```

Selenium 4 has **first-class native CDP integration** via the `DevTools` interface, meaning you no longer need third-party tools like `chrome-remote-interface`. Everything is available through the Selenium Java API directly.

### 🔑 Key CDP Domains You'll Use

| Domain    | What It Controls |
|-----------|------------------|
| `Emulation` | Device simulation, geolocation, locale |
| `Network`   | Request/response interception, throttling, blocking |
| `Log`       | Browser log capture (JS errors, warnings) |
| `Security`  | Certificate error handling |
| `Performance` | Performance metrics collection |
| `Browser`   | Browser-level operations |

### 📌 Real-World Use Cases

- **Cross-device testing**: Test your responsive UI for iPhone, Android without real devices.
- **API validation**: Assert that your front-end is making the right API calls with the right payloads.
- **Performance testing**: Simulate a 3G user and measure how long your site takes to load.
- **Resilience testing**: Simulate server failures (503, 404) without touching your backend.
- **Locale testing**: Test your app with different languages and timezones.
- **Debugging**: Capture JavaScript errors automatically in CI/CD pipelines.

---

## 2. Understanding Device Metrics Override — Simulating Mobile Browsers

### 🎯 What Is Device Metrics Override?

Device Metrics Override is a CDP feature under the `Emulation` domain that lets you **simulate any mobile device** by overriding:

- Screen **width** and **height**
- **Device Pixel Ratio (DPR)** — retina displays use 2x or 3x
- **Mobile mode** flag — tells the browser to behave like a mobile browser
- User Agent string — makes the server think it's talking to a mobile client

### 🧠 Why Is This Important?

When you test on a desktop browser, your page renders in desktop mode. Your responsive web application might look completely different — or even break — on a phone screen. Using Device Metrics Override, you can test the **exact same conditions** a real iPhone or Android user experiences, without needing physical devices.

### 📐 Common Device Specifications

| Device           | Width | Height | DPR | User Agent Snippet |
|------------------|-------|--------|-----|--------------------|
| iPhone 14 Pro    | 393   | 852    | 3.0 | iPhone; CPU iPhone OS 17_0 |
| Samsung Galaxy S21 | 360 | 800  | 3.0 | SM-G991B |
| iPad Pro 12.9"   | 1024  | 1366   | 2.0 | iPad |
| Pixel 7          | 412   | 915    | 2.6 | Pixel 7 |
| Generic Mobile   | 375   | 812    | 2.0 | mobile |

### ✅ Step-by-Step Example

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.emulation.Emulation;

import java.util.Optional;

public class MobileEmulationTest {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // -------------------------------------------------------
        // Step 1: Enable Emulation domain (required before calling
        //         setDeviceMetricsOverride)
        // -------------------------------------------------------

        // Step 2: Set Device Metrics Override
        devTools.send(Emulation.setDeviceMetricsOverride(
            393,                    // width in pixels (iPhone 14 Pro)
            852,                    // height in pixels
            3.0,                    // deviceScaleFactor (Device Pixel Ratio)
            true,                   // mobile flag — CRITICAL for mobile emulation
            Optional.empty(),       // scale (optional)
            Optional.empty(),       // screenWidth (optional)
            Optional.empty(),       // screenHeight (optional)
            Optional.empty(),       // positionX (optional)
            Optional.empty(),       // positionY (optional)
            Optional.empty(),       // dontSetVisibleSize (optional)
            Optional.empty(),       // screenOrientation (optional)
            Optional.empty(),       // viewport (optional)
            Optional.empty()        // displayFeature (optional)
        ));

        // Step 3: (Optional but recommended) Override User Agent to spoof mobile
        devTools.send(Emulation.setUserAgentOverride(
            "Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) "
            + "AppleWebKit/605.1.15 (KHTML, like Gecko) "
            + "Version/17.0 Mobile/15E148 Safari/604.1",
            Optional.empty(),     // acceptLanguage
            Optional.empty(),     // platform
            Optional.empty()      // userAgentMetadata
        ));

        // Step 4: Navigate to your application
        driver.get("https://www.amazon.in");

        // Step 5: Verify — take a screenshot to visually confirm mobile view
        // The page should now render as if on iPhone 14 Pro
        System.out.println("Page title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // You can now interact with mobile-specific elements:
        // driver.findElement(By.id("nav-hamburger-menu")).click();

        driver.quit();
    }
}
```

### 🔄 Portrait vs Landscape Orientation

```java
import org.openqa.selenium.devtools.v130.emulation.model.ScreenOrientation;
import org.openqa.selenium.devtools.v130.emulation.model.OrientationType;

// Switch to LANDSCAPE
devTools.send(Emulation.setDeviceMetricsOverride(
    852,   // width and height are SWAPPED for landscape
    393,
    3.0,
    true,
    Optional.empty(),
    Optional.empty(),
    Optional.empty(),
    Optional.empty(),
    Optional.empty(),
    Optional.empty(),
    Optional.of(new ScreenOrientation(OrientationType.LANDSCAPE_PRIMARY, 90)), // orientation
    Optional.empty(),
    Optional.empty()
));
```

### 🔧 Reset to Desktop Mode

```java
// Clears all device overrides — returns to normal desktop rendering
devTools.send(Emulation.clearDeviceMetricsOverride());
```

### ⚠️ Common Gotchas

- Always set `mobile = true` — without this, the page won't trigger mobile breakpoints even if width is small.
- User Agent override is **separate** from metrics override; you need both for full mobile simulation.
- Some sites detect emulation vs real devices; use a real device for final validation.
- `deviceScaleFactor = 1.0` means standard resolution. Retina displays use `2.0` or `3.0`.

---

## 3. Importance of `executeCdpCommand` — Building Custom CDP Functions

### 🔍 What Is `executeCdpCommand`?

Selenium 4's `ChromeDriver` exposes a method called `executeCdpCommand(String commandName, Map<String, Object> parameters)`. This is your **escape hatch** — it lets you send **any** CDP command directly to Chrome, even if Selenium hasn't wrapped it into a typed Java API yet.

```java
// Signature:
Map<String, Object> result = driver.executeCdpCommand("Domain.methodName", Map.of("param1", value1));
```

### 🤔 Why Is This Important?

The Selenium `DevTools` typed API (using `devTools.send(...)`) covers many CDP domains, but CDP has **hundreds of commands** across dozens of domains. The typed API can lag behind Chrome's latest CDP version. `executeCdpCommand` lets you:

- Use **any CDP command** regardless of whether Selenium has a typed wrapper.
- Build **helper/utility methods** that encapsulate complex multi-step CDP operations.
- Integrate with **newer CDP features** as soon as Chrome releases them.
- Keep your code **version-flexible** — no need to upgrade `selenium-devtools-vXXX` for every command.

### 📌 Anatomy of a CDP Command

Every CDP command follows this pattern:

```
Domain.Method
  └── Network.enable
  └── Emulation.setGeolocationOverride
  └── Browser.getVersion
  └── Performance.getMetrics
```

Parameters and return values are plain JSON-compatible Java Maps.

### ✅ Example 1 — Getting Browser Version Info

```java
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Map;

public class ExecuteCdpCommandExample {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        // CDP Command: Browser.getVersion
        // Returns: protocolVersion, product, revision, userAgent, jsVersion
        Map<String, Object> browserVersion = driver.executeCdpCommand(
            "Browser.getVersion",
            Map.of()    // No parameters needed
        );

        System.out.println("Protocol Version : " + browserVersion.get("protocolVersion"));
        System.out.println("Browser Product  : " + browserVersion.get("product"));
        System.out.println("User Agent       : " + browserVersion.get("userAgent"));
        System.out.println("JS Version       : " + browserVersion.get("jsVersion"));

        driver.quit();
    }
}

/* Expected Output:
   Protocol Version : 1.3
   Browser Product  : Chrome/130.0.0.0
   User Agent       : Mozilla/5.0 (Windows NT 10.0; Win64; x64)...
   JS Version       : 13.0.0.0
*/
```

### ✅ Example 2 — Setting Geolocation Override

```java
// Override browser's reported GPS coordinates
driver.executeCdpCommand(
    "Emulation.setGeolocationOverride",
    Map.of(
        "latitude",  19.0760,    // Nagpur, Maharashtra coordinates
        "longitude", 72.8777,
        "accuracy",  1           // GPS accuracy in meters
    )
);

driver.get("https://www.google.com/maps");
// Maps will now show the overridden location
```

### ✅ Example 3 — Building a Reusable CDP Utility Class

This is the **power pattern** — wrapping raw CDP commands into clean, reusable methods:

```java
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class wrapping common CDP commands for easy reuse across test suites.
 * Each method hides the raw CDP complexity behind a clean API.
 */
public class CdpCommandUtils {

    private final ChromeDriver driver;

    public CdpCommandUtils(ChromeDriver driver) {
        this.driver = driver;
    }

    /**
     * Enable Network domain event tracking.
     * Must be called before using any Network.* commands.
     */
    public void enableNetworkTracking() {
        driver.executeCdpCommand("Network.enable", Map.of());
        System.out.println("[CDP] Network tracking enabled.");
    }

    /**
     * Simulate a geographic location for the browser session.
     *
     * @param latitude  GPS latitude (e.g., 19.0760 for Mumbai)
     * @param longitude GPS longitude (e.g., 72.8777 for Mumbai)
     * @param accuracy  Accuracy in meters (use 1 for highest precision)
     */
    public void setGeoLocation(double latitude, double longitude, double accuracy) {
        Map<String, Object> params = new HashMap<>();
        params.put("latitude",  latitude);
        params.put("longitude", longitude);
        params.put("accuracy",  accuracy);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", params);
        System.out.printf("[CDP] Geolocation set → lat: %.4f, lng: %.4f%n", latitude, longitude);
    }

    /**
     * Clear geolocation override — restores real GPS.
     */
    public void clearGeoLocation() {
        driver.executeCdpCommand("Emulation.clearGeolocationOverride", Map.of());
    }

    /**
     * Override browser timezone.
     *
     * @param timezoneId IANA timezone identifier (e.g., "Asia/Kolkata", "America/New_York")
     */
    public void setTimezone(String timezoneId) {
        driver.executeCdpCommand(
            "Emulation.setTimezoneOverride",
            Map.of("timezoneId", timezoneId)
        );
        System.out.println("[CDP] Timezone overridden to: " + timezoneId);
    }

    /**
     * Collect performance metrics from Chrome.
     * Useful for lightweight performance assertions in functional tests.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Number> getPerformanceMetrics() {
        driver.executeCdpCommand("Performance.enable", Map.of());
        Map<String, Object> result = driver.executeCdpCommand("Performance.getMetrics", Map.of());

        Map<String, Number> metrics = new HashMap<>();
        // Result has a "metrics" key containing a list of {name, value} objects
        java.util.List<Map<String, Object>> metricsList =
            (java.util.List<Map<String, Object>>) result.get("metrics");

        for (Map<String, Object> metric : metricsList) {
            metrics.put(
                (String) metric.get("name"),
                (Number) metric.get("value")
            );
        }
        return metrics;
    }
}

// ---- Usage in your test: ----
// CdpCommandUtils cdp = new CdpCommandUtils(driver);
// cdp.enableNetworkTracking();
// cdp.setGeoLocation(28.6139, 77.2090, 1);  // New Delhi
// cdp.setTimezone("Asia/Kolkata");
// Map<String, Number> metrics = cdp.getPerformanceMetrics();
// System.out.println("DOM Nodes: " + metrics.get("Nodes"));
```

### ✅ Example 4 — Enabling Console Log Capture via Raw CDP

```java
// Enable the Log domain to receive browser log entries
driver.executeCdpCommand("Log.enable", Map.of());

// This is how you'd listen to them in combination with DevTools events
// (See Section 11 for the full event-listener pattern)
```

### 📌 When to Use `executeCdpCommand` vs `devTools.send()`

| Scenario | Recommended Approach |
|----------|----------------------|
| CDP command has a typed Selenium wrapper | `devTools.send(Domain.method(...))` — type-safe |
| Command not yet wrapped in Selenium API | `driver.executeCdpCommand(...)` — flexible |
| Need to listen to CDP events | `devTools.addListener(...)` — event-driven |
| Need to handle results as raw JSON | `driver.executeCdpCommand(...)` — returns `Map<String, Object>` |
| Building reusable utility methods | `driver.executeCdpCommand(...)` — easier to abstract |

---

## 4. Localization Testing with Selenium 4 Using CDP

### 🌍 What Is Localization Testing?

Localization testing (also called L10N testing) verifies that your application:

1. **Displays the correct language** for a given locale.
2. **Formats dates, numbers, and currencies** according to regional conventions.
3. **Handles right-to-left (RTL)** text for Arabic, Hebrew, etc.
4. **Shows the correct timezone** for the user's region.
5. **Accepts locale-specific input** (e.g., decimal comma in German: `1.234,56`).

### 🧩 How CDP Helps

Without CDP, simulating a French user in France required:

- Changing OS language (affects entire system)
- Using `ChromeOptions` `accept_languages` (only partial)
- Deploying separate browser profiles

With CDP, you can change **locale, timezone, and geolocation independently per test**, in the same browser session.

### ✅ Example 1 — Override Browser Locale and Language

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.emulation.Emulation;

import java.util.Optional;

public class LocalizationTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // -------------------------------------------------------
        // Simulate a FRENCH user in Paris
        // -------------------------------------------------------

        // Step 1: Set locale override (affects Intl.DateTimeFormat, Intl.NumberFormat, etc.)
        devTools.send(Emulation.setLocaleOverride(
            Optional.of("fr-FR")   // BCP 47 language tag: French (France)
        ));

        // Step 2: Set timezone override
        devTools.send(Emulation.setTimezoneOverride("Europe/Paris"));

        // Step 3: Set geolocation to Paris coordinates
        devTools.send(Emulation.setGeolocationOverride(
            Optional.of(48.8566),   // latitude  — Paris
            Optional.of(2.3522),    // longitude — Paris
            Optional.of(1.0)        // accuracy in meters
        ));

        // Step 4: Set Accept-Language header (tells server the user's language)
        devTools.send(Emulation.setUserAgentOverride(
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
            Optional.of("fr-FR,fr;q=0.9,en;q=0.8"),  // Accept-Language header
            Optional.empty(),
            Optional.empty()
        ));

        // Navigate and verify localization
        driver.get("https://www.google.com");
        Thread.sleep(2000);

        // Verify locale via JavaScript — the browser should report 'fr-FR'
        String locale = (String) driver.executeScript(
            "return Intl.DateTimeFormat().resolvedOptions().locale;"
        );
        System.out.println("Browser Locale: " + locale); // Expected: fr-FR

        // Verify timezone
        String timezone = (String) driver.executeScript(
            "return Intl.DateTimeFormat().resolvedOptions().timeZone;"
        );
        System.out.println("Browser Timezone: " + timezone); // Expected: Europe/Paris

        // Verify date format (French uses DD/MM/YYYY)
        String dateFormatted = (String) driver.executeScript(
            "return new Intl.DateTimeFormat('fr-FR').format(new Date());"
        );
        System.out.println("Date in fr-FR: " + dateFormatted); // e.g., 25/04/2026

        driver.quit();
    }
}
```

### ✅ Example 2 — Number and Currency Format Verification

```java
// Test that your app displays currency correctly for different locales

// German (Germany): 1.234,56 € — dot for thousands, comma for decimals
devTools.send(Emulation.setLocaleOverride(Optional.of("de-DE")));
devTools.send(Emulation.setTimezoneOverride("Europe/Berlin"));

driver.get("https://yourapp.com/product-price-page");

// Verify via JavaScript that browser formats numbers German-style
String germanNumber = (String) driver.executeScript(
    "return new Intl.NumberFormat('de-DE', {style: 'currency', currency: 'EUR'})"
    + ".format(1234.56);"
);
System.out.println("German Currency Format: " + germanNumber); 
// Expected: 1.234,56 €

// Japanese Yen — no decimals
devTools.send(Emulation.setLocaleOverride(Optional.of("ja-JP")));
String japaneseYen = (String) driver.executeScript(
    "return new Intl.NumberFormat('ja-JP', {style: 'currency', currency: 'JPY'})"
    + ".format(1234);"
);
System.out.println("Japanese Currency Format: " + japaneseYen);
// Expected: ¥1,234
```

### ✅ Example 3 — Data-Driven Localization Testing with TestNG

```java
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLocalizationTest extends BaseTest {

    @DataProvider(name = "localeData")
    public Object[][] getLocaleData() {
        return new Object[][] {
            // locale,   timezone,          lat,     lng,     expectedDateFormat
            { "en-US",  "America/New_York", 40.7128, -74.0060, "M/D/YYYY" },
            { "fr-FR",  "Europe/Paris",     48.8566,  2.3522,  "DD/MM/YYYY" },
            { "de-DE",  "Europe/Berlin",    52.5200, 13.4050,  "DD.MM.YYYY" },
            { "ja-JP",  "Asia/Tokyo",       35.6762,139.6503,  "YYYY/MM/DD" },
            { "ar-SA",  "Asia/Riyadh",      24.7136, 46.6753,  "DD/MM/YYYY" },
        };
    }

    @Test(dataProvider = "localeData")
    public void testLocalization(String locale, String timezone,
                                  double lat, double lng, String expectedFormat)
        throws InterruptedException {

        devTools.send(Emulation.setLocaleOverride(Optional.of(locale)));
        devTools.send(Emulation.setTimezoneOverride(timezone));
        devTools.send(Emulation.setGeolocationOverride(
            Optional.of(lat), Optional.of(lng), Optional.of(1.0)
        ));

        driver.get("https://yourapp.com/date-display-page");
        Thread.sleep(1000);

        String displayedDate = driver.findElement(By.id("displayed-date")).getText();
        System.out.printf("[%s / %s] Displayed date: %s%n", locale, timezone, displayedDate);

        // Add assertions matching your app's expected date format behavior
        // Assert.assertTrue(displayedDate.matches(...));
    }
}
```

### ⚠️ Localization Testing Checklist

- [ ] Date format matches locale (MM/DD vs DD/MM vs YYYY-MM-DD)
- [ ] Currency symbol and position correct ($ before vs ₹ before vs € after)
- [ ] Decimal separator correct (`.` vs `,`)
- [ ] Thousands separator correct (`,` vs `.` vs ` `)
- [ ] Text direction correct (LTR for English, RTL for Arabic/Hebrew)
- [ ] Timezone-sensitive content (event times, "today" labels) correct
- [ ] Language of UI strings correct (menus, buttons, errors)

---

## 5. Extracting Network Responses and Status Codes with CDP Listeners

### 🌐 What Are CDP Listeners?

CDP Listeners are **event subscribers** that receive real-time notifications from Chrome as it loads a page. Unlike `executeCdpCommand` (one-shot commands), listeners remain active throughout a test and fire a callback **every time** a matching event occurs.

Think of it like subscribing to a news feed: you register once, and Chrome sends you updates as they happen.

### 📡 Relevant CDP Events

| Event | Fires When |
|-------|-----------|
| `Network.requestWillBeSent` | Chrome is about to send an HTTP request |
| `Network.responseReceived` | Chrome received an HTTP response header |
| `Network.loadingFinished` | Response body fully downloaded |
| `Network.loadingFailed` | Request failed (network error, DNS failure, etc.) |

### 🔄 Event Flow

```
Browser Action
     ↓
Network.requestWillBeSent  → captures URL, method, headers, requestId
     ↓
Network.responseReceived   → captures status code, response headers
     ↓
Network.loadingFinished    → captures total bytes, timing
```

### ✅ Example — Capture All API Calls and Status Codes

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Response;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetworkResponseCaptureTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Thread-safe list to collect captured responses
        List<String> capturedLogs = new CopyOnWriteArrayList<>();

        // -------------------------------------------------------
        // Step 1: Enable the Network domain
        //         MUST be called before adding Network listeners
        // -------------------------------------------------------
        devTools.send(Network.enable(
            Optional.empty(),   // maxTotalBufferSize
            Optional.empty(),   // maxResourceBufferSize
            Optional.empty()    // maxPostDataSize
        ));

        // -------------------------------------------------------
        // Step 2: Listen to responseReceived events
        // -------------------------------------------------------
        devTools.addListener(Network.responseReceived(), responseReceived -> {

            Response response = responseReceived.getResponse();
            String url        = response.getUrl();
            int statusCode    = response.getStatus();
            String mimeType   = response.getMimeType();

            // Filter: only log API calls (JSON responses)
            if (url.contains("/api/") || mimeType.contains("application/json")) {
                String log = String.format(
                    "[API] %d | %s | MIME: %s",
                    statusCode, url, mimeType
                );
                capturedLogs.add(log);
                System.out.println(log);
            }
        });

        // -------------------------------------------------------
        // Step 3: Listen to failed requests separately
        // -------------------------------------------------------
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            String log = String.format(
                "[FAILED] RequestId: %s | ErrorText: %s | Canceled: %b",
                loadingFailed.getRequestId(),
                loadingFailed.getErrorText(),
                loadingFailed.getCanceled().orElse(false)
            );
            capturedLogs.add(log);
            System.out.println(log);
        });

        // -------------------------------------------------------
        // Step 4: Navigate — listeners fire automatically
        // -------------------------------------------------------
        driver.get("https://reqres.in/");

        // Simulate clicking a button that triggers API calls
        // driver.findElement(By.id("some-button")).click();

        Thread.sleep(3000); // Wait for all network activity to complete

        // -------------------------------------------------------
        // Step 5: Analyze captured logs
        // -------------------------------------------------------
        System.out.println("\n===== NETWORK SUMMARY =====");
        System.out.println("Total responses captured: " + capturedLogs.size());

        long successCount = capturedLogs.stream()
            .filter(log -> log.contains(" 200 ") || log.contains(" 201 "))
            .count();
        long errorCount = capturedLogs.stream()
            .filter(log -> log.matches(".*[45]\\d{2}.*"))
            .count();

        System.out.println("Successful (2xx): " + successCount);
        System.out.println("Errors (4xx/5xx): " + errorCount);

        driver.quit();
    }
}
```

### ✅ Example — Asserting a Specific API Returns 200

```java
import java.util.concurrent.atomic.AtomicInteger;

// Track status of a specific endpoint
AtomicInteger loginApiStatus = new AtomicInteger(-1);

devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

devTools.addListener(Network.responseReceived(), event -> {
    String url = event.getResponse().getUrl();
    if (url.contains("/api/v1/login")) {
        loginApiStatus.set(event.getResponse().getStatus());
    }
});

driver.get("https://yourapp.com");
driver.findElement(By.id("username")).sendKeys("testuser");
driver.findElement(By.id("password")).sendKeys("testpass");
driver.findElement(By.id("login-btn")).click();

Thread.sleep(2000);

// TestNG/JUnit assertion
Assert.assertEquals(loginApiStatus.get(), 200,
    "Login API should return 200 but got: " + loginApiStatus.get());
```

### 📋 Complete Status Code Reference in CDP Context

| HTTP Status | Category | What It Means in Testing |
|-------------|----------|--------------------------|
| 200         | OK | Normal success — always expected for GET |
| 201         | Created | POST/PUT succeeded in creating a resource |
| 204         | No Content | DELETE succeeded (no response body) |
| 301 / 302   | Redirect | May indicate broken URL or unexpected redirect |
| 400         | Bad Request | Your request payload is malformed — test data issue |
| 401         | Unauthorized | Token expired or missing — auth test case |
| 403         | Forbidden | User lacks permissions — RBAC test case |
| 404         | Not Found | API endpoint doesn't exist — routing bug |
| 500         | Server Error | Backend crash — negative/load test |
| 503         | Service Unavailable | Downstream service down — resilience test |

---

## 6. Intercept Network / API Responses with Selenium Chrome DevTools

### 🔄 What Is Response Interception?

Response interception allows you to **modify** or **completely replace** the response that Chrome returns from the server before JavaScript on the page processes it. This is fundamentally different from just **listening** (Section 5) — here, you're actively **changing** what the page sees.

### 🎯 Why Would You Intercept Responses?

- **Test error states** without breaking the real backend (simulate 500 error on login).
- **Mock API responses** to isolate front-end testing from back-end dependencies.
- **Test edge cases** like empty arrays, null values, or malformed JSON.
- **Speed up tests** by returning cached/fake responses instantly.
- **Verify UI behavior** when an API returns unexpected data.

### 🧰 CDP Approach: `Fetch` Domain

The `Fetch` domain in CDP is specifically designed for request/response interception. It works like a man-in-the-middle proxy inside Chrome.

```
Browser → [Fetch.requestPaused event] → Your Selenium Test
              ↓ (you decide)
         [Fulfill with mock] OR [Continue with real request]
```

### ✅ Example 1 — Intercept API and Return Mock Response

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.fetch.Fetch;
import org.openqa.selenium.devtools.v130.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v130.fetch.model.RequestStage;
import org.openqa.selenium.devtools.v130.network.model.ResourceType;

import java.util.*;

public class ResponseInterceptionTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // -------------------------------------------------------
        // Step 1: Define which URLs / request types to intercept
        // -------------------------------------------------------
        RequestPattern interceptPattern = new RequestPattern(
            Optional.of("**/api/users*"),  // URL pattern (glob-style)
            Optional.of(ResourceType.XHR), // Only intercept XHR/Fetch calls
            Optional.of(RequestStage.RESPONSE) // Intercept at RESPONSE stage
        );

        // Step 2: Enable Fetch interception
        devTools.send(Fetch.enable(
            Optional.of(List.of(interceptPattern)),
            Optional.empty()  // handleAuthRequests
        ));

        // -------------------------------------------------------
        // Step 3: Listen for paused requests and respond with mock
        // -------------------------------------------------------
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {

            String interceptedUrl = requestPaused.getRequest().getUrl();
            System.out.println("[INTERCEPTED] " + interceptedUrl);

            // Build a mock JSON response
            String mockResponseBody = "{"
                + "\"data\": ["
                + "  {\"id\": 999, \"email\": \"mock@test.com\", \"first_name\": \"Mock\", \"last_name\": \"User\"}"
                + "],"
                + "\"total\": 1,"
                + "\"mocked\": true"
                + "}";

            // Base64-encode the response body (required by CDP)
            String base64Body = Base64.getEncoder()
                .encodeToString(mockResponseBody.getBytes());

            // Fulfill the request with our mock response
            devTools.send(Fetch.fulfillRequest(
                requestPaused.getRequestId(),  // Which request to fulfill
                200,                           // Status code to return
                Optional.of(List.of(           // Response headers
                    new org.openqa.selenium.devtools.v130.fetch.model.HeaderEntry(
                        "Content-Type", "application/json"
                    )
                )),
                Optional.empty(),              // responsePhrase
                Optional.of(base64Body),       // responseBody (Base64)
                Optional.empty()               // binaryResponseHeaders
            ));

            System.out.println("[MOCK SENT] Returned mocked user data");
        });

        // -------------------------------------------------------
        // Step 4: Navigate — the interception fires automatically
        // -------------------------------------------------------
        driver.get("https://reqres.in/api/users?page=1");
        Thread.sleep(2000);

        // The page now sees "mocked@test.com" instead of real data
        System.out.println("Page source contains mock: "
            + driver.getPageSource().contains("mock@test.com"));

        driver.quit();
    }
}
```

### ✅ Example 2 — Conditional Interception (Pass Some, Mock Others)

```java
devTools.addListener(Fetch.requestPaused(), requestPaused -> {
    String url = requestPaused.getRequest().getUrl();

    if (url.contains("/api/products")) {
        // INTERCEPT: Return mock product list
        String mockProducts = "{\"products\": [{\"id\": 1, \"name\": \"Fake Product\"}]}";
        String base64 = Base64.getEncoder().encodeToString(mockProducts.getBytes());

        devTools.send(Fetch.fulfillRequest(
            requestPaused.getRequestId(), 200,
            Optional.of(List.of(
                new HeaderEntry("Content-Type", "application/json")
            )),
            Optional.empty(), Optional.of(base64), Optional.empty()
        ));
        System.out.println("[MOCKED] Products API");

    } else {
        // PASS THROUGH: Let all other requests go through normally
        devTools.send(Fetch.continueRequest(
            requestPaused.getRequestId(),
            Optional.empty(),   // url (don't change)
            Optional.empty(),   // method (don't change)
            Optional.empty(),   // postData (don't change)
            Optional.empty(),   // headers (don't change)
            Optional.empty()    // interceptResponse
        ));
        System.out.println("[PASS] " + url);
    }
});
```

### ✅ Example 3 — Modify Request Headers Before Sending

```java
// Intercept at REQUEST stage (not RESPONSE) to modify headers going out
RequestPattern requestPattern = new RequestPattern(
    Optional.of("**"),
    Optional.empty(),
    Optional.of(RequestStage.REQUEST)   // ← REQUEST stage
);

devTools.send(Fetch.enable(Optional.of(List.of(requestPattern)), Optional.empty()));

devTools.addListener(Fetch.requestPaused(), event -> {
    // Add a custom header to every outgoing request
    List<HeaderEntry> modifiedHeaders = new ArrayList<>(
        event.getResponseHeaders().orElse(Collections.emptyList())
    );
    modifiedHeaders.add(new HeaderEntry("X-Test-Header", "AutomationTest"));
    modifiedHeaders.add(new HeaderEntry("Authorization", "Bearer mock-token-12345"));

    devTools.send(Fetch.continueRequest(
        event.getRequestId(),
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        Optional.of(modifiedHeaders),   // ← Inject modified headers
        Optional.empty()
    ));
});
```

---

## 7. Testing Failed Network Request Calls with Selenium CDP Commands

### 💥 What Is Failed Request Simulation?

This technique allows you to **deliberately cause network requests to fail** — as if the server is down, DNS is broken, or the connection timed out. This is essential for testing your application's **error handling, retry logic, and fallback UIs**.

### 🎯 Why Test Failure Scenarios?

- Verify error messages are user-friendly (not raw stack traces).
- Confirm that loading spinners disappear even when API fails.
- Ensure retry buttons actually work.
- Test fallback content (cached data, skeleton screens).
- Validate that one failed API doesn't crash the entire page.

### 🛑 CDP Error Codes for Network Failures

| CDP Error Code       | Simulates |
|----------------------|-----------|
| `Failed`             | Generic network failure |
| `TimedOut`           | Connection timeout |
| `ConnectionRefused`  | Server actively refused |
| `NameNotResolved`    | DNS lookup failed |
| `InternetDisconnected` | No internet |
| `AddressUnreachable` | Network path broken |

### ✅ Example 1 — Fail a Specific API Endpoint

```java
import org.openqa.selenium.devtools.v130.fetch.Fetch;
import org.openqa.selenium.devtools.v130.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v130.network.model.ErrorReason;

public class FailedRequestTest extends BaseTest {

    @Test
    public void testAppBehaviorWhenLoginApiFails() throws InterruptedException {

        // Step 1: Enable Fetch interception for the login API only
        RequestPattern loginPattern = new RequestPattern(
            Optional.of("**/api/auth/login*"),
            Optional.of(ResourceType.XHR),
            Optional.of(RequestStage.REQUEST)
        );
        devTools.send(Fetch.enable(Optional.of(List.of(loginPattern)), Optional.empty()));

        // Step 2: When login API is called, fail it
        devTools.addListener(Fetch.requestPaused(), event -> {
            System.out.println("[FAILING] Request: " + event.getRequest().getUrl());

            devTools.send(Fetch.failRequest(
                event.getRequestId(),
                ErrorReason.FAILED   // Simulates a network-level failure
            ));
        });

        // Step 3: Perform login action in the app
        driver.get("https://yourapp.com/login");
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("testpass");
        driver.findElement(By.id("login-btn")).click();

        Thread.sleep(2000);

        // Step 4: Verify the app shows a proper error message
        WebElement errorMsg = driver.findElement(By.id("error-message"));
        Assert.assertTrue(errorMsg.isDisplayed(),
            "Error message should be displayed when login API fails");
        Assert.assertTrue(errorMsg.getText().contains("Unable to connect"),
            "Error text should be user-friendly");

        // Verify no raw stack trace is shown
        Assert.assertFalse(driver.getPageSource().contains("NullPointerException"),
            "Raw exception should NOT be exposed to user");
    }
}
```

### ✅ Example 2 — Simulate Connection Timeout

```java
devTools.addListener(Fetch.requestPaused(), event -> {
    if (event.getRequest().getUrl().contains("/api/data")) {
        devTools.send(Fetch.failRequest(
            event.getRequestId(),
            ErrorReason.TIMED_OUT   // Simulates request timeout
        ));
        System.out.println("[TIMEOUT SIMULATED] " + event.getRequest().getUrl());
    } else {
        devTools.send(Fetch.continueRequest(
            event.getRequestId(),
            Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty()
        ));
    }
});
```

### ✅ Example 3 — Simulate DNS Resolution Failure

```java
devTools.addListener(Fetch.requestPaused(), event -> {
    devTools.send(Fetch.failRequest(
        event.getRequestId(),
        ErrorReason.NAME_NOT_RESOLVED  // DNS lookup fails
    ));
});

driver.get("https://yourapp.com");
Thread.sleep(2000);

// Verify offline/error page is shown
String pageTitle = driver.getTitle();
System.out.println("Page title when DNS fails: " + pageTitle);
// Should show your app's custom "No connection" page, not Chrome's ERR_NAME_NOT_RESOLVED
```

### 📋 Test Matrix for Failure Scenarios

| Scenario | CDP Error Reason | What to Assert |
|----------|------------------|----------------|
| Login API fails | `FAILED` | Error message shown, form not reset |
| Product list timeout | `TIMED_OUT` | Loading spinner gone, retry button visible |
| Search API DNS fail | `NAME_NOT_RESOLVED` | Graceful empty state, no crash |
| Payment gateway down | `CONNECTION_REFUSED` | Payment not charged, friendly message |
| File upload interrupted | `INTERNET_DISCONNECTED` | Upload cancelled, data preserved |

---

## 8. Blocking Unwanted Network Requests to Speed Up Execution

### 🚀 Why Block Network Requests?

When Selenium loads a typical e-commerce or news site, the browser makes **dozens to hundreds** of network requests. Many of them are useless for testing:

- **Ad networks** (DoubleClick, AdSense, etc.)
- **Analytics** (Google Analytics, Hotjar, Mixpanel)
- **Social widgets** (Facebook Like button, Twitter embed)
- **Chat widgets** (Intercom, Zendesk, Drift)
- **Video embeds** (YouTube, Vimeo thumbnails)
- **Fonts** from external CDNs

These requests can slow page load by **3–10 seconds** unnecessarily.

### 📊 Impact of Blocking

| Request Type | Typical Count | Typical Size | Time Savings |
|--------------|---------------|--------------|--------------|
| Google Analytics | 2–5 | ~50KB | 200–500ms |
| Ad scripts | 5–20 | 200KB–2MB | 1–5s |
| Social widgets | 3–8 | ~150KB | 300ms–1s |
| Chat widgets | 2–4 | ~500KB | 500ms–2s |
| **Total savings** | | | **2–9 seconds per test** |

For a test suite with 100 tests, that's **3–15 minutes saved**.

### ✅ Example 1 — Block Ads and Analytics Using `Network.setBlockedURLs`

```java
import org.openqa.selenium.devtools.v130.network.Network;

public class BlockNetworkRequestsTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Step 1: Enable Network domain
        devTools.send(Network.enable(
            Optional.empty(), Optional.empty(), Optional.empty()
        ));

        // Step 2: Define URL patterns to block (glob-style wildcards)
        List<String> urlsToBlock = Arrays.asList(
            // Google Analytics & Tag Manager
            "*google-analytics.com*",
            "*googletagmanager.com*",
            "*googletagservices.com*",

            // Facebook / Meta
            "*connect.facebook.net*",
            "*facebook.com/tr*",

            // Twitter / X
            "*platform.twitter.com*",
            "*syndication.twitter.com*",

            // Advertising networks
            "*doubleclick.net*",
            "*ads.pubmatic.com*",
            "*adsystem.amazon.com*",
            "*adservice.google.com*",

            // Hotjar
            "*hotjar.com*",

            // Intercom chat
            "*intercom.io*",
            "*widget.intercom.io*",

            // Mixpanel analytics
            "*mixpanel.com*",

            // Fonts (optional — only block if your test doesn't need font rendering)
            // "*fonts.googleapis.com*",

            // Images (optional — block to speed up non-visual tests)
            // "*.jpg", "*.jpeg", "*.png", "*.gif", "*.webp"
        );

        // Step 3: Apply the blocking rules
        devTools.send(Network.setBlockedURLs(urlsToBlock));
        System.out.println("[CDP] Blocking " + urlsToBlock.size() + " URL patterns");

        // Step 4: Navigate and time it
        long startTime = System.currentTimeMillis();
        driver.get("https://www.amazon.in");
        long loadTime = System.currentTimeMillis() - startTime;

        System.out.println("Page loaded in: " + loadTime + " ms (with blocking)");

        // Step 5: Verify no blocked scripts executed
        // Analytics should not have set any cookies
        driver.manage().getCookies().forEach(cookie -> {
            System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
        });

        driver.quit();
    }
}
```

### ✅ Example 2 — Block Images for Pure Content Testing

```java
// Block all image requests when testing text content or form flows
// This dramatically speeds up tests that don't need visual content

List<String> imagePatterns = Arrays.asList(
    "*.jpg", "*.jpeg", "*.png", "*.gif", "*.webp", "*.svg",
    "*.ico", "*.bmp", "*.tiff"
);

devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
devTools.send(Network.setBlockedURLs(imagePatterns));

driver.get("https://yourapp.com/checkout");
// Checkout form loads almost instantly without waiting for product images
```

### ✅ Example 3 — Measure Performance Before and After Blocking

```java
public class PerformanceComparisonTest {

    public long measurePageLoad(ChromeDriver driver, boolean withBlocking)
        throws InterruptedException {

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        if (withBlocking) {
            devTools.send(Network.setBlockedURLs(Arrays.asList(
                "*google-analytics.com*",
                "*doubleclick.net*",
                "*hotjar.com*",
                "*facebook.com*"
            )));
        }

        long start = System.currentTimeMillis();
        driver.get("https://www.yoursite.com");
        // Wait for page to be fully loaded
        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        long duration = System.currentTimeMillis() - start;

        driver.quit();
        return duration;
    }

    @Test
    public void compareLoadTimes() throws Exception {
        long withoutBlocking = measurePageLoad(new ChromeDriver(), false);
        long withBlocking    = measurePageLoad(new ChromeDriver(), true);

        System.out.printf("Without blocking: %d ms%n", withoutBlocking);
        System.out.printf("With blocking:    %d ms%n", withBlocking);
        System.out.printf("Time saved:       %d ms (%.1f%% faster)%n",
            withoutBlocking - withBlocking,
            ((double)(withoutBlocking - withBlocking) / withoutBlocking) * 100
        );
    }
}
```

### ✅ Example 4 — Dynamic Blocking Based on Test Type

```java
/**
 * Use this utility in your BaseTest to selectively block
 * resources based on the type of test being run.
 */
public enum TestMode {
    VISUAL,        // Don't block images — screenshot comparison tests
    FUNCTIONAL,    // Block ads/analytics — standard functional tests
    PERFORMANCE    // Block everything non-essential — pure speed measurement
}

public void configureNetworkBlocking(TestMode mode) {
    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

    switch (mode) {
        case VISUAL:
            // Only block tracking scripts, keep images
            devTools.send(Network.setBlockedURLs(Arrays.asList(
                "*google-analytics.com*", "*hotjar.com*"
            )));
            break;

        case FUNCTIONAL:
            // Block ads, analytics, social (default for most tests)
            devTools.send(Network.setBlockedURLs(Arrays.asList(
                "*google-analytics.com*", "*doubleclick.net*",
                "*facebook.com/tr*", "*hotjar.com*", "*mixpanel.com*"
            )));
            break;

        case PERFORMANCE:
            // Block everything except your app's own resources
            devTools.send(Network.setBlockedURLs(Arrays.asList(
                "*google-analytics.com*", "*doubleclick.net*",
                "*facebook.com*", "*twitter.com*", "*hotjar.com*",
                "*mixpanel.com*", "*intercom.io*", "*.jpg", "*.png", "*.gif"
            )));
            break;
    }
}
```

---

## 9. Emulating Network Speed with Selenium Chrome DevTools

### 📶 What Is Network Throttling?

Network throttling lets you simulate **slow internet connections** by artificially limiting:

- **Download speed** (how fast data arrives from server to browser)
- **Upload speed** (how fast data goes from browser to server)
- **Latency** (how many milliseconds before a connection is established — "ping")

### 🌍 Why Test With Slow Networks?

- India has millions of users on 2G/3G networks in rural areas.
- Your app may feel instant on the office WiFi but unusable on mobile data.
- Core Web Vitals metrics (LCP, FID, CLS) are meaningless without realistic conditions.
- Regulatory compliance may require apps to be usable under poor network conditions.

### 📊 Common Network Profiles

| Profile        | Download Speed | Upload Speed | Latency |
|----------------|---------------|--------------|---------|
| No Throttling  | Unlimited     | Unlimited    | 0ms     |
| Fast 3G        | 1.5 Mbps      | 750 Kbps     | 562ms   |
| Slow 3G        | 500 Kbps      | 500 Kbps     | 2000ms  |
| 2G             | 250 Kbps      | 50 Kbps      | 300ms   |
| GPRS           | 50 Kbps       | 20 Kbps      | 500ms   |
| Regular 4G     | 4 Mbps        | 3 Mbps       | 20ms    |
| WiFi           | 30 Mbps       | 15 Mbps      | 2ms     |
| **Offline**    | 0             | 0            | —       |

*All speeds are in bits per second — CDP uses bytes per second internally.*

### ✅ Example 1 — Simulate Slow 3G

```java
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.ConnectionType;

public class NetworkThrottlingTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable Network domain
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // -------------------------------------------------------
        // Configure Slow 3G throttling
        // Note: downloadThroughput and uploadThroughput are in BYTES/second
        //       latency is in milliseconds
        // -------------------------------------------------------
        devTools.send(Network.emulateNetworkConditions(
            false,          // offline — false means we have a connection
            2000,           // latency in ms (2 seconds = very slow connection)
            62500,          // downloadThroughput: 500 Kbps ÷ 8 = 62,500 bytes/sec
            62500,          // uploadThroughput:  500 Kbps ÷ 8 = 62,500 bytes/sec
            Optional.of(ConnectionType.CELLULAR3G)  // connection type hint
        ));

        System.out.println("[CDP] Throttled to: Slow 3G (500 Kbps, 2000ms latency)");

        // Measure load time under slow network
        long start = System.currentTimeMillis();
        driver.get("https://www.flipkart.com");
        new WebDriverWait(driver, Duration.ofSeconds(60))
            .until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
        long loadTime = System.currentTimeMillis() - start;

        System.out.println("Page loaded in: " + loadTime + " ms on Slow 3G");

        // Assert the page still loads within acceptable SLA even on slow network
        Assert.assertTrue(loadTime < 30000,
            "Page should load within 30 seconds even on 3G, but took: " + loadTime + "ms");

        driver.quit();
    }
}
```

### ✅ Example 2 — Test Offline Behavior

```java
// Simulate complete offline (airplane mode)
devTools.send(Network.emulateNetworkConditions(
    true,           // offline = TRUE — no internet
    0,              // latency
    0,              // downloadThroughput
    0,              // uploadThroughput
    Optional.empty()
));

driver.get("https://yourapp.com");
Thread.sleep(2000);

// Verify Service Worker / PWA offline page is shown
String pageContent = driver.getPageSource();
boolean showsOfflinePage =
    pageContent.contains("You're offline") ||
    pageContent.contains("No internet connection") ||
    pageContent.contains("offline");

Assert.assertTrue(showsOfflinePage,
    "App should show offline page when there's no internet");

// Restore connection
devTools.send(Network.emulateNetworkConditions(
    false, 0,
    -1,  // -1 means "unlimited" — remove throttling
    -1,
    Optional.empty()
));
System.out.println("Connection restored to normal");
```

### ✅ Example 3 — Complete Network Profile Utility

```java
/**
 * Reusable network throttling profiles.
 * Use these in any test that needs to simulate realistic network conditions.
 */
public class NetworkThrottleUtils {

    private final DevTools devTools;

    public NetworkThrottleUtils(DevTools devTools) {
        this.devTools = devTools;
    }

    public void setFast3G() {
        throttle(562, 188_000, 94_000, ConnectionType.CELLULAR3G);
        System.out.println("[NETWORK] Set to Fast 3G");
    }

    public void setSlow3G() {
        throttle(2000, 62_500, 62_500, ConnectionType.CELLULAR3G);
        System.out.println("[NETWORK] Set to Slow 3G");
    }

    public void set2G() {
        throttle(300, 31_250, 6_250, ConnectionType.CELLULAR2G);
        System.out.println("[NETWORK] Set to 2G");
    }

    public void set4G() {
        throttle(20, 500_000, 375_000, ConnectionType.CELLULAR4G);
        System.out.println("[NETWORK] Set to 4G");
    }

    public void setWiFi() {
        throttle(2, 3_750_000, 1_875_000, ConnectionType.WIFI);
        System.out.println("[NETWORK] Set to WiFi");
    }

    public void setOffline() {
        devTools.send(Network.emulateNetworkConditions(
            true, 0, 0, 0, Optional.empty()
        ));
        System.out.println("[NETWORK] Set to OFFLINE");
    }

    public void clearThrottling() {
        devTools.send(Network.emulateNetworkConditions(
            false, 0, -1, -1, Optional.empty()
        ));
        System.out.println("[NETWORK] Throttling cleared — back to full speed");
    }

    private void throttle(int latencyMs, long downloadBps,
                           long uploadBps, ConnectionType type) {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
            false, latencyMs, downloadBps, uploadBps, Optional.of(type)
        ));
    }
}

// Usage in tests:
// NetworkThrottleUtils network = new NetworkThrottleUtils(devTools);
// network.setSlow3G();
// driver.get("https://yourapp.com");
// // ... run assertions ...
// network.clearThrottling();
```

---

## 10. Working with Basic Authentication Using `uriPredicate`

### 🔐 What Is HTTP Basic Authentication?

HTTP Basic Authentication is a challenge-response mechanism where:

1. Browser requests a URL.
2. Server responds with `401 Unauthorized` + `WWW-Authenticate: Basic realm="..."` header.
3. Browser shows a native popup asking for username/password.
4. Browser sends credentials as `Authorization: Basic base64(username:password)`.

The problem: **Selenium cannot interact with native OS dialog boxes**, so you cannot type into the browser's built-in Basic Auth popup.

### 🛠️ Old Workaround vs CDP Approach

| Old Approach | Problem |
|--------------|---------|
| Embed credentials in URL: `https://user:pass@site.com` | Deprecated in modern Chrome (security concern) |
| Alert handling | Basic Auth dialog is NOT a JavaScript alert — Selenium cannot interact |
| ChromeOptions proxy | Complex setup, brittle |
| **CDP `registerBasicAuth` with `uriPredicate`** | ✅ Clean, built-in Selenium 4 support |

### ✅ Example 1 — `HasAuthentication.register()` (Recommended Selenium 4 Way)

Selenium 4 introduced `HasAuthentication` interface which wraps the CDP `Fetch` domain's auth handling:

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.bidi.network.AddInterceptParameters;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;
import java.util.function.Predicate;

public class BasicAuthCdpTest {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();

        // -------------------------------------------------------
        // Approach 1: Register credentials for ALL requests
        // -------------------------------------------------------
        ((org.openqa.selenium.HasAuthentication) driver)
            .register(UsernameAndPassword.of("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        // Verify authentication succeeded
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Congratulations"),
            "Basic Auth should succeed with registered credentials");

        System.out.println("Basic Auth passed! Page: " + driver.getTitle());
        driver.quit();
    }
}
```

### ✅ Example 2 — `uriPredicate` — Selective Credential Registration

The `uriPredicate` function lets you specify **which URLs** the credentials should apply to. This is critical when your page has mixed content — some endpoints need auth, others don't.

```java
import java.net.URI;
import java.util.function.Predicate;

public class UriPredicateAuthTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        // -------------------------------------------------------
        // uriPredicate: Only apply credentials when URL matches
        // -------------------------------------------------------

        // Example 1: Match a specific host
        Predicate<URI> onlyHerokuApp = uri ->
            uri.getHost().equals("the-internet.herokuapp.com");

        ((org.openqa.selenium.HasAuthentication) driver)
            .register(onlyHerokuApp, UsernameAndPassword.of("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);
        System.out.println("Heroku Auth: " + driver.getTitle());

        // -------------------------------------------------------
        // Example 2: Match by URL path pattern
        // -------------------------------------------------------
        Predicate<URI> protectedApiOnly = uri ->
            uri.getPath().startsWith("/api/admin") ||
            uri.getPath().startsWith("/secure");

        ((org.openqa.selenium.HasAuthentication) driver)
            .register(protectedApiOnly, UsernameAndPassword.of("apiuser", "apipass"));

        // -------------------------------------------------------
        // Example 3: Match multiple environments
        // -------------------------------------------------------
        Predicate<URI> anyTestEnv = uri ->
            uri.getHost().contains("staging.") ||
            uri.getHost().contains("uat.") ||
            uri.getHost().contains("dev.");

        ((org.openqa.selenium.HasAuthentication) driver)
            .register(anyTestEnv, UsernameAndPassword.of("testuser", "testpass"));

        driver.quit();
    }
}
```

### ✅ Example 3 — CDP Raw Approach Using `Fetch.enable` with Auth Handling

For maximum control (e.g., when credentials differ per request, or when you need to test failed auth):

```java
import org.openqa.selenium.devtools.v130.fetch.Fetch;
import org.openqa.selenium.devtools.v130.fetch.model.AuthChallengeResponse;
import org.openqa.selenium.devtools.v130.fetch.model.AuthChallengeResponseResponse;

public class RawCdpBasicAuthTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Step 1: Enable Fetch with handleAuthRequests = true
        devTools.send(Fetch.enable(
            Optional.empty(),
            Optional.of(true)   // handleAuthRequests = TRUE — enables auth challenge events
        ));

        // Step 2: Handle auth challenges
        devTools.addListener(Fetch.authRequired(), authRequired -> {

            System.out.println("[AUTH] Challenge received for: "
                + authRequired.getRequest().getUrl());
            System.out.println("[AUTH] Auth source: "
                + authRequired.getAuthChallenge().getSource().name());

            // Respond with credentials
            devTools.send(Fetch.continueWithAuth(
                authRequired.getRequestId(),
                new AuthChallengeResponse(
                    AuthChallengeResponseResponse.PROVIDE_CREDENTIALS,
                    Optional.of("admin"),   // username
                    Optional.of("admin")    // password
                )
            ));
        });

        // Step 3: Also handle regular non-auth requests (must continue them)
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            // If no auth challenge, just continue the request normally
            if (!requestPaused.getAuthChallenge().isPresent()) {
                devTools.send(Fetch.continueRequest(
                    requestPaused.getRequestId(),
                    Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty()
                ));
            }
        });

        // Step 4: Navigate to protected URL
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);

        Assert.assertTrue(driver.getPageSource().contains("Congratulations"),
            "Should authenticate successfully");

        driver.quit();
    }
}
```

### ✅ Example 4 — Test What Happens With Wrong Credentials

```java
// Test the app's behavior when Basic Auth credentials are WRONG
devTools.addListener(Fetch.authRequired(), authRequired -> {
    devTools.send(Fetch.continueWithAuth(
        authRequired.getRequestId(),
        new AuthChallengeResponse(
            AuthChallengeResponseResponse.PROVIDE_CREDENTIALS,
            Optional.of("wronguser"),    // ← Wrong credentials
            Optional.of("wrongpass")
        )
    ));
});

driver.get("https://the-internet.herokuapp.com/basic_auth");
Thread.sleep(2000);

// Verify the auth failure is handled gracefully
Assert.assertFalse(driver.getPageSource().contains("Congratulations"),
    "Wrong credentials should NOT grant access");
Assert.assertTrue(
    driver.getPageSource().contains("Not authorized") ||
    driver.getCurrentUrl().contains("401"),
    "Should show 401 or authorization error page"
);
```

---

## 11. Logging JavaScript Errors to Console from Selenium Scripts

### 🐛 Why Capture JavaScript Errors?

JavaScript errors in the browser don't automatically fail your Selenium tests. A button click might be silently broken due to a JS error, while Selenium happily reports "click succeeded." Without JS error capture:

- Real bugs get missed in CI/CD pipelines.
- Intermittent errors are never diagnosed.
- You find out about JS errors from production users, not your test suite.

With CDP JS error logging, you can **automatically fail tests when the page has JavaScript errors**, making your test suite a much stronger safety net.

### 📋 Types of JS Events CDP Can Capture

| Event | What It Captures |
|-------|-----------------|
| `Log.entryAdded` | Console messages: `console.log`, `console.warn`, `console.error` |
| `Runtime.exceptionThrown` | Uncaught exceptions (the most critical ones) |
| `Runtime.consoleAPICalled` | Every `console.*` call with full arguments |

### ✅ Example 1 — Capture All Console Logs and Errors

```java
import org.openqa.selenium.devtools.v130.log.Log;

public class JavaScriptErrorCaptureTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Thread-safe collections for captured logs
        List<String> jsErrors   = new CopyOnWriteArrayList<>();
        List<String> jsWarnings = new CopyOnWriteArrayList<>();
        List<String> jsLogs     = new CopyOnWriteArrayList<>();

        // -------------------------------------------------------
        // Step 1: Enable the Log domain
        // -------------------------------------------------------
        devTools.send(Log.enable());

        // -------------------------------------------------------
        // Step 2: Listen for log entries
        // -------------------------------------------------------
        devTools.addListener(Log.entryAdded(), logEntry -> {

            String level   = logEntry.getEntry().getLevel().toString();
            String message = logEntry.getEntry().getText();
            String source  = logEntry.getEntry().getSource().toString();

            String formatted = String.format("[%s][%s] %s", level.toUpperCase(), source, message);
            System.out.println(formatted);

            switch (level.toLowerCase()) {
                case "error":
                    jsErrors.add(formatted);
                    break;
                case "warning":
                    jsWarnings.add(formatted);
                    break;
                default:
                    jsLogs.add(formatted);
            }
        });

        // -------------------------------------------------------
        // Step 3: Navigate
        // -------------------------------------------------------
        driver.get("https://yourapp.com");
        Thread.sleep(3000);

        // -------------------------------------------------------
        // Step 4: Report and assert
        // -------------------------------------------------------
        System.out.println("\n===== JS ERROR REPORT =====");
        System.out.println("Errors  : " + jsErrors.size());
        System.out.println("Warnings: " + jsWarnings.size());
        System.out.println("Logs    : " + jsLogs.size());

        if (!jsErrors.isEmpty()) {
            System.out.println("\n--- ERRORS ---");
            jsErrors.forEach(System.out::println);
        }

        // Fail the test if there are JavaScript errors
        Assert.assertEquals(jsErrors.size(), 0,
            "Page should have zero JS errors but found: \n" + String.join("\n", jsErrors));

        driver.quit();
    }
}
```

### ✅ Example 2 — Capture Uncaught Runtime Exceptions (Most Critical)

```java
import org.openqa.selenium.devtools.v130.runtime.Runtime;

public class UncaughtExceptionTest extends BaseTest {

    @Test
    public void testNoUncaughtExceptions() throws InterruptedException {

        List<String> uncaughtExceptions = new CopyOnWriteArrayList<>();

        // Enable Runtime domain
        devTools.send(Runtime.enable());

        // Listen for uncaught exceptions — these are the most severe JS errors
        devTools.addListener(Runtime.exceptionThrown(), exceptionThrown -> {

            var exceptionDetails = exceptionThrown.getExceptionDetails();
            String errorMessage  = exceptionDetails.getText();
            int lineNumber       = exceptionDetails.getLineNumber();
            int columnNumber     = exceptionDetails.getColumnNumber();
            String scriptUrl     = exceptionDetails.getUrl().orElse("unknown");

            String formatted = String.format(
                "[UNCAUGHT EXCEPTION] %s | at %s:%d:%d",
                errorMessage, scriptUrl, lineNumber, columnNumber
            );

            uncaughtExceptions.add(formatted);
            System.err.println(formatted);

            // Optional: get the full stack trace
            exceptionDetails.getStackTrace().ifPresent(stack -> {
                stack.getCallFrames().forEach(frame ->
                    System.err.println("  at " + frame.getFunctionName()
                        + " (" + frame.getUrl() + ":" + frame.getLineNumber() + ")")
                );
            });
        });

        // Perform test actions
        driver.get("https://yourapp.com");
        driver.findElement(By.id("some-button")).click();
        Thread.sleep(2000);

        // Assert no uncaught exceptions occurred
        Assert.assertTrue(uncaughtExceptions.isEmpty(),
            "No uncaught JS exceptions expected. Found:\n"
            + String.join("\n", uncaughtExceptions));
    }
}
```

### ✅ Example 3 — Inject Custom JavaScript and Capture Its Output

```java
// Inject JS that logs to console, then capture it via CDP
devTools.send(Runtime.enable());

List<String> capturedOutput = new CopyOnWriteArrayList<>();

devTools.addListener(Runtime.consoleAPICalled(), consoleCall -> {
    String type = consoleCall.getType().toString();  // log, warn, error, info, etc.

    String output = consoleCall.getArgs().stream()
        .map(arg -> arg.getValue().map(Object::toString).orElse("[object]"))
        .reduce("", (a, b) -> a + " " + b)
        .trim();

    capturedOutput.add("[" + type.toUpperCase() + "] " + output);
    System.out.println("[CDP CONSOLE] " + type + ": " + output);
});

// Execute JS that generates console output
driver.get("https://yourapp.com");
driver.executeScript("console.log('Test started at: ' + new Date().toISOString());");
driver.executeScript("console.warn('Deprecated API in use');");
driver.executeScript("console.error('Simulated test error for CDP capture');");

Thread.sleep(1000);

// Verify specific log messages were captured
Assert.assertTrue(
    capturedOutput.stream().anyMatch(log -> log.contains("Test started at")),
    "Custom console.log should be captured"
);
Assert.assertTrue(
    capturedOutput.stream().anyMatch(log -> log.contains("Simulated test error")),
    "Injected console.error should be captured"
);
```

### ✅ Example 4 — Complete JS Error Monitoring in BaseTest (Production-Ready Pattern)

```java
/**
 * Add JS error monitoring to your BaseTest so EVERY test automatically
 * checks for console errors. No special code needed in individual tests.
 */
public class BaseTest {

    protected ChromeDriver driver;
    protected DevTools devTools;
    protected List<String> jsConsoleErrors;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        devTools = driver.getDevTools();
        devTools.createSession();
        jsConsoleErrors = new CopyOnWriteArrayList<>();

        // Enable both Log and Runtime domains
        devTools.send(Log.enable());
        devTools.send(Runtime.enable());

        // Capture console errors via Log domain
        devTools.addListener(Log.entryAdded(), entry -> {
            if ("error".equalsIgnoreCase(entry.getEntry().getLevel().toString())) {
                jsConsoleErrors.add("[LOG ERROR] " + entry.getEntry().getText());
            }
        });

        // Capture uncaught exceptions via Runtime domain
        devTools.addListener(Runtime.exceptionThrown(), ex -> {
            jsConsoleErrors.add(
                "[UNCAUGHT] " + ex.getExceptionDetails().getText()
                + " at line " + ex.getExceptionDetails().getLineNumber()
            );
        });
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // If test passed but there were JS errors, fail it
        if (result.getStatus() == ITestResult.SUCCESS && !jsConsoleErrors.isEmpty()) {
            System.err.println("\n⚠️  Test passed but JS errors were found:");
            jsConsoleErrors.forEach(e -> System.err.println("  " + e));
            // Uncomment to enforce zero JS errors policy:
            // result.setStatus(ITestResult.FAILURE);
        }

        if (driver != null) driver.quit();
    }
}
```

### 📌 JS Error Severity Guide

| Source | Severity | Action |
|--------|----------|--------|
| `console.error` | High | Should fail test |
| Uncaught exceptions | Critical | Must fail test |
| `console.warn` | Medium | Log and review |
| `console.log` | Low / Info | Capture for debugging only |
| Network errors (CORS, 404) | High | Fail test if blocking |

---

## 🏗️ Complete Integration Example — All Concepts Together

The following example shows how these CDP features combine in a realistic end-to-end test scenario:

```java
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.emulation.Emulation;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.log.Log;
import org.openqa.selenium.devtools.v130.runtime.Runtime;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * COMPLETE INTEGRATION TEST — E-commerce mobile checkout flow
 * Combines: Mobile emulation + Locale + Network capture +
 *           Slow 3G + Request blocking + JS error monitoring
 */
public class CompleteIntegrationTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        List<String> jsErrors        = new CopyOnWriteArrayList<>();
        List<String> apiResponses    = new CopyOnWriteArrayList<>();

        // ===== 1. SIMULATE MOBILE DEVICE (iPhone 14 Pro) =====
        devTools.send(Emulation.setDeviceMetricsOverride(
            393, 852, 3.0, true,
            Optional.empty(), Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty(),
            Optional.empty(), Optional.empty(), Optional.empty()
        ));
        devTools.send(Emulation.setUserAgentOverride(
            "Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) "
            + "AppleWebKit/605.1.15 Mobile/15E148 Safari/604.1",
            Optional.of("en-IN,en;q=0.9,hi;q=0.8"),
            Optional.empty(), Optional.empty()
        ));

        // ===== 2. SET INDIAN LOCALE + TIMEZONE =====
        devTools.send(Emulation.setLocaleOverride(Optional.of("en-IN")));
        devTools.send(Emulation.setTimezoneOverride("Asia/Kolkata"));
        devTools.send(Emulation.setGeolocationOverride(
            Optional.of(19.0760), Optional.of(72.8777), Optional.of(1.0)
        ));

        // ===== 3. ENABLE NETWORK + BLOCK ADS =====
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(Arrays.asList(
            "*google-analytics.com*", "*doubleclick.net*",
            "*facebook.com/tr*", "*hotjar.com*"
        )));

        // ===== 4. SIMULATE SLOW 4G (Realistic Indian mobile data) =====
        devTools.send(Network.emulateNetworkConditions(
            false, 100, 500_000, 375_000,
            Optional.of(org.openqa.selenium.devtools.v130.network.model.ConnectionType.CELLULAR4G)
        ));

        // ===== 5. CAPTURE API RESPONSES =====
        devTools.addListener(Network.responseReceived(), event -> {
            if (event.getResponse().getUrl().contains("/api/")) {
                apiResponses.add(
                    event.getResponse().getStatus() + " | "
                    + event.getResponse().getUrl()
                );
            }
        });

        // ===== 6. MONITOR JS ERRORS =====
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(), entry -> {
            if ("error".equalsIgnoreCase(entry.getEntry().getLevel().toString())) {
                jsErrors.add(entry.getEntry().getText());
            }
        });

        // ===== 7. BASIC AUTH (if app is staging-protected) =====
        ((org.openqa.selenium.HasAuthentication) driver)
            .register(
                uri -> uri.getHost().contains("staging.yourapp.com"),
                UsernameAndPassword.of("stageuser", "stagepass")
            );

        // ===== 8. RUN THE ACTUAL TEST =====
        long start = System.currentTimeMillis();
        driver.get("https://staging.yourapp.com");
        Thread.sleep(5000);
        long pageLoadTime = System.currentTimeMillis() - start;

        // ===== 9. ASSERTIONS =====
        System.out.println("\n========== TEST RESULTS ==========");
        System.out.println("Page load time (4G, Mobile): " + pageLoadTime + "ms");
        System.out.println("API calls captured:          " + apiResponses.size());
        System.out.println("JS errors found:             " + jsErrors.size());
        System.out.println("\nAPI Responses:");
        apiResponses.forEach(r -> System.out.println("  " + r));
        if (!jsErrors.isEmpty()) {
            System.out.println("\nJS Errors:");
            jsErrors.forEach(e -> System.out.println("  ❌ " + e));
        }

        // Core assertions
        assert pageLoadTime < 15000 : "Page should load in under 15s on 4G";
        assert jsErrors.isEmpty()   : "No JavaScript errors should occur";
        assert apiResponses.stream()
            .noneMatch(r -> r.startsWith("5"))  : "No 5xx server errors";

        System.out.println("\n✅ All assertions passed!");
        driver.quit();
    }
}
```

---

## 📚 Quick Reference Card

### Most Common CDP Method Signatures

```java
// Enable Network tracking (REQUIRED before all Network.* commands)
devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

// Mobile emulation
devTools.send(Emulation.setDeviceMetricsOverride(width, height, dpr, mobile, ...));

// Locale + Timezone
devTools.send(Emulation.setLocaleOverride(Optional.of("en-IN")));
devTools.send(Emulation.setTimezoneOverride("Asia/Kolkata"));

// Geolocation
devTools.send(Emulation.setGeolocationOverride(Optional.of(lat), Optional.of(lng), Optional.of(1.0)));

// Block URLs
devTools.send(Network.setBlockedURLs(List.of("*google-analytics.com*")));

// Network throttling
devTools.send(Network.emulateNetworkConditions(offline, latencyMs, downloadBps, uploadBps, Optional.of(type)));

// Intercept responses
devTools.send(Fetch.enable(Optional.of(List.of(pattern)), Optional.empty()));

// Fail a request
devTools.send(Fetch.failRequest(requestId, ErrorReason.FAILED));

// Mock a response
devTools.send(Fetch.fulfillRequest(requestId, 200, headers, Optional.empty(), Optional.of(base64Body), Optional.empty()));

// Log JS errors
devTools.send(Log.enable());
devTools.addListener(Log.entryAdded(), entry -> { /* handle */ });

// Basic auth
((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("user", "pass"));

// Raw CDP command
driver.executeCdpCommand("Domain.method", Map.of("key", value));
```

---

## 🔗 References and Further Reading

### Official Documentation
- **Selenium 4 DevTools:** https://www.selenium.dev/documentation/webdriver/bidirectional/chrome_devtools/
- **Chrome DevTools Protocol Viewer:** https://chromedevtools.github.io/devtools-protocol/
- **CDP Network Domain:** https://chromedevtools.github.io/devtools-protocol/tot/Network/
- **CDP Emulation Domain:** https://chromedevtools.github.io/devtools-protocol/tot/Emulation/
- **CDP Fetch Domain:** https://chromedevtools.github.io/devtools-protocol/tot/Fetch/

### Useful GitHub Repositories
- **Selenium GitHub:** https://github.com/SeleniumHQ/selenium
- **Chrome DevTools Examples:** https://github.com/ChromeDevTools/awesome-chrome-devtools

### Related Concepts to Explore
- **Selenium BiDi (BiDirectional):** The next evolution of CDP integration in Selenium.
- **Playwright CDP:** Microsoft's Playwright also supports CDP for comparison.
- **WebDriver BiDi Protocol:** The W3C-standardized cross-browser alternative to Chrome-specific CDP.

---

## ✅ Summary Table

| Section | CDP Domain | Key Method | Primary Use Case |
|---------|-----------|------------|-----------------|
| 1. DevTools Intro | — | — | Understanding the "why" |
| 2. Mobile Emulation | `Emulation` | `setDeviceMetricsOverride` | Mobile responsive testing |
| 3. Custom Commands | — | `executeCdpCommand` | Access any CDP feature |
| 4. Localization | `Emulation` | `setLocaleOverride`, `setTimezoneOverride` | L10N/I18N testing |
| 5. Network Listening | `Network` | `responseReceived` listener | Status code validation |
| 6. Response Interception | `Fetch` | `fulfillRequest` | API mocking |
| 7. Failed Requests | `Fetch` | `failRequest` | Error handling testing |
| 8. Request Blocking | `Network` | `setBlockedURLs` | Speed optimization |
| 9. Network Throttling | `Network` | `emulateNetworkConditions` | Performance testing |
| 10. Basic Auth | `Fetch` / `HasAuthentication` | `register(uriPredicate, creds)` | Auth-protected pages |
| 11. JS Error Capture | `Log`, `Runtime` | `entryAdded`, `exceptionThrown` | Error monitoring |

---

> 💡 **Pro Tip for Freshers:** Start with Sections 1, 2, and 5. Once comfortable, move to 6, 8, and 11.
> These four cover 80% of real-world CDP use cases you'll encounter on the job.

> 💡 **Pro Tip for Experienced Engineers:** Chain multiple CDP features in your `BaseTest.setUp()`.
> Mobile emulation + locale + JS error monitoring adds almost zero overhead but dramatically
> improves test coverage quality across every single test in your suite.

---

*Guide authored for Selenium 4.x with Chrome DevTools Protocol. Last validated against Selenium 4.18.x and Chrome 130+.*
