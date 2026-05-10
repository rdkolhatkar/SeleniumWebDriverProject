package com.selenium.test;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.URI;
import java.time.Duration;
import java.util.function.Predicate;

public class HandlingBasicWindowAuthentication {
    /**
     * ============================================================
     * 🔹 Test Case: Handle Basic Authentication Pop-Up using Selenium
     * ============================================================
     *
     * 📌 Purpose:
     * This test demonstrates how to handle HTTP Basic Authentication
     * pop-up windows using Selenium 4 built-in authentication support.
     *
     * Instead of manually interacting with browser authentication
     * pop-ups, Selenium injects username and password automatically
     * before the protected webpage loads.
     *
     * 📌 What this test does:
     * - Launches Chrome browser
     * - Registers authentication credentials
     * - Intercepts authentication challenge request
     * - Supplies username and password automatically
     * - Opens protected URL successfully
     * - Avoids manual pop-up handling
     *
     * 📌 Authentication URL Used:
     *      http://httpbin.org/basic-auth/foo/bar
     *
     * 📌 How this URL works:
     *
     * This endpoint belongs to:
     *      http://httpbin.org
     *
     * It is a public testing service used for:
     * - API testing
     * - Authentication testing
     * - Header validation
     * - Request/response testing
     *
     * Endpoint Structure:
     *
     *      /basic-auth/{username}/{password}
     *
     * Here:
     *      username = foo
     *      password = bar
     *
     * Therefore valid credentials are:
     *
     *      Username → foo
     *      Password → bar
     *
     * If correct credentials are passed:
     *      ✔ Access Granted
     *
     * Otherwise:
     *      ❌ 401 Unauthorized
     *
     * ============================================================
     * 🔹 What is Authentication Pop-Up Window?
     * ============================================================
     *
     * When a browser tries to access a protected webpage/server,
     * the server may respond with:
     *
     *      HTTP Status Code → 401 Unauthorized
     *
     * Along with:
     *
     *      WWW-Authenticate Header
     *
     * Browser then automatically displays a native authentication
     * dialog/pop-up asking for:
     *
     *      - Username
     *      - Password
     *
     * Example Pop-Up:
     *
     * ------------------------------------------------
     * Authentication Required
     *
     * Username: __________
     * Password: __________
     *
     * [Sign In]
     * ------------------------------------------------
     *
     * ============================================================
     * 🔹 Important Understanding:
     * ============================================================
     *
     * This authentication pop-up is:
     *
     * ❌ NOT a JavaScript Alert
     * ❌ NOT an HTML Modal
     * ❌ NOT part of DOM
     *
     * It is:
     *
     * ✔ Browser-Level Native Authentication Dialog
     * ✔ Controlled by browser/network layer
     * ✔ Triggered before webpage loads
     *
     * ============================================================
     * 🔹 Why Selenium Locators Do NOT Work?
     * ============================================================
     *
     * Since this pop-up:
     *
     * - Is NOT inside webpage DOM
     * - Is NOT HTML
     * - Is NOT rendered by JavaScript
     *
     * Selenium cannot use:
     *
     *      driver.findElement()
     *      XPath
     *      CSS Selector
     *      ID
     *      Name
     *
     * Traditional UI automation strategies fail here.
     *
     * ============================================================
     * 🔹 Old Approach (Not Recommended)
     * ============================================================
     *
     * Earlier Selenium versions commonly used:
     *
     *      https://username:password@url
     *
     * Example:
     *
     *      https://foo:bar@httpbin.org/basic-auth/foo/bar
     *
     * Problems with old approach:
     * - Browser security restrictions
     * - Credentials visible in URL
     * - Deprecated in modern browsers
     * - Security concerns
     *
     * ============================================================
     * 🔹 Modern Selenium 4 Approach
     * ============================================================
     *
     * Selenium 4 introduced:
     *
     *      HasAuthentication
     *
     * This allows Selenium to:
     * - Listen for authentication challenge
     * - Automatically inject credentials
     * - Handle browser-level auth securely
     *
     * ============================================================
     * 🔹 Selenium APIs Used
     * ============================================================
     *
     * 1. HasAuthentication
     *
     *    → Selenium interface used for browser authentication
     *
     * 2. register()
     *
     *    → Registers authentication handler
     *
     * 3. UsernameAndPassword
     *
     *    → Stores username/password credentials
     *
     * ============================================================
     * 🔹 Java 8 Functional Programming Concepts Used
     * ============================================================
     *
     * This code uses:
     *
     *      Predicate<URI>
     *
     * which is part of Java 8 Functional Interfaces.
     *
     * ============================================================
     * 🔹 What is Predicate in Java 8?
     * ============================================================
     *
     * Predicate is a Functional Interface introduced in:
     *
     *      java.util.function.Predicate
     *
     * Purpose:
     *      Evaluates a condition and returns:
     *
     *          true
     *          OR
     *          false
     *
     * Syntax:
     *
     *      Predicate<T>
     *
     * Example:
     *
     *      Predicate<String> isEmpty =
     *              str -> str.isEmpty();
     *
     * ============================================================
     * 🔹 Predicate Used in This Test
     * ============================================================
     *
     * Code:
     *
     *      Predicate<URI> uriPredicate =
     *              uri -> uri.getHost()
     *                        .contains("httpbin.org");
     *
     * Meaning:
     *
     * - Selenium checks every requested URI
     * - Predicate validates whether host contains:
     *
     *      "httpbin.org"
     *
     * If condition becomes TRUE:
     *      ✔ Credentials are applied
     *
     * If FALSE:
     *      ❌ Authentication not applied
     *
     * ============================================================
     * 🔹 Lambda Expression Explanation
     * ============================================================
     *
     * Code:
     *
     *      uri -> uri.getHost().contains("httpbin.org")
     *
     * This is a Java 8 Lambda Expression.
     *
     * Equivalent Traditional Code:
     *
     *      new Predicate<URI>() {
     *          @Override
     *          public boolean test(URI uri) {
     *              return uri.getHost()
     *                        .contains("httpbin.org");
     *          }
     *      };
     *
     * Lambda reduces boilerplate code significantly.
     *
     * ============================================================
     * 🔹 What is Consumer in Java 8?
     * ============================================================
     *
     * Consumer is another Functional Interface:
     *
     *      java.util.function.Consumer
     *
     * Purpose:
     *      Accepts input and performs operation
     *      without returning value.
     *
     * Syntax:
     *
     *      Consumer<T>
     *
     * Example:
     *
     *      Consumer<String> print =
     *              name -> System.out.println(name);
     *
     * Difference:
     *
     * Predicate:
     *      ✔ Returns boolean
     *
     * Consumer:
     *      ✔ Performs action
     *      ❌ No return value
     *
     * ============================================================
     * 🔹 Why Predicate is Used Here Instead of Consumer?
     * ============================================================
     *
     * Selenium must decide:
     *
     *      Should credentials be applied or not?
     *
     * That decision requires:
     *
     *      true / false evaluation
     *
     * Therefore:
     *
     *      Predicate is appropriate
     *
     * Consumer is NOT suitable because:
     *
     *      It does not return boolean.
     *
     * ============================================================
     * 🔹 Real-world usage of Authentication Handling
     * ============================================================
     *
     * - Internal enterprise applications
     * - API gateways
     * - Staging/UAT environments
     * - VPN protected websites
     * - Admin dashboards
     * - Secure intranet portals
     *
     * ============================================================
     * 🔹 Why Selenium 4 Authentication Feature is Better?
     * ============================================================
     *
     * ✔ Secure credential handling
     * ✔ Cleaner implementation
     * ✔ No URL credential exposure
     * ✔ Browser-compatible
     * ✔ Modern approach
     * ✔ Supports Chromium browsers
     *
     * ============================================================
     * 🔹 Important Notes
     * ============================================================
     *
     * - Authentication handling works before page loads
     * - Supported mainly in Chromium browsers
     * - Credentials remain hidden from URL
     * - More reliable than old URL-based auth
     *
     * ⚠️ Thread.sleep():
     * - Used only for demo purposes
     * - Prefer WebDriverWait in production automation
     *
     * ============================================================
     * 🔹 Reference (Official Documentation)
     * ============================================================
     *
     * Selenium HasAuthentication:
     * https://www.selenium.dev/documentation/webdriver/bidi/cdp/network/
     *
     * Java Predicate Documentation:
     * https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
     *
     * Java Consumer Documentation:
     * https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html
     *
     * HTTP Authentication:
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication
     */
    @Test
    public void handlingBasicWindowAuthenticationWithSelenum()
            throws InterruptedException {

        // Here when we will navigate to any website,
        // to load the given URL or URI it will display
        // a browser-level authentication pop-up.

        // Since this is NOT a JavaScript/HTML pop-up,
        // Selenium locators and DOM strategies will not work.

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // ============================================================
        // 🔹 Java 8 Predicate Functional Interface
        // ============================================================

        // Predicate checks whether current URI host
        // contains "httpbin.org"

        Predicate<URI> uriPredicate =
                uri -> uri.getHost()
                        .contains("httpbin.org");

        // ============================================================
        // 🔹 Register Authentication Credentials
        // ============================================================

        // Selenium automatically injects credentials
        // whenever matching authentication challenge appears

        ((HasAuthentication) driver)
                .register(
                        uriPredicate,
                        UsernameAndPassword.of("foo", "bar")
                );

        // ============================================================
        // 🔹 Open Protected URL
        // ============================================================

        driver.get("http://httpbin.org/basic-auth/foo/bar");

        Thread.sleep(3000);

        // ============================================================
        // 🔹 Close Browser
        // ============================================================

        driver.quit();
    }
    @Test
    public void handlingBasicAuthenticationWithSelenum() throws InterruptedException {
        // Here when we will navigate to any website, to load the given URL or URI it will display a window pop-up and inside that pop-up we have to pass our username and password
        // Note: This is not a UI/JavaScript Pop-Up, So locator strategy and UI-DOM strategy will not work.
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Here we are using Java 8 Predicate and Consumer Concepts
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("http://httpbin.org");
        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
        driver.get("http://httpbin.org/basic-auth/foo/bar");
        Thread.sleep(3000);
        driver.quit();

    }
}
