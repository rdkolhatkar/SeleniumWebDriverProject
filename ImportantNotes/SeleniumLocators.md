# Selenium WebDriver Locators

* In automation, Selenium interacts with web page elements (e.g., clicking, typing, selecting).
* To perform these actions, Selenium must **locate elements in the HTML DOM**.
* **Locators** are strategies used to uniquely identify elements.
* In **dynamic websites** (IRCTC, Amazon, Flipkart), elements may appear, disappear, or change attributes frequently, so **advanced locator strategies** are required.

Selenium supports the following types of locators:

* **ID**
* **XPath**
* **CSS Selector**
* **Name**
* **Class Name**
* **Tag Name**
* **Link Text**
* **Partial Link Text**

---

### Example HTML Element

```html
<input type="text" placeholder="Username" id="inputUsername" value="">
```

* **Tag name** → `input`
* **Attribute** → `id`, `placeholder`, `value`
* **Attribute value** → `"inputUsername"`, `"Username"`, `""`

---

## CSS Selectors

### 1. By Class Name

* Syntax: `tagname.classname`
* Example: `button.signInBtn`
* Shorthand (only class): `.error`

```html
<button class="signInBtn">Login</button>
```

```java
driver.findElement(By.cssSelector("button.signInBtn")).click();
```

---

### 2. By ID

* Syntax: `tagname#id`
* Example: `input#inputUsername`

```java
driver.findElement(By.cssSelector("input#inputUsername")).sendKeys("admin");
```

---

### 3. By Attribute

* Syntax: `tagname[attribute='value']`

```html
<input type="text" placeholder="Username" value="">
```

Locator → `input[placeholder='Username']`

---

### 4. By Child / Hierarchy

* Syntax: `parent child`
* Selects child elements inside parent

```html
<div class="form">
  <input type="text">
</div>
```

```css
div.form input
```

---

### 5. Partial Attribute Match (Dynamic Friendly ⭐)

* **Contains (`*`)**

```css
input[type*='pass']
```

Matches:

```html
<input type="password">
<input type="userPassword">
```

---

* **Starts With (`^`)**

```css
input[id^='user']
```

Matches:

```html
<input id="user_12345">
```

---

* **Ends With (`$`)**

```css
input[id$='name']
```

Matches:

```html
<input id="login_name">
```

---

### 6. By Tag Name

```css
input
```

---

### 7. Nth Child (Index-based – Use Carefully ⚠️)

```css
ul li:nth-child(2)
```

---

## XPath Selectors

### 1. By Attribute

* Syntax: `//tagname[@attribute='value']`

```html
<input type="text" placeholder="Name">
```

```xpath
//input[@placeholder='Name']
```

---

### 2. By Attribute with Index

* Syntax: `//tagname[@attribute='value'][index]`

```xpath
//input[@type='text'][2]
```

⚠️ Index can break if DOM order changes.

---

### 3. By Parent → Child Relationship

* Syntax: `//parentTagname/childTagname`

```xpath
//div/input
```

---

### 4. By Contains (Dynamic Attribute Handling ⭐)

* Syntax:

```xpath
//tagname[contains(@attribute,'value')]
```

Example:

```html
<button class="btn submit-button active">Submit</button>
```

```xpath
//button[contains(@class,'submit')]
```

---

### 5. By Starts-with (Dynamic IDs ⭐)

* Syntax:

```xpath
//tagname[starts-with(@attribute,'value')]
```

Example:

```html
<input id="user_123456">
```

```xpath
//input[starts-with(@id,'user_')]
```

---

### 6. By Text()

```xpath
//button[text()='Login']
```

---

### 7. Partial Text Match (Very Useful for Dynamic Text ⭐)

```xpath
//a[contains(text(),'Train')]
```

---

### 8. By Tag Name Only

```xpath
//input
```

---

### 9. Advanced Relationships (Very Important for Dynamic DOM ⭐)

#### Following Sibling

```xpath
//header/div/button[1]/following-sibling::button[1]
```

Example:

```html
<button>OK</button>
<button>Cancel</button>
```

---

#### Preceding Sibling

```xpath
//button[text()='Cancel']/preceding-sibling::button
```

---

#### Parent Reference

```xpath
//button[text()='Save']/parent::div
```

---

#### Ancestor (Climb DOM tree)

```xpath
//input[@id='username']/ancestor::form
```

---

#### Descendant (Flexible hierarchy)

```xpath
//form[@id='loginForm']//input
```

---

### 10. Complex Indexed / Grouped XPath (Real-World Forms ⭐⭐⭐)

Used when:

* No unique attributes
* Repeated form rows
* Deeply nested div structures (OrangeHRM, IRCTC, SAP, Salesforce)

#### Example XPath

```xpath
(//div[contains(@class,'oxd-form-row')]/div[1]/div[2]/div[1]/div[2])[1]/input
```

#### Explanation

* `//div[contains(@class,'oxd-form-row')]` → Locate all form rows
* `/div[1]/div[2]/div[1]/div[2]` → Traverse exact child structure
* `( ... )[1]` → Select first matching block
* `/input` → Target the input field

#### Selenium Usage (Java)

```java
driver.findElement(
    By.xpath("(//div[contains(@class,'oxd-form-row')]/div[1]/div[2]/div[1]/div[2])[1]/input")
).sendKeys("Admin");
```

⚠️ **Note**:
This type of XPath is powerful but tightly coupled to DOM structure.
Prefer `contains()`, `starts-with()`, and relative anchors whenever possible.

---

## Dynamic Website Locator Strategy (IRCTC / Live Data Sites)

### Key Principles

