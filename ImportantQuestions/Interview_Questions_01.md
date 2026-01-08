# Interview_Questions_01_Updated

## 1) What Are The Different Components Of The Selenium Framework?

Selenium is an automation development kit that comprises the following components.
▪ Selenium IDE:
-> A Firefox/chrome extension to record and play the user actions performed on a web page.
▪ Selenium RC:
-> A Selenium Remote Control which exposes APIs for scripting tests in different languages and also runs them in browsers.
▪ Selenium Webdriver:
-> These are native APIs that directly interact with the browser. They give more control and faster than the RC APIs.
▪ Selenium Grid:
-> It provides concurrency. With its help, we can split testing and run a set of cases on one machine and some on another.

---

## 2) Can you use the Selenium for the testing of Rest API or Web Services?

Answer: Selenium provides Native API's for interacting with browsers using action and events. Rest API and Web Services
Don't have any UI and hence can't be automated using selenium

---

## 3) What are the different types of Web Driver API's supported in selenium?

Answer: Selenium 3 supports legacy drivers like InternetExplorerDriver and HtmlUnitDriver,
while Selenium 4 fully follows the W3C WebDriver protocol, removes legacy drivers, supports only Chromium-based Edge, and introduces DevTools integration.

### Selenium 3:

| **WebDriver Name**            | **WebDriver API**                                           | **Supported Browser**            |
| ----------------------------- | ----------------------------------------------------------- | -------------------------------- |
| ChromeDriver                  | `org.openqa.selenium.chrome.ChromeDriver`                   | Google Chrome                    |
| FirefoxDriver                 | `org.openqa.selenium.firefox.FirefoxDriver`                 | Mozilla Firefox                  |
| EdgeDriver                    | `org.openqa.selenium.edge.EdgeDriver` *(Legacy & Chromium)* | Microsoft Edge                   |
| SafariDriver                  | `org.openqa.selenium.safari.SafariDriver`                   | Apple Safari                     |
| InternetExplorerDriver        | `org.openqa.selenium.ie.InternetExplorerDriver`             | Internet Explorer                |
| RemoteWebDriver               | `org.openqa.selenium.remote.RemoteWebDriver`                | Multiple browsers (Grid / Cloud) |
| HtmlUnitDriver *(Deprecated)* | `org.openqa.selenium.htmlunit.HtmlUnitDriver`               | Headless browser                 |

### Selenium 4:

| **WebDriver Name**                 | **WebDriver API**                            | **Supported Browser**            |
| ---------------------------------- | -------------------------------------------- | -------------------------------- |
| ChromeDriver                       | `org.openqa.selenium.chrome.ChromeDriver`    | Google Chrome                    |
| FirefoxDriver                      | `org.openqa.selenium.firefox.FirefoxDriver`  | Mozilla Firefox                  |
| EdgeDriver                         | `org.openqa.selenium.edge.EdgeDriver`        | Microsoft Edge (Chromium only)   |
| SafariDriver                       | `org.openqa.selenium.safari.SafariDriver`    | Apple Safari                     |
| RemoteWebDriver                    | `org.openqa.selenium.remote.RemoteWebDriver` | Multiple browsers (Grid / Cloud) |
| **DevTools (New)**                 | `org.openqa.selenium.devtools.DevTools`      | Chrome, Edge                     |
| InternetExplorerDriver *(Removed)* | ❌ Not supported                              | ❌                                |
| HtmlUnitDriver *(Removed)*         | ❌ Not supported                              | ❌                                |

---

## 4) Which of the WebDriver API is fastest and why?

Answer: HtmlUnitDriver was the fastest WebDriver API because it is headless and does not use a real browser, but since it is deprecated,
ChromeDriver or EdgeDriver in headless mode is the fastest and recommended option in Selenium 4.

---

## 5) What is Selenium IDE?

Answer: Selenium IDE (Integrated Development Environment) is a record-and-playback tool provided by Selenium that allows users to record browser interactions and replay them as test cases without writing much code.
It is available as a browser extension for Chrome and Firefox. Selenium IDE is a browser-based automation tool that records and plays back user actions.
It is mainly used for learning, quick prototyping, and simple smoke tests. Although it is still supported after Selenium 3 and 4, modern automation frameworks prefer Selenium WebDriver for scalability and maintainability.

---

## 6) What is Selenese?

