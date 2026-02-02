# Browser DevTools & Console – Beginner to Advanced README

This README is a **step‑by‑step, copy‑paste‑ready guide** to using **Browser Developer Tools (DevTools)** for **automation testing, debugging, and inspection**.

It is written for **beginners**, but goes up to **advanced, real‑world usage** used by Selenium / Playwright engineers.

---

## 1️⃣ What is Browser DevTools?

Browser DevTools (Developer Tools) are built‑in tools in modern browsers (Chrome, Edge, Firefox) that allow you to:

* Inspect HTML & CSS
* Test locators (ID, XPath, CSS, etc.)
* Debug JavaScript
* Analyze network calls
* Measure performance
* Inspect storage (cookies, localStorage)

👉 For automation testers, DevTools is **MANDATORY**.

---

## 2️⃣ How to Open DevTools

### Method 1: Right Click (Most Common)

1. Open any website
2. Right‑click on the page
3. Click **Inspect**

---

### Method 2: Keyboard Shortcuts

| Action                | Shortcut (Windows) |
| --------------------- | ------------------ |
| Open DevTools         | `F12`              |
| Inspect Element       | `Ctrl + Shift + C` |
| Open Console directly | `Ctrl + Shift + J` |

---

## 3️⃣ DevTools Main Tabs Explained

### 🔹 Elements Tab (MOST IMPORTANT)

Used to:

* Inspect HTML structure (DOM)
* View & edit CSS
* Identify attributes for locators

Example HTML:

```html
<input id="username" class="input-field" placeholder="Username">
```

You can find:

* Tag name → `input`
* ID → `username`
* Class → `input-field`

---

### 🔹 Console Tab (SECOND MOST IMPORTANT)

Used to:

* Test XPath / CSS selectors
* Run JavaScript commands
* Debug issues

Example:

```js
$x("//input[@id='username']")
```

---

### 🔹 Sources Tab

Used to:

* Debug JavaScript
* Add breakpoints
* View loaded JS files

---

### 🔹 Network Tab

Used to:

* View API calls (GET / POST)
* Check request & response
* Validate backend integration

---

### 🔹 Performance Tab

Used to:

* Measure page load time
* Detect performance bottlenecks

---

### 🔹 Application Tab

Used to:

* Inspect cookies
* Inspect localStorage / sessionStorage

---

## 4️⃣ Console NOT Showing at Bottom? (Very Common Issue)

### Problem

Console is **NOT visible at the bottom of Elements tab**.

### ✅ FIX 1 (FASTEST)

Press:

```
Esc
```

👉 This toggles the **Console Drawer** at the bottom

---

### ✅ FIX 2 (Dock to Bottom)

1. Click **⋮ (three dots)** in DevTools
2. Select **Dock side → Dock to bottom**
3. Press `Esc`

---

### ✅ FIX 3 (Open Console Fully)

```
Ctrl + Shift + J
```

---

## 5️⃣ Clearing the Console

### Clear Console (Recommended)

```
Ctrl + L
```

OR

```js
clear()
```

⚠️ If console does NOT clear:

* Disable **Preserve log** checkbox

---

## 6️⃣ Testing Locators in Console

### 🔹 ID Locator

```js
document.getElementById("username")
```

---

### 🔹 CSS Selector

Single element:

```js
document.querySelector("input#username")
```

Multiple elements:

```js
document.querySelectorAll(".input-field")
```

Shortcut:

```js
$$(".input-field")
```

---

### 🔹 XPath Locator (Best for Selenium)

```js
$x("//input[@id='username']")
```

Returns array of matched elements

---

### 🔹 XPath by Text

```js
$x("//button[text()='Submit']")
```

---

### 🔹 XPath Contains

```js
$x("//input[contains(@class,'input')]" )
```

---

### 🔹 XPath with Index

```js
$x("(//input[@type='text'])[2]")
```

---

## 7️⃣ All Selenium Locators Explained

### ✅ ID

```java
By.id("username")
```

---

### ✅ Name

```java
By.name("email")
```

---

### ✅ Class Name

```java
By.className("input-field")
```

⚠️ Single class only

---

### ✅ Tag Name

```java
By.tagName("input")
```

---

### ✅ Link Text

```java
By.linkText("Login")
```

---

### ✅ Partial Link Text

```java
By.partialLinkText("Log")
```

---

### ✅ CSS Selector

```java
By.cssSelector("input#username")
```

---

### ✅ XPath

```java
By.xpath("//input[@id='username']")
```

---

## 8️⃣ Calculating Width & Height of Page

### Viewport Width & Height

```js
window.innerWidth
window.innerHeight
```

---

### Full Page Width & Height

```js
document.documentElement.scrollWidth
document.documentElement.scrollHeight
```

---

### Element Width & Height

```js
document.querySelector("#username").offsetWidth
document.querySelector("#username").offsetHeight
```

---

## 9️⃣ Useful Console Helpers

| Command           | Description           |
| ----------------- | --------------------- |
| `$0`              | Last selected element |
| `$x()`            | XPath test            |
| `$$()`            | CSS selector test     |
| `console.table()` | Print table           |

---

## 🔟 Best Practices (Real Projects)

* Prefer **ID > CSS > XPath**
* Avoid absolute XPath
* Avoid index‑based locators
* Use `data-testid` when available
* Keep locators short & stable

---

## ✅ Final Summary

This README covered:

* DevTools basics
* Console usage
* XPath & CSS testing
* All Selenium locators
* Fixing console layout issues
* Page & element dimension calculation

📌 This document can be used for:

* Learning
* Interviews
* Framework design
* Daily automation work

---

Happy Testing 🚀