* ❌ Do NOT locate by dynamic values (train number, time, seat count)
* ✅ Locate by **stable structure**
* ✅ Use **contains(), starts-with(), relative XPath**
* ✅ Assert **presence, count, flow**, not exact data

---

### Example: Train Results Page (Dynamic Data)

```xpath
//div[contains(@class,'train-row')]//button[contains(text(),'Book')]
```

✔ Works even if trains change
✔ Ignores dynamic IDs
✔ Anchored to stable UI behavior

---

## Selenium WebDriver Locators – Cheat Sheet

| **Locator Type**          | **Syntax**                          | **HTML Example**                                          | **Selenium Code (Java)**                                                       |
| ------------------------- | ----------------------------------- | --------------------------------------------------------- | ------------------------------------------------------------------------------ |
| **ID**                    | `By.id("idValue")`                  | `<input id="inputUsername">`                              | `driver.findElement(By.id("inputUsername")).sendKeys("testUser");`             |
| **Name**                  | `By.name("nameValue")`              | `<input type="password" name="password">`                 | `driver.findElement(By.name("password")).sendKeys("securePass");`              |
| **Class Name**            | `By.className("classValue")`        | `<button class="signInBtn">Login</button>`                | `driver.findElement(By.className("signInBtn")).click();`                       |
| **Tag Name**              | `By.tagName("tagname")`             | `<a href="/home">Home</a>`                                | `List<WebElement> links = driver.findElements(By.tagName("a"));`               |
| **Link Text**             | `By.linkText("fullText")`           | `<a href="/about">About Us</a>`                           | `driver.findElement(By.linkText("About Us")).click();`                         |
| **Partial Link Text**     | `By.partialLinkText("partialText")` | `<a href="/products/software">View Software Products</a>` | `driver.findElement(By.partialLinkText("Software")).click();`                  |
| **CSS – Contains**        | `tag[attr*='value']`                | `<input type="userPassword">`                             | `driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("pwd");`   |
| **CSS – Starts With**     | `tag[attr^='value']`                | `<input id="user_123">`                                   | `driver.findElement(By.cssSelector("input[id^='user']")).sendKeys("abc");`     |
| **CSS – Ends With**       | `tag[attr$='value']`                | `<input id="login_name">`                                 | `driver.findElement(By.cssSelector("input[id$='name']")).sendKeys("xyz");`     |
| **XPath – Contains()**    | `//tag[contains(@attr,'value')]`    | `<button class="submit-btn">`                             | `driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();` |
| **XPath – Starts-with()** | `//tag[starts-with(@attr,'value')]` | `<div id="train_123">`                                    | `driver.findElement(By.xpath("//div[starts-with(@id,'train_')]"));`            |
| **XPath – Text Contains** | `//tag[contains(text(),'value')]`   | `<span>Train Details</span>`                              | `driver.findElement(By.xpath("//span[contains(text(),'Train')]"));`            |
| **XPath – Ancestor**      | `//child/ancestor::parent`          | `<form><input></form>`                                    | `driver.findElement(By.xpath("//input/ancestor::form"));`                      |
| **XPath – Descendant**    | `//parent//child`                   | `<div><span></span></div>`                                | `driver.findElement(By.xpath("//div//span"));`                                 |

---

## 📘 XPath Theory & Conceptual Explanation (Added Section)

### What is an Anchor in XPath?

An **anchor element** is a **stable, identifiable element** used as a reference point to locate nearby elements.

Example:

```xpath
//label[text()='Username']/following-sibling::input
```

Here:

* `label` is the **anchor**
* `input` is located **relative to it**

Anchors are used because:

* IDs are dynamic
* Structure is more stable than values

---

### What is `text()` in XPath?

`text()` is a **node function** that extracts visible text of an element.

```xpath
//button[text()='Login']
```

Use when:

* Text is stable
* No unique attributes exist

Avoid when:

* Text changes dynamically

---

### What is `contains()` and why we use it?

`contains()` performs **partial matching**.

```xpath
contains(@class,'submit')
```

Used because:

* Classes and IDs often change
* Partial values remain consistent

---

### What is `starts-with()`?

Matches attributes that **begin with a known prefix**.

```xpath
starts-with(@id,'user_')
```

Ideal for:

* Auto-generated IDs
* Dynamic numeric suffixes

---

### What is XPath Axis?

Axes define **direction of traversal** in the DOM.

Syntax:

```xpath
axis::node
```

---

### Why do we use `::` (double colon)?

`::` separates:

* **Axis name**
* **Node selector**

Example:

```xpath
following-sibling::button
```

Meaning:

> “Select `button` nodes that are following siblings of the current node”

It is **not four colons**, it is **two colons** called **axis operator**.

---

### following-sibling::button

Moves **forward** at the same DOM level.

```xpath
//button[text()='OK']/following-sibling::button
```

Use when:

* Buttons are side-by-side
* Order is consistent

---

### preceding-sibling::button

Moves **backward** at the same DOM level.

```xpath
//button[text()='Cancel']/preceding-sibling::button
```

---

### parent::div

Moves **one level up**.

```xpath
//input/parent::div
```

---

### ancestor::form

Moves **multiple levels up**.

```xpath
//input/ancestor::form
```

Used when:

* You need container context
* Parent is not immediate

---

### descendant:: (or `//`)

Moves **downwards at any depth**.

```xpath
//form//input
```

---

### Why relative XPath is preferred over absolute XPath?

❌ Absolute XPath:

```xpath
/html/body/div[2]/div[1]/input
```

✅ Relative XPath:

```xpath
//input[@placeholder='Username']
```

Relative XPath:

* More stable
* Easier to maintain
* Less flaky

---

### ⭐ Final Conceptual Rule

> **XPath is not about path length.
> XPath is about relationship.**

---