Answer: Selenese is the command language used by Selenium IDE to create and execute automated test steps.
It consists of a set of predefined commands that tell the browser what action to perform, on which element, and what value to use.
Selenese is the command language used by Selenium IDE that defines browser actions, element locators, and validations for automated tests.

| Category      | Examples                             |
| ------------- | ------------------------------------ |
| Actions       | `open`, `click`, `type`, `select`    |
| Accessors     | `getText`, `getTitle`                |
| Assertions    | `assertText`, `assertTitle`          |
| Verifications | `verifyText`, `verifyElementPresent` |
| Waiting       | `waitForElementPresent`              |

---

## 7) What is Selenium Grid? Explain Pros & Cons of Selenium Grid And what are different features of Selenium Grid with respect to Selenium 3 & Selenium 4

Answer: Selenium Grid is a Selenium tool that allows you to run automated tests in parallel across multiple machines, browsers, and operating systems from a single test suite.

### Pros

| **Pros**               | **Explanation**                               |
| ---------------------- | --------------------------------------------- |
| Parallel execution     | Runs multiple test cases at the same time     |
| Faster test execution  | Reduces overall execution time significantly  |
| Cross-browser testing  | Same tests run on Chrome, Firefox, Edge, etc. |
| Cross-platform testing | Tests run on Windows, Linux, macOS            |
| Centralized control    | One hub controls multiple nodes               |
| CI/CD friendly         | Integrates well with Jenkins, GitHub Actions  |

### Cons

| **Cons**                     | **Explanation**                             |
| ---------------------------- | ------------------------------------------- |
| Complex setup                | Requires configuration of hub and nodes     |
| Debugging is difficult       | Failures occur on remote machines           |
| Infrastructure cost          | Needs multiple machines or VMs              |
| Maintenance overhead         | Browser & driver versions must be managed   |
| Network dependency           | Test execution depends on network stability |
| Not ideal for small projects | Overkill for small test suites              |

### Selenium Grid 3 vs Selenium Grid 4

| Feature       | Selenium Grid 3 | Selenium Grid 4           |
| ------------- | --------------- | ------------------------- |
| Architecture  | Hub–Node        | Distributed (Event-based) |
| Setup         | Complex         | Simplified                |
| Observability | Limited         | Dashboard & tracing       |
| Scalability   | Manual          | Better auto-scaling       |
| W3C Support   | Partial         | Full                      |

---

## 8) What is Hub and Node in Selenium grid, What do they do in Selenium Grid and Explain their function and importance

Answer:
-> The Hub is the central controller in Selenium Grid. It receives test execution requests and distributes them to the appropriate Nodes.
-> A Node is a machine (physical or virtual) that actually executes the test cases.

A) What does the Hub do in selenium grid
- Acts as a single entry point for all test requests
- Maintains information about Registered Nodes, Browser types & versions, Platform (Windows, Linux, macOS)
  Decides which Node should execute a test and Routes test commands from test scripts to Nodes

B) Hub Responsibilities
- Accepts WebDriver requests (RemoteWebDriver), Matches test requirements with available Nodes, Forwards commands to the selected Node and Collects test execution status.

C) Importance of Hub
- Centralized test management, Enables parallel execution and Eliminates the need to connect tests to individual machines

D) What does a Node do in selenium grid
- Registers itself with the Hub, Provides real browsers for testing, Executes Selenium commands sent by the Hub and Sends test results back to the Hub

E) Node Responsibilities
- Runs browser instances (Chrome, Firefox, Edge, etc.), Executes tests on specified OS/browser combinations, Supports multiple sessions (parallel tests)

F) Importance of Node
- Performs the actual test execution, Enables cross-browser & cross-platform testing and Improves execution speed by parallelism

---

## 9) What are Selenium Grid Extras?

Answer : Selenium Grid Extras is a third-party utility (not part of Selenium core) that extends Selenium Grid by providing additional operational, monitoring, and node-management features which are not available out-of-the-box in Selenium Grid.
In Selenium 4, Grid is already more powerful, but Grid Extras can still be used when advanced node control and maintenance automation are required.

---

## 10) Difference Between maxSessions and maxInstances in Selenium Grid?

Answer:
-> maxSessions defines the maximum number of total test sessions a node can run at the same time, regardless of browser type.
-> maxInstances defines the maximum number of instances of a specific browser (Chrome, Firefox, etc.) that can run in parallel on that node.
-> maxSessions controls overall parallel capacity of a node, while maxInstances controls parallel execution per browser type.
