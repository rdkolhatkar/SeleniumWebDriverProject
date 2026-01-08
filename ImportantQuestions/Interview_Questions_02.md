# Interview_Questions_02

## What Are The Locators Selenium Supports?

Following is the list of supported locators by Selenium.
▪ ID: Unique for every web element
▪ Name: Same as ID although it is not unique
▪ CSS Selector: Works on element tags and attributes
▪ XPath: Searches elements in the DOM, Reliable but slow
▪ Class name: Uses the class name attribute
▪ TagName: Uses HTML tags to locate web elements
▪ LinkText: Uses anchor text to locate web elements
▪ Partial Link Text: Uses partial link text to find web elements

**Additional Notes (Selenium 3 & Selenium 4):**

* All these locators are supported in both Selenium 3 and Selenium 4.
* Selenium 4 internally uses the W3C WebDriver standard but locator strategies remain unchanged.
* Best practice is to prefer stable and unique locators to reduce test maintenance.

---

## Which Of The Id, Name, XPath, Or CSS Selector Should You Use?

If the page has unique names or identifiers available, then we should use them.

If they are not available, then go for a CSS selector as it is faster than the XPath.

When none of the preferred locators is present, then you may try the XPath.

**Additional Notes:**

* ID is considered the fastest and most reliable locator.
* CSS selectors are generally faster than XPath in most browsers.
* XPath is powerful and flexible but should be used carefully due to maintenance overhead.

---

## What Is XPath? How Does It Work?

XPath is the most-used locator strategies Selenium uses to find web elements.

▪ It works by navigating through the DOM elements and attributes to locate the target object. For example – a text box or a button or checkboxes.
▪
▪ Although it guarantees to give you the element you are looking after. But it is slower than as compared to other locators like ID, name, or CSS selectors.

**Additional Notes:**

* XPath supports complex conditions such as parent-child, sibling, and ancestor relationships.
* Selenium 4 continues to fully support XPath with no behavioral changes.

---

## What Does A Single Slash “/” Mean In XPath?

A single (forward) slash “/” represents the absolute path.
In this case, the XPath engine navigates the DOM right from the first node.

/html/body/div/div[2]/input

---

## What Does A Double Slash “//” Mean In XPath?

A double (forward) Slash “//” represents the relative path.
In this case, the XPath engine searches for the matching element anywhere in the DOM.

//div//input[@id=’test’]

---

## What Is An Absolute XPath, Explain With Example?

An absolute XPath will always search from the root node until it reaches the target. Such an XPath expression includes the single forward-slash (/) as the prefix.

/html/body/div[1]/div[5]/form/table/tbody/tr[3]/td/input

**Additional Notes:**

* Absolute XPath is fragile and can easily break if the DOM structure changes.
* It is generally not recommended for long-term automation frameworks.

---

## What Is A Relative XPath, Explain With Example?

A relative XPath doesn’t have a specific point to start. It can begin navigation from any node inside the DOM and continues. Such an XPath expression includes the double forward-slash (//), as given below.

//div[@id=app]

**Additional Notes:**

* Relative XPath is more stable and preferred over absolute XPath.
* It is commonly used in real-world automation projects.

---

## How Do You Locate An Element By Partially Comparing Its Attributes In XPath?

XPath supports the contains() method. It allows partially matching of attribute’s value.
It helps when the attributes use dynamic values while having some fixed part.

See the below example-

xPath usage => //*[contains(@category, 'tablet')]

id=test_123
test_234
test_345

//input[contains(@id, ‘test_’)]

The above expression would match all values of the category attribute having the word ‘tablet’ in them.

**Additional Notes:**

* contains() is commonly used for dynamic IDs and classes.
* Other useful XPath functions include starts-with() and ends-with().

---

## How Do You Locate Elements Based On The Text In XPath?

We can call the text() method. The below expression will get elements that have text nodes that equal ‘Python.’

xPath usage = //a[text()='Python']

**Additional Notes:**

* text() is useful for locating links, buttons, and labels.
* It should be used carefully when text changes dynamically.

---

## How Do You Access The Parent Of A Node With XPath?

We can use the double dot (“..”) to point to the parent of any node using the XPath.

For example – The locator //span[@id=”current”]/.. will return the parent of the span element matching id value as ‘current’.

---

## How Do You Get To The Nth Sub-Element Using The XPath?

We can modify the XPath expression to get to the nth element in the following ways:

1. Use XPath as an array by appending the square brackets with an index.

# Example

tr[2]

The above XPath expression will return the second row of a table.

2. By calling position() in the XPath expression

# Example

tr[position()=4]

The above XPath will give the fourth row.

---

## How Do You Use “Class” As A CSS Selector?

We can use the below syntax to access elements using the class CSS selector.

.<class>
e.g. .color

It can help to select all elements related to the specified class.

**Additional Notes:**

* Multiple classes can be chained for better precision.

---

## How Do You Use “ID” As A CSS Selector?

We can use the below syntax to access elements using ID as the CSS selector.

#<ID>
e.g. #name

---

## How To Specify Attribute Value While Using The CSS Selector?

Here is the syntax to provide the attribute value with the CSS selector.

[attribute=value]
e.g. [type=submit]

---

## How To Access The Nth Element Using The CSS Selector?

Here is the syntax to access the nth attribute using the CSS selector.

<type>:nth-child(n)
e.g. tr:nth-child(4)

---

## What Is The Primary Difference Between The XPath And CSS Selectors?

With the XPath, we can traverse both forward and backward, whereas CSS selector only moves forward.

**Additional Notes:**

* XPath supports text-based and parent traversal, CSS does not.
* CSS selectors are generally faster and simpler when applicable.
* Selenium 4 supports both without any functional difference from Selenium 3.