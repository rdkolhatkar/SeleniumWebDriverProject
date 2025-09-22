# Selenium WebDriver Locators

* In automation, Selenium interacts with web page elements (e.g., clicking, typing, selecting).
* To perform these actions, Selenium must **locate elements in the HTML DOM**.
* **Locators** are strategies used to uniquely identify elements.

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

1. **By Class Name**

   * Syntax: `tagname.classname`
   * Example: `Button.signInBtn`
   * Shorthand (only class): `.error`

2. **By ID**

   * Syntax: `tagname#id`
   * Example: `input#inputUsername`

3. **By Attribute**

   * Syntax: `tagname[attribute='value']`
   * Example:

     ```html
     <input type="text" placeholder="Username" value="">
     ```

     Locator → `input[placeholder='Username']`

4. **By Child/Hierarchy**

   * Syntax: `//Tagname[@attribute='value']:nth-child(index)` → Selects child items
   * Example: `Parenttagname childtagname`

5. **Partial Attribute Match**

   * Example: `input[type*='pass']` → Matches `input` where type contains `"pass"`

6. **By Tag Name**

   * Example: `tagname`

---

## XPath Selectors

1. **By Attribute**

   * Syntax: `//Tagname[@attribute='value']`
   * Example:

     ```html
     <input type="text" placeholder="Name">
     ```

     Locator → `//input[@placeholder='Name']`

2. **By Attribute with Index**

   * Syntax: `//Tagname[@attribute='value'][index]`

3. **By Parent → Child Relationship**

   * Syntax: `//parentTagname/childTagname`

4. **By Contains (Pattern Matching)**

   * Syntax: `//tagname[contains(@attribute,'value')]`
   * Example: `//button[contains(@class,'submit')]`

5. **By Tag Name Only**

   * Syntax: `//tagname`

6. **Advanced Relationships**

   * Following sibling:
     `//header/div/button[1]/following-sibling::button[1]`
   * Parent reference:
     `//header/div/button[1]/parent::div`

---
# Selenium WebDriver Locators – Cheat Sheet

| **Locator Type**                           | **Syntax**                                    | **HTML Example**                                              | **Selenium Code (Java)**                                                               |
| ------------------------------------------ | --------------------------------------------- | ------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
| **ID**                                     | `By.id("idValue")`                            | `<input id="inputUsername">`                                  | `driver.findElement(By.id("inputUsername")).sendKeys("testUser");`                     |
| **Name**                                   | `By.name("nameValue")`                        | `<input type="password" name="password">`                     | `driver.findElement(By.name("password")).sendKeys("securePass");`                      |
| **Class Name**                             | `By.className("classValue")`                  | `<button class="signInBtn">Login</button>`                    | `driver.findElement(By.className("signInBtn")).click();`                               |
| **Tag Name**                               | `By.tagName("tagname")`                       | `<a href="/home">Home</a>`                                    | `List<WebElement> links = driver.findElements(By.tagName("a"));`                       |
| **Link Text**                              | `By.linkText("fullText")`                     | `<a href="/about">About Us</a>`                               | `driver.findElement(By.linkText("About Us")).click();`                                 |
| **Partial Link Text**                      | `By.partialLinkText("partialText")`           | `<a href="/products/software">View Software Products</a>`     | `driver.findElement(By.partialLinkText("Software")).click();`                          |
| **CSS Selector – By ID**                   | `tagname#id`                                  | `<input id="inputUsername">`                                  | `driver.findElement(By.cssSelector("input#inputUsername")).sendKeys("john");`          |
| **CSS Selector – By Class**                | `tagname.classname`                           | `<button class="signInBtn">Login</button>`                    | `driver.findElement(By.cssSelector("button.signInBtn")).click();`                      |
| **CSS Selector – By Attribute**            | `tagname[attribute='value']`                  | `<input placeholder="Username">`                              | `driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("raj");` |
| **CSS Selector – Partial Attribute Match** | `tagname[attribute*='value']`                 | `<input type="password" name="userPassword">`                 | `driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("mypassword");`    |
| **CSS Selector – Parent → Child**          | `parent child`                                | `<div class="form"><input type="text"></div>`                 | `driver.findElement(By.cssSelector("div.form input")).sendKeys("abc@test.com");`       |
| **XPath – By Attribute**                   | `//tagname[@attribute='value']`               | `<input placeholder="Name">`                                  | `driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Raj");`        |
| **XPath – By Index**                       | `//tagname[@attribute='value'][index]`        | `<input type="text" name="f1"> <input type="text" name="f2">` | `driver.findElement(By.xpath("//input[@type='text'][2]")).sendKeys("Second");`         |
| **XPath – Parent → Child**                 | `//parent/child`                              | `<div><input type="email"></div>`                             | `driver.findElement(By.xpath("//div/input")).sendKeys("mail@test.com");`               |
| **XPath – Contains()**                     | `//tagname[contains(@attribute,'value')]`     | `<button class="btn submit-button">Submit</button>`           | `driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();`         |
| **XPath – By Tag Name**                    | `//tagname`                                   | `<input type="text">`                                         | `List<WebElement> inputs = driver.findElements(By.xpath("//input"));`                  |
| **XPath – Following Sibling**              | `//tagname/following-sibling::tagname[index]` | `<button>OK</button><button>Cancel</button>`                  | `driver.findElement(By.xpath("//button[1]/following-sibling::button[1]")).click();`    |
| **XPath – Parent Reference**               | `//child/parent::tagname`                     | `<div><button>Save</button></div>`                            | `WebElement parent = driver.findElement(By.xpath("//button/parent::div"));`            |

---
