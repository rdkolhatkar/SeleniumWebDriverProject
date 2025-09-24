
Key Differences Between "driver.close()" & "driver.quit();"

| Feature              | `driver.close()`                                | `driver.quit()`                                 |
| -------------------- | ----------------------------------------------- | ----------------------------------------------- |
| Scope                | Current tab/window only                         | Entire browser session                          |
| Session              | Keeps session alive if other windows exist      | Ends WebDriver session completely               |
| Usability after call | Driver still usable if session has open windows | Driver object becomes invalid                   |
| Use case             | Closing a popup, child window, or one tab       | Ending test execution and cleaning up resources |

Perfect question 👍 — Selenium waits often confuse beginners. Let’s compare **Implicit Wait**, **Explicit Wait**, and **Fluent Wait** in a **tabular format** with syntax + examples for each.

---

# 📊 Comparison of Selenium Waits

| Feature                | **Implicit Wait**                                                                                                            | **Explicit Wait**                                                                                   | **Fluent Wait**                                                                                                     |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| **Definition**         | Sets a **default waiting time** for the WebDriver to search for elements. Applies to **all elements** in the driver session. | Waits for a **specific condition** to be met before proceeding, applied to **specific element(s)**. | A type of Explicit Wait that allows you to define **polling frequency**, **timeout**, and **exceptions to ignore**. |
| **Scope**              | Global — applies to all element searches (`findElement` / `findElements`).                                                   | Local — applies only to the element(s) specified in the wait condition.                             | Local — applies only to the defined wait condition (more configurable).                                             |
| **Condition**          | Only waits for **element presence in DOM** (not clickable, not visible).                                                     | Waits for **predefined ExpectedConditions** (clickable, visible, text present, etc.).               | Can wait for **custom conditions** (more flexible than Explicit).                                                   |
| **Polling frequency**  | Default polling (500 ms).                                                                                                    | Default polling (500 ms).                                                                           | **Customizable polling interval** (e.g., every 2 seconds).                                                          |
| **Exception handling** | Throws `NoSuchElementException` after timeout.                                                                               | Can ignore certain exceptions internally.                                                           | Can **ignore specific exceptions** explicitly (e.g., `NoSuchElementException`).                                     |
| **Use case**           | When you want all element searches to wait a bit by default.                                                                 | When you want to wait for a **specific condition** on a specific element.                           | When you need **fine-grained control** over wait — custom conditions, polling frequency, exception handling.        |
| **Best Practice**      | Avoid overusing, can cause hidden delays.                                                                                    | Recommended for conditions like visibility, clickability.                                           | Use when Explicit Wait isn’t flexible enough.                                                                       |

---

# ✅ Syntax and Examples

### 1. **Implicit Wait**

```java
// Import
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

WebDriver driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
// Global wait of 10 seconds

driver.get("https://example.com");
driver.findElement(By.id("username")).sendKeys("testuser");
// If element not found immediately, Selenium keeps checking until 10 sec
```

---

### 2. **Explicit Wait**

```java
// Import
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

WebDriver driver = new ChromeDriver();
driver.get("https://example.com");

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
// Wait up to 15 sec until element is clickable
WebElement loginButton = wait.until(
        ExpectedConditions.elementToBeClickable(By.id("loginBtn"))
);

loginButton.click();
```

---

### 3. **Fluent Wait**

```java
// Import
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.NoSuchElementException;

WebDriver driver = new ChromeDriver();
driver.get("https://example.com");

Wait<WebDriver> fluentWait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(20))       // max wait time
        .pollingEvery(Duration.ofSeconds(2))       // check every 2 seconds
        .ignoring(NoSuchElementException.class);   // ignore this exception

WebElement username = fluentWait.until(
        drv -> drv.findElement(By.id("username"))
);

username.sendKeys("testuser");
```

---

# 🚀 Quick Summary

* **Implicit Wait** → Global default delay for locating elements.
* **Explicit Wait** → Wait for specific conditions (recommended).
* **Fluent Wait** → Advanced Explicit Wait with polling + exception handling.

---

👉 Pro Tip: In modern Selenium tests, **prefer Explicit/Fluent waits** because they’re more reliable than Implicit Wait (which can cause unpredictable behavior when mixed with Explicit waits).

---
