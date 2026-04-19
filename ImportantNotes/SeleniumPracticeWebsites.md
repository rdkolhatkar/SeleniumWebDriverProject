# 🌐 Selenium WebDriver — Practice Websites Mega Guide

> A curated, fresher-friendly list of **real-time and demo websites** for practising every Selenium concept — from basic form filling to dynamic JavaScript popups, SSL errors, frames, file uploads, and more.  
> ✅ All sites below are **automation-friendly** — they will NOT ban or block your IP like Amazon, Flipkart, or BookMyShow.

---

## ⚠️ Why You Should NOT Automate Amazon / Flipkart / BookMyShow

```
╔══════════════════════════════════════════════════════════════════════════╗
║  ❌  SITES TO AVOID FOR SELENIUM PRACTICE                                ║
╠══════════════════════════════════════════════════════════════════════════╣
║                                                                          ║
║   🛒 Amazon.in / Amazon.com                                              ║
║      → Uses bot-detection (PerimeterX / CAPTCHA). IP gets blocked        ║
║        after just a few automated runs. May trigger account suspension.  ║
║                                                                          ║
║   🛍️ Flipkart.com                                                        ║
║      → Heavy Cloudflare protection + CAPTCHA. Automation fails often.    ║
║                                                                          ║
║   🎟️ BookMyShow.com                                                      ║
║      → Active bot-blocking. Ticketing systems are legally protected.      ║
║                                                                          ║
║   🚂 IRCTC.co.in                                                         ║
║      → CAPTCHA on every page. Server blocks automated access.            ║
║        Automating IRCTC booking is actually ILLEGAL in India.            ║
║                                                                          ║
║   ✈️ MakeMyTrip / Goibibo                                                ║
║      → Detects headless browsers and dynamic fingerprinting.             ║
║                                                                          ║
╚══════════════════════════════════════════════════════════════════════════╝
```

**Use the websites listed below instead — they are purpose-built for automation practice!**

---

## 📖 Table of Contents

1. [Quick Concept-to-Site Map](#1-quick-concept-to-site-map)
2. [Category A — The All-In-One Practice Sites](#2-category-a--the-all-in-one-practice-sites)
3. [Category B — E-Commerce Demo Sites (Like Amazon/Flipkart)](#3-category-b--e-commerce-demo-sites-like-amazonflipcart)
4. [Category C — Travel & Booking Demo Sites (Like IRCTC/MakeMyTrip)](#4-category-c--travel--booking-demo-sites-like-irctcmakeymytrip)
5. [Category D — Enterprise / HR Application Demo Sites](#5-category-d--enterprise--hr-application-demo-sites)
6. [Category E — SSL Certificate Practice Sites](#6-category-e--ssl-certificate-practice-sites)
7. [Category F — Dynamic JS / AJAX / Popup Practice Sites](#7-category-f--dynamic-js--ajax--popup-practice-sites)
8. [Category G — File Upload & Download Sites](#8-category-g--file-upload--download-sites)
9. [Category H — Frames & iFrames Practice Sites](#9-category-h--frames--iframes-practice-sites)
10. [Category I — Mouse Actions & Drag-Drop Sites](#10-category-i--mouse-actions--drag-drop-sites)
11. [Category J — Banking / Finance Demo Sites](#11-category-j--banking--finance-demo-sites)
12. [Selenium Concept Practice Matrix](#12-selenium-concept-practice-matrix)
13. [Sample Automation Scenarios for Each Site](#13-sample-automation-scenarios-for-each-site)

---

## 1. Quick Concept-to-Site Map

Use this table to instantly find which site to use for which Selenium concept:

| Selenium Concept | Best Site to Practice |
|---|---|
| Form Filling (text, dropdown, radio, checkbox) | `demoqa.com`, `automationpractice.pl` |
| Login with Username/Password Popup | `the-internet.herokuapp.com/basic_auth` |
| JavaScript Alerts / Confirm / Prompt Popups | `the-internet.herokuapp.com/javascript_alerts` |
| Advertisement Popups / Overlays | `demo.guru99.com`, `demoblaze.com` |
| Dynamic Content / AJAX Waits | `the-internet.herokuapp.com/dynamic_loading` |
| SSL Certificate Errors | `badssl.com`, `expired.badssl.com` |
| iFrames / Nested Frames | `the-internet.herokuapp.com/frames`, `demoqa.com` |
| File Upload | `the-internet.herokuapp.com/upload`, `demoqa.com` |
| File Download | `the-internet.herokuapp.com/download` |
| Scrolling (vertical / horizontal) | `magento.softwaretestingboard.com` |
| Mouse Hover, Right-Click, Double Click | `demoqa.com/menu`, `jqueryui.com` |
| Drag and Drop | `jqueryui.com/droppable`, `demoqa.com` |
| Multiple Browser Windows / Tabs | `the-internet.herokuapp.com/windows` |
| Broken Links | `the-internet.herokuapp.com/broken_images` |
| Shadow DOM Elements | `the-internet.herokuapp.com/shadowdom` |
| Tables with Dynamic Data | `demoqa.com/webtables`, `way2automation.com` |
| Date Picker / Calendar Widget | `demoqa.com`, `phptravels.net` |
| Pagination | `magento.softwaretestingboard.com` |
| E-Commerce (Cart, Checkout) | `saucedemo.com`, `opencart demo` |
| Role-Based Login | `saucedemo.com`, `orangehrm-demo.com` |
| CAPTCHA (for learning only) | `google.com/recaptcha/about` (visual study) |

---

## 2. Category A — The All-In-One Practice Sites

These are the **best all-purpose** sites for Selenium. They cover almost all concepts in one place.

---

### 🥇 Site 1 — The Internet (Heroku App)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://the-internet.herokuapp.com/                           │
│   👨‍💻 Created by: Dave Haeffner (Elemental Selenium)                    │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Advanced                              │
│   📦 Type: Multi-purpose testing playground                             │
└─────────────────────────────────────────────────────────────────────────┘
```

**What Makes It Special:** This site is specifically built for Selenium learners. Every page is a different challenge.

**Complete List of Modules Available:**

| Page / Module | URL Path | What You Learn |
|---|---|---|
| AB Testing | `/abtest` | Dynamic page content |
| Basic Auth (HTTP popup) | `/basic_auth` | Username/password popup handling |
| Broken Images | `/broken_images` | Finding and asserting broken elements |
| Challenging DOM | `/challenging_dom` | Dynamic IDs, unstable locators |
| Checkboxes | `/checkboxes` | Selecting/deselecting checkboxes |
| Context Menu (Right Click) | `/context_menu` | Mouse right-click action |
| Digest Authentication | `/digest_auth` | HTTP Digest auth popup |
| Disappearing Elements | `/disappearing_elements` | Handling elements that appear/disappear |
| Drag and Drop | `/drag_and_drop` | Mouse drag-and-drop actions |
| Dropdown | `/dropdown` | `<select>` tag interaction |
| Dynamic Content | `/dynamic_content` | Content that changes on each reload |
| Dynamic Controls | `/dynamic_controls` | Enabling/disabling form elements |
| Dynamic Loading | `/dynamic_loading/1` | Explicit waits, AJAX loading |
| Entry Ad (Modal Popup) | `/entry_ad` | Closing advertisement popups |
| Exit Intent (Popup) | `/exit_intent` | Mouse movement triggered popup |
| File Download | `/download` | Clicking and downloading files |
| File Upload | `/upload` | `sendKeys()` for file upload |
| Floating Menu | `/floating_menu` | Scrolling with sticky navigation |
| Forgot Password | `/forgot_password` | Form submission |
| Form Authentication (Login) | `/login` | Standard login form |
| Frames | `/frames` | Switching between frames |
| Geolocation | `/geolocation` | Browser permission handling |
| Horizontal Slider | `/horizontal_slider` | Slider interaction |
| Hovers | `/hovers` | Mouse hover action |
| Infinite Scroll | `/infinite_scroll` | Scroll-triggered content loading |
| JavaScript Alerts | `/javascript_alerts` | Alert / Confirm / Prompt dialogs |
| JavaScript onload Event Error | `/javascript_error` | Error handling |
| Key Presses | `/key_presses` | Keyboard key events |
| Large & Deep DOM | `/large` | Performance with large pages |
| Multiple Windows | `/windows` | New tab / window handling |
| Notification Messages | `/notification_message` | Flash messages |
| Redirect Link | `/redirector` | Page redirect handling |
| Secure File Download | `/secure_file_download` | Auth + download combo |
| Shadow DOM | `/shadowdom` | Shadow DOM element access |
| Shifting Content | `/shifting_content` | Unstable layouts |
| Slow Resources | `/slow` | Timeout handling |
| Sortable Data Tables | `/tables` | Table data extraction & sorting |
| Status Codes | `/status_codes` | HTTP response codes |
| Typos | `/typos` | Text assertion with typos |
| WYSIWYG Editor | `/tinymce` | Rich text editor (iframe-based) |

**Login Credentials (for `/login` page):**
```
Username: tomsmith
Password: SuperSecretPassword!
```

---

### 🥈 Site 2 — DemoQA (ToolsQA)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demoqa.com/                                           │
│   👨‍💻 Created by: ToolsQA                                               │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Advanced                              │
│   📦 Type: Structured UI testing playground with categories             │
└─────────────────────────────────────────────────────────────────────────┘
```

**What Makes It Special:** Organized into clean categories. Feels like a real enterprise web app. Perfect for practising with Page Object Model (POM).

**Sections Available:**

```
demoqa.com
│
├── 📝 Elements
│   ├── Text Box              ← Form filling, input validation
│   ├── Check Box             ← Tree-style checkboxes (tricky!)
│   ├── Radio Button          ← Radio group handling
│   ├── Web Tables            ← Dynamic table: add/edit/delete rows
│   ├── Buttons               ← Double click, right click, click
│   ├── Links                 ← Status code links
│   ├── Broken Links & Images ← Assertion practice
│   ├── Upload & Download     ← File upload and download ⭐
│   └── Dynamic Properties    ← Elements that load after delay
│
├── 🎨 Forms
│   └── Practice Form         ← Complete registration form with:
│                                 Date picker, gender radio buttons,
│                                 hobby checkboxes, profile picture upload,
│                                 state & city dependent dropdowns ⭐
│
├── 🪟 Alerts, Frame & Windows
│   ├── Browser Windows       ← New tab, new window, new message window
│   ├── Alerts                ← JS Alert, Confirm, Prompt with timer
│   ├── Frames                ← Single frame, nested frames ⭐
│   └── Modal Dialogs         ← Small & Large modal popups ⭐
│
├── 🖱️ Widgets
│   ├── Accordian             ← Expand/collapse sections
│   ├── Auto Complete         ← Multi & single color selector
│   ├── Date Picker           ← Calendar widget with month/year ⭐
│   ├── Slider                ← Range slider
│   ├── Progress Bar          ← Wait for progress
│   ├── Tabs                  ← Tab switching
│   ├── Tool Tips             ← Hover tooltip
│   ├── Menu                  ← Multi-level navigation menu ⭐
│   └── Select Menu           ← Various dropdown types
│
├── 🤝 Interactions
│   ├── Sortable              ← Drag to reorder list items
│   ├── Selectable            ← Click to select items
│   ├── Resizable             ← Resize a box
│   ├── Droppable             ← Drag and drop zones ⭐
│   └── Dragabble             ← Constrained drag actions
│
└── 📚 Book Store Application
    ├── Login / Register      ← Full auth flow
    ├── Book List             ← Dynamic table with search/filter
    ├── Book Detail           ← Navigate to sub-page
    └── Profile               ← User-specific content ⭐
```

---

### 🥉 Site 3 — LambdaTest Selenium Playground

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://www.lambdatest.com/selenium-playground/               │
│   👨‍💻 Created by: LambdaTest                                            │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Intermediate                          │
│   📦 Type: Categorized playground with clean UI                         │
└─────────────────────────────────────────────────────────────────────────┘
```

**Key Modules:**

| Module | What You Practice |
|---|---|
| Simple Form Demo | `sendKeys`, `getText`, form submit |
| Checkbox Demo | Single & multiple checkbox handling |
| Bootstrap Alert Messages | Dynamic message boxes |
| Auto-Healing Test | Unstable locator practice |
| Input Form Submit | Complete real-looking form |
| Ajax Form Submit | AJAX with explicit waits |
| JavaScript Alert & Confirm Box | All 3 alert types |
| JQuery Date Picker | Calendar widget |
| Bootstrap Date Picker | Different datepicker style |
| Table Sort | Sortable table columns |
| Table Data Search | Table filtering |
| Drag & Drop | Mouse drag |
| Drag & Drop Sliders | Range sliders |
| Select Dropdown Demo | Standard and multi-select |
| Bootstrap Multi Select | Complex multi-select |
| File Upload | `sendKeys` file upload |
| Progress Bar | Wait for async operation |
| Window Popup Modal | Modal popup handling |
| iFrame Demo | Frame switching ⭐ |
| Image Carousel | Rotating image slider |

---

### Site 4 — LetCode.in

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://letcode.in/test                                       │
│   👨‍💻 Created by: Koushik                                               │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
│   📦 Type: Selenium-specific challenge site with real problems          │
└─────────────────────────────────────────────────────────────────────────┘
```

| Page | Selenium Topic |
|---|---|
| `/edit` | Input field interaction |
| `/buttons` | All button types |
| `/select` | Dropdowns |
| `/multi-select` | Multi-select boxes |
| `/alert` | Alerts & Prompts |
| `/windows` | Window/tab switching |
| `/frame` | Frames/iFrames |
| `/elements` | Finding elements by various strategies |
| `/waits` | Explicit & implicit waits |
| `/shadow` | Shadow DOM elements |
| `/actions` | Mouse actions |
| `/calendar` | Date picker |
| `/table` | Table operations |
| `/file` | File upload |

---

### Site 5 — UI Testing Playground

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: http://uitestingplayground.com/                               │
│   👨‍💻 Created by: Inflectra                                             │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
│   📦 Type: Designed to make automation HARDER on purpose                │
└─────────────────────────────────────────────────────────────────────────┘
```

> 💡 This site is intentionally tricky — it simulates real-world problems that break badly written automation scripts.

| Challenge | What You Learn |
|---|---|
| Dynamic ID | IDs change on reload — forces good locators |
| Class Attribute | Multiple classes — CSS selector practice |
| Hidden Layers | Overlapping clickable elements |
| Load Delay | Elements load slowly |
| AJAX Data | Wait for AJAX-loaded data |
| Client Side Delay | JS-delayed elements |
| Click | Simple vs. complex click scenarios |
| Text Input | Input verification |
| Scrolling | Auto-scroll to element |
| Dynamic Table | Table cells changing every load |
| Verify Text | Text assertion challenges |
| Progress Bar | Time-based progress |
| Visibility | Visible vs. invisible elements |
| Sample App | End-to-end login-based mini app |
| Mouse Over | Hover interactions |
| Non-Breaking Space | Text with `&nbsp;` — tricky assertions |
| Overlapped Element | Element hidden behind another |
| Shadow DOM | Shadow DOM in practice |

---

## 3. Category B — E-Commerce Demo Sites (Like Amazon/Flipkart)

These sites feel like Amazon or Flipkart but are built specifically for testing.

---

### Site 6 — SauceDemo / Swag Labs ⭐ MOST POPULAR

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://www.saucedemo.com/                                    │
│   👨‍💻 Created by: Sauce Labs                                            │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Intermediate                          │
│   📦 Type: Mock e-commerce store with multiple user roles               │
└─────────────────────────────────────────────────────────────────────────┘
```

**Why It's Like Flipkart/Amazon:**
- Product listing page with images, names, prices
- Add to Cart, Remove from Cart
- Shopping cart, checkout flow
- Complete purchase with shipping + payment form

**Login Credentials (Multiple User Types!):**

| Username | Password | Behavior |
|---|---|---|
| `standard_user` | `secret_sauce` | Normal user — works fine ✅ |
| `locked_out_user` | `secret_sauce` | Login is blocked — tests error messages |
| `problem_user` | `secret_sauce` | Images broken, forms behave oddly |
| `performance_glitch_user` | `secret_sauce` | Pages load slowly — tests implicit/explicit waits |
| `error_user` | `secret_sauce` | Some actions throw errors |
| `visual_user` | `secret_sauce` | Visual/layout issues |

**Selenium Scenarios You Can Practice:**

```
SCENARIO 1 — End-to-End Purchase Flow
  1. Open https://www.saucedemo.com
  2. Enter username: standard_user, password: secret_sauce
  3. Click Login button
  4. Assert product page is visible
  5. Sort products by "Price (low to high)"
  6. Click "Add to cart" for first 2 products
  7. Click cart icon (badge should show "2")
  8. Click Checkout
  9. Fill shipping form: First name, Last name, Zip code
  10. Click Continue → verify items on overview page
  11. Click Finish → assert "Thank you for your order" message

SCENARIO 2 — Negative Login Test
  1. Login with locked_out_user / secret_sauce
  2. Assert error message: "Sorry, this user has been locked out."

SCENARIO 3 — Sorting Test
  1. Login → verify default sort is "Name (A to Z)"
  2. Change sort to "Price (high to low)"
  3. Assert first product has the highest price
```

---

### Site 7 — Magento Sample Store (Luma)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://magento.softwaretestingboard.com/                     │
│   👨‍💻 Created by: Magento / Software Testing Board                      │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
│   📦 Type: Full-featured e-commerce like Amazon — most realistic!       │
└─────────────────────────────────────────────────────────────────────────┘
```

**Why It's the Most Realistic Amazon/Flipkart Replacement:**
- Top navigation with mega menus
- Category pages with filters (price, size, color, material)
- Product listing with pagination (scroll + click next page)
- Product detail page with size/color selection
- Wishlist functionality
- Shopping cart & full checkout
- User registration and login
- Address book management
- Order history
- Newsletter subscription
- Review/ratings system

**Selenium Scenarios You Can Practice:**

```
SCENARIO — Product Search & Filter
  1. Open the site
  2. Search for "Yoga pants" in search bar
  3. Apply filter: Color = Blue
  4. Apply filter: Price = $25.00 - $50.00
  5. Sort by "Price: Low to High"
  6. Scroll down to see all products (pagination)
  7. Click on second page
  8. Add first product to wishlist (requires login)
  9. Add second product to cart
  10. Verify cart count updates

SCENARIO — User Registration
  1. Go to: /customer/account/create/
  2. Fill in: First name, Last name, Email, Password, Confirm Password
  3. Check "Subscribe to newsletter" checkbox
  4. Submit and assert: "Thank you for registering"

SCENARIO — Mega Menu Navigation
  1. Hover over "Women" menu item
  2. Hover over "Tops" sub-menu
  3. Click "Jackets"
  4. Assert: Page title is "Jackets"
```

---

### Site 8 — nopCommerce Demo

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demo.nopcommerce.com/                                 │
│   👨‍💻 Created by: nopCommerce                                           │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate                                      │
│   📦 Type: Full e-commerce with Admin + Customer panels                  │
└─────────────────────────────────────────────────────────────────────────┘
```

**Admin Panel Access:**
```
URL:      https://admin-demo.nopcommerce.com/admin/
Username: admin@yourstore.com
Password: admin
```

**What You Can Practice:**
- Customer-facing store automation (browse, search, buy)
- Admin panel automation (add products, manage orders)
- Login for both customer and admin
- Product review submission
- Coupon code application
- Guest vs. registered checkout flow

---

### Site 9 — OpenCart Demo

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demo.opencart.com/                                    │
│   👨‍💻 Created by: OpenCart                                              │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate                                      │
│   📦 Type: E-commerce with Admin dashboard                               │
└─────────────────────────────────────────────────────────────────────────┘
```

**Admin Access:**
```
URL:      https://demo.opencart.com/admin/
Username: demo
Password: demo
```

**Key Selenium Scenarios:**
- Product catalog navigation with categories
- Search with autocomplete dropdown
- Multiple product variants (size, color) selection
- Compare products feature
- Wish list management
- Currency and language switcher dropdowns

---

### Site 10 — Automation Practice (automationpractice.pl)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://automationpractice.pl/index.php                       │
│   👨‍💻 Created by: Community (clone of PrestaShop)                       │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Intermediate                          │
│   📦 Type: Realistic online clothing store                               │
└─────────────────────────────────────────────────────────────────────────┘
```

**Why It Looks Like a Real Site:**
- Real clothing categories (Dresses, T-shirts, Blouses)
- Quick view popup on product hover
- Cart slider on "Add to Cart"
- Promo newsletter subscription popup on first visit ⭐
- Contact form with file attachment
- Live search with autocomplete results dropdown

**Key Scenarios:**
```
SCENARIO — Search with Autocomplete
  1. Click the search box at the top
  2. Type "Dress" slowly (character by character)
  3. Wait for autocomplete suggestions to appear
  4. Assert suggestions contain the word "Dress"
  5. Click on first suggestion

SCENARIO — Newsletter Popup Handling (Advertisement Popup)
  1. Open the site for the first time
  2. Detect the newsletter popup that appears
  3. Close it by clicking the X button
  4. Assert: Popup is no longer visible
```

---

### Site 11 — DemoBlaze (Demo Store)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://www.demoblaze.com/                                    │
│   👨‍💻 Created by: Blazemeter                                            │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner                                          │
│   📦 Type: Electronics store — phone/laptop/monitor                      │
└─────────────────────────────────────────────────────────────────────────┘
```

**What's Special for Selenium:**
- JavaScript modal popup for Sign Up and Login ⭐ (not a form page — it's a JS popup!)
- Dynamic product cards loaded via AJAX
- Categories switch product list dynamically
- Alert box appears after adding to cart
- Cart page with dynamic table

**Key Scenarios:**
```
SCENARIO — JS Modal Login Popup
  1. Click "Log in" in the nav bar
  2. Wait for JavaScript MODAL to appear (not a page — it's a popup!)
  3. Enter Username and Password inside the popup
  4. Click Login button inside the popup
  5. Assert: Nav bar now shows "Welcome username"

SCENARIO — Add to Cart with Alert
  1. Login first (see above)
  2. Click on any product (e.g., "Samsung galaxy s6")
  3. Click "Add to cart" button
  4. Handle the JavaScript Alert that says "Product added"
  5. Click OK on the alert
  6. Navigate to Cart and assert the product is there
```

---

## 4. Category C — Travel & Booking Demo Sites (Like IRCTC/MakeMyTrip)

---

### Site 12 — PHP Travels Demo

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://phptravels.net/demo                                   │
│   👨‍💻 Created by: PHPTravels                                            │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
│   📦 Type: Full-featured travel portal like MakeMyTrip/Goibibo          │
└─────────────────────────────────────────────────────────────────────────┘
```

**This is the CLOSEST demo to IRCTC / MakeMyTrip you can automate freely!**

**What's Available:**
- Flight search with From / To / Date / Passengers
- Hotel search with destination, dates, guests
- Car rentals, Tours, Visa applications
- Calendar date pickers (complex!) ⭐
- Auto-complete destination fields ⭐
- Multi-step booking flow with payment page
- Admin panel for managing bookings

**Login Credentials:**
```
Admin:
  URL: https://phptravels.net/admin
  Email: admin@phptravels.com
  Password: demoadmin

Customer:
  Email: user@phptravels.com
  Password: demouser
```

**Key Selenium Scenarios:**
```
SCENARIO — Flight Search (Like IRCTC/MakeMyTrip)
  1. Click "Flights" tab
  2. Select "One Way" trip
  3. Click "From" field → type "Mumbai" → select from dropdown
  4. Click "To" field → type "Delhi" → select from dropdown
  5. Click departure date field
  6. Handle calendar popup → select date 15 days from today
  7. Select "2 Adults" from passengers dropdown
  8. Click "Search" button
  9. Assert: Flight results are displayed

SCENARIO — Hotel Search with Calendar
  1. Click "Hotels" tab
  2. Enter city: "Goa" → select from autocomplete
  3. Click Check-in date → select from calendar widget
  4. Click Check-out date → select from calendar widget
  5. Select Rooms: 2, Adults: 2
  6. Search and assert results appear
```

---

### Site 13 — GoFly Demo (Another Travel Demo)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demo.goflysolutions.com/                              │
│   👨‍💻 Created by: GoFly Solutions                                       │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate                                      │
│   📦 Type: Airline booking demo site                                     │
└─────────────────────────────────────────────────────────────────────────┘
```

**What You Can Practice:**
- Round-trip vs. One-way radio button selection
- Calendar date pickers with month navigation
- Passenger count stepper buttons
- Search results with dynamic fare loading
- Seat selection interface

---

## 5. Category D — Enterprise / HR Application Demo Sites

---

### Site 14 — OrangeHRM Demo ⭐ CLASSIC PRACTICE SITE

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://opensource-demo.orangehrmlive.com/                    │
│   👨‍💻 Created by: OrangeHRM                                             │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
│   📦 Type: HR Management System (like a corporate internal tool)        │
└─────────────────────────────────────────────────────────────────────────┘
```

**Login Credentials:**
```
Username: Admin
Password: admin123
```

**Why It's Great for Learning:**
- Feels like a real enterprise application
- Role-based access (Admin vs. Employee vs. HR Manager)
- Complex forms with dependent dropdowns ⭐
- Dynamic tables with search, filter, sort ⭐
- Modal dialogs for confirmation
- Date picker widgets
- Photo/file upload for employee profiles ⭐
- Left sidebar navigation

**Key Selenium Scenarios:**
```
SCENARIO — Add a New Employee
  1. Login with Admin/admin123
  2. Click "PIM" → "Add Employee"
  3. Enter First Name, Last Name
  4. Upload a profile photo (file upload!)
  5. Note the auto-generated Employee ID
  6. Enable "Create Login Details" toggle
  7. Enter username and password for the new employee
  8. Select Role from dropdown
  9. Click Save
  10. Assert success message and employee appears in list

SCENARIO — Search Employee with Filter
  1. Go to PIM → Employee List
  2. Enter employee name in search field
  3. Select Department from dropdown
  4. Click Search
  5. Assert filtered results in the table
  6. Verify correct employee appears in results

SCENARIO — Leave Management
  1. Login → Click "Leave" → "Apply"
  2. Select Leave Type from dropdown
  3. Click From Date → Use calendar picker
  4. Click To Date → Use calendar picker
  5. Enter comments in text area
  6. Submit and assert "Successfully Submitted"
```

---

### Site 15 — Guru99 Bank Demo

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demo.guru99.com/V4/                                   │
│   👨‍💻 Created by: Guru99                                                │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Intermediate                          │
│   📦 Type: Banking application demo                                      │
└─────────────────────────────────────────────────────────────────────────┘
```

> Note: Get credentials by registering at https://demo.guru99.com/

**Features Available:**
- New Customer creation form
- New Account creation with account type dropdown
- Fund Transfer between accounts
- Balance Enquiry
- Mini Statement
- Delete Account / Delete Customer
- Manager login with multiple pages
- Form validation with real error messages

---

## 6. Category E — SSL Certificate Practice Sites

These sites are specifically for practising SSL error handling in Selenium.

---

### Site 16 — BadSSL.com ⭐ BEST FOR SSL PRACTICE

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://badssl.com/                                           │
│   👨‍💻 Created by: Google / BadSSL Contributors                          │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate                                      │
│   📦 Type: SSL certificate scenarios for testing                        │
└─────────────────────────────────────────────────────────────────────────┘
```

**All SSL Scenarios Available:**

| URL | SSL Issue | What You Learn |
|---|---|---|
| `https://expired.badssl.com/` | Expired certificate | Accept/ignore expired SSL |
| `https://wrong.host.badssl.com/` | Wrong hostname | Handle hostname mismatch |
| `https://self-signed.badssl.com/` | Self-signed cert | Bypass self-signed certificate |
| `https://untrusted-root.badssl.com/` | Untrusted root CA | Handle untrusted CA |
| `https://no-subject.badssl.com/` | Missing subject | Various cert fields |
| `https://incomplete-chain.badssl.com/` | Incomplete cert chain | Chain validation |
| `https://sha1-intermediate.badssl.com/` | Weak SHA-1 algorithm | Algorithm-based errors |
| `https://rc4.badssl.com/` | RC4 cipher (weak) | Cipher suite issues |
| `http://http.badssl.com/` | Plain HTTP (no SSL) | No certificate at all |

**Selenium Code to Handle SSL in Chrome:**
```java
// Java — ChromeOptions to ignore SSL errors
ChromeOptions options = new ChromeOptions();
options.setAcceptInsecureCerts(true);  // ← This is the key!

WebDriver driver = new ChromeDriver(options);
driver.get("https://expired.badssl.com/");
System.out.println("Title: " + driver.getTitle());
```

**Selenium Code to Handle SSL in Firefox:**
```java
// Java — FirefoxOptions to ignore SSL errors
FirefoxOptions options = new FirefoxOptions();
options.setAcceptInsecureCerts(true);  // ← Works the same way

WebDriver driver = new FirefoxDriver(options);
driver.get("https://self-signed.badssl.com/");
```

---

## 7. Category F — Dynamic JS / AJAX / Popup Practice Sites

---

### Site 17 — Rahul Shetty Academy Practice Site

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://rahulshettyacademy.com/seleniumPractise/              │
│   👨‍💻 Created by: Rahul Shetty Academy                                  │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Beginner → Advanced                              │
│   📦 Type: E-commerce + various Selenium elements                       │
└─────────────────────────────────────────────────────────────────────────┘
```

**Key Features:**
- Accordion sections that expand/collapse
- Shop with product cards and Add to Cart
- Dynamic search with suggestion dropdown ⭐
- Bootstrap alerts (green/red messages)
- Checkout with coupon code validation
- Dynamic dropdown (Country selection)
- Mouse hover cards with hidden "Add" buttons ⭐

```
SCENARIO — Dynamic Dropdown
  1. Go to: https://rahulshettyacademy.com/dropdownsPractise/
  2. Click "AutoSuggest DropDown" field
  3. Type "Ind"
  4. Wait for the dropdown suggestions to appear (AJAX!)
  5. Select "India" from the list
  6. Assert the field now shows "India"
```

**Other URLs on Rahul Shetty's site:**

| URL | Practice Topic |
|---|---|
| `/loginpagePractise/` | Login page + checkbox + radio + dropdown |
| `/dropdownsPractise/` | Country auto-suggest + jQuery dropdowns |
| `/angularJs-protractor/registrationLogin/` | Angular JS form |
| `/Handle-Alerts-ConfirmBox-Popup.html` | All popup types |
| `/AutomationPractice` | Full UI playground |
| `/upload-file/` | File upload scenarios |

---

### Site 18 — Selenium Easy

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://demo.seleniumeasy.com/                                │
│   👨‍💻 Created by: SeleniumEasy                                          │
│   🛡️ Bot Blocking: NONE ✅ (has an initial popup to handle!)            │
│   💡 Difficulty Level: Beginner → Intermediate                          │
│   📦 Type: Categorized examples for every Selenium concept              │
└─────────────────────────────────────────────────────────────────────────┘
```

> 💡 **Bonus Challenge:** This site itself has an **advertisement modal popup** that appears when you first load it. Closing it is your first Selenium task!

**What's Included:**

```
Input Forms
  ├── Simple Form Demo
  ├── Multi Select List Demo
  ├── Checkbox Demo
  ├── Select Dropdown List Demo
  ├── Radio Buttons Demo
  ├── Connect Form Demo
  ├── Ajax Form Submit
  └── Java Script Alerts

Wait for Element
  ├── Bootstrap Alerts
  ├── Dynamic DataTable
  └── Loading buttons

iFrame Demo
  └── iFrame with Editor

Table Examples
  ├── Table Sort and Search Demo
  ├── Data Table Demo
  └── Fixed Header Table

Input Form
  ├── Bootstrap Date Picker Demo
  └── jQuery Date Picker Demo

Mouse / Keyboard Events
  ├── Drag and Drop Demo
  ├── Slider Demo
  └── Resizable Demo

Bootstrap Examples
  ├── Modals
  ├── Tooltip and Popover Demo
  ├── Progress Bar Demo
  └── Multiple Windows

File Operations
  └── File Upload Demo
```

---

## 8. Category G — File Upload & Download Sites

---

### Site 19 — The Internet — Upload & Download Pages

```
┌─────────────────────────────────────────────────────────────────────────┐
│   Upload URL:   https://the-internet.herokuapp.com/upload               │
│   Download URL: https://the-internet.herokuapp.com/download             │
│   🛡️ Bot Blocking: NONE ✅                                              │
└─────────────────────────────────────────────────────────────────────────┘
```

**File Upload — Two Approaches:**

```java
// APPROACH 1 — sendKeys (simplest, works for visible file inputs)
WebElement uploadInput = driver.findElement(By.id("file-upload"));
uploadInput.sendKeys("C:\\Users\\YourName\\Desktop\\testfile.txt");
driver.findElement(By.id("file-submit")).click();
// Assert success message
String msg = driver.findElement(By.id("uploaded-files")).getText();
assert msg.equals("testfile.txt");

// APPROACH 2 — Robot class (for OS-level file dialog)
driver.findElement(By.id("file-upload")).click();
Thread.sleep(2000);
Robot robot = new Robot();
StringSelection ss = new StringSelection("C:\\file.txt");
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
robot.keyPress(KeyEvent.VK_CONTROL);
robot.keyPress(KeyEvent.VK_V);
robot.keyRelease(KeyEvent.VK_V);
robot.keyRelease(KeyEvent.VK_CONTROL);
robot.keyPress(KeyEvent.VK_ENTER);
robot.keyRelease(KeyEvent.VK_ENTER);
```

**File Download — With Chrome Preferences:**
```java
HashMap<String, Object> prefs = new HashMap<>();
prefs.put("download.default_directory", "C:\\Downloads");
prefs.put("download.prompt_for_download", false);
prefs.put("download.directory_upgrade", true);

ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("prefs", prefs);
WebDriver driver = new ChromeDriver(options);

driver.get("https://the-internet.herokuapp.com/download");
driver.findElement(By.linkText("some-file.txt")).click();
// File automatically downloads to C:\Downloads
```

---

### Site 20 — DemoQA File Upload Section

```
URL: https://demoqa.com/upload-download
```

Has both upload (with file path input) and download (button click triggers download). Good for practising asserting the filename after upload.

---

## 9. Category H — Frames & iFrames Practice Sites

Frames are one of the trickiest Selenium concepts. These sites give you dedicated practice.

---

### Site 21 — The Internet — Frames

```
┌─────────────────────────────────────────────────────────────────────────┐
│   iFrame URL: https://the-internet.herokuapp.com/iframe                 │
│   Frames URL: https://the-internet.herokuapp.com/frames                 │
│   Nested Frames: https://the-internet.herokuapp.com/nested_frames       │
│   🛡️ Bot Blocking: NONE ✅                                              │
└─────────────────────────────────────────────────────────────────────────┘
```

**Frame Switching — Code Examples:**

```java
// Switch to frame by ID or Name
driver.switchTo().frame("mce_0_ifr");   // ← frame ID

// Switch to frame by index (0 = first frame on page)
driver.switchTo().frame(0);

// Switch to frame by WebElement
WebElement frameElement = driver.findElement(By.tagName("iframe"));
driver.switchTo().frame(frameElement);

// Do your work inside the frame
driver.findElement(By.id("tinymce")).sendKeys("Hello from inside the iframe!");

// ⚠️ VERY IMPORTANT: Always switch back to main page after!
driver.switchTo().defaultContent();

// Switch to parent frame (when nested frames)
driver.switchTo().parentFrame();
```

**Nested Frames Structure:**
```
Page
  └── TOP frame
       └── LEFT frame (has text "LEFT")
       └── MIDDLE frame (has text "MIDDLE")
       └── RIGHT frame (has text "RIGHT")
  └── BOTTOM frame (has text "BOTTOM")
```

---

### Site 22 — W3Schools TryIt Editor (iframe inside a page)

```
URL: https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic
```

Practice switching into the "result frame" and asserting content inside it. The entire preview area is an iframe.

---

## 10. Category I — Mouse Actions & Drag-Drop Sites

---

### Site 23 — jQuery UI (Official Site)

```
┌─────────────────────────────────────────────────────────────────────────┐
│   Drag & Drop: https://jqueryui.com/droppable/                          │
│   Sortable:    https://jqueryui.com/sortable/                           │
│   Resizable:   https://jqueryui.com/resizable/                          │
│   Selectable:  https://jqueryui.com/selectable/                         │
│   Slider:      https://jqueryui.com/slider/                             │
│   🛡️ Bot Blocking: NONE ✅                                              │
└─────────────────────────────────────────────────────────────────────────┘
```

> ⚠️ Note: jQuery UI's drag-drop uses iframes to show demos. Switch to the iframe first before interacting.

**Mouse Actions Code Examples:**

```java
Actions actions = new Actions(driver);

// --- HOVER (Mouse Over) ---
WebElement menuItem = driver.findElement(By.id("ui-id-1"));
actions.moveToElement(menuItem).perform();

// --- RIGHT CLICK ---
WebElement element = driver.findElement(By.id("div1"));
actions.contextClick(element).perform();

// --- DOUBLE CLICK ---
WebElement btn = driver.findElement(By.id("btn"));
actions.doubleClick(btn).perform();

// --- DRAG AND DROP ---
WebElement source = driver.findElement(By.id("draggable"));
WebElement target = driver.findElement(By.id("droppable"));
actions.dragAndDrop(source, target).perform();

// --- DRAG BY OFFSET (pixels) ---
actions.dragAndDropBy(source, 200, 0).perform();

// --- CLICK AND HOLD ---
actions.clickAndHold(source).moveToElement(target).release().perform();

// --- KEYBOARD ACTIONS ---
actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
```

---

### Site 24 — GlobeSQA Drag-Drop

```
URL: https://www.globalsqa.com/demo-site/draganddrop/
```

Has multiple Drag-and-Drop demos: HTML5-based and jQuery-based (they require different Selenium handling techniques).

---

## 11. Category J — Banking / Finance Demo Sites

---

### Site 25 — Way2Automation Banking Application

```
┌─────────────────────────────────────────────────────────────────────────┐
│   🌐 URL: https://www.way2automation.com/angularjs-protractor/banking/  │
│   📦 Type: Angular JS Banking App                                        │
│   🛡️ Bot Blocking: NONE ✅                                              │
│   💡 Difficulty Level: Intermediate → Advanced                          │
└─────────────────────────────────────────────────────────────────────────┘
```

**What This Teaches (Angular-specific challenges):**
- Angular JS dynamic elements (require special handling)
- Bank Manager login → Add Customer → Open Account
- Customer login → Deposit → Withdrawal
- Dynamic table of accounts and transactions
- Transaction history with date filtering

**Key Scenarios:**
```
SCENARIO — Bank Manager Creates Account
  1. Click "Bank Manager Login"
  2. Click "Add Customer" tab
  3. Fill: First Name, Last Name, Post Code
  4. Click Add Customer button
  5. Handle JS Alert that shows new customer ID
  6. Click "Open Account" tab
  7. Select the created customer from dropdown
  8. Select Currency: Rupee
  9. Click Process → Handle Alert with Account Number

SCENARIO — Customer Makes a Deposit
  1. Click "Customer Login"
  2. Select customer name from dropdown
  3. Click Login
  4. Note current balance
  5. Click "Deposit"
  6. Enter amount (e.g. 5000)
  7. Click Deposit button
  8. Assert: balance increased by 5000
  9. Check transaction table for the deposit entry
```

---

## 12. Selenium Concept Practice Matrix

Use this matrix to pick your site based on what you are practising today:

```
╔═══════════════════════════════════╦═══╦═══╦════╦════╦═════╦═══════╦══════╦════╦══════╗
║ CONCEPT                           ║ 1 ║ 2 ║ 3  ║ 4  ║  5  ║  6    ║  7   ║ 16 ║  25  ║
║                                   ║Her║DQA║LTst║Let ║UITP ║Sauce  ║Magto ║SSL ║Way2A ║
╠═══════════════════════════════════╬═══╬═══╬════╬════╬═════╬═══════╬══════╬════╬══════╣
║ Basic Form Filling                ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║  ✅ ║  ✅   ║  ✅  ║    ║  ✅  ║
║ Login Page                        ║ ✅ ║ ✅ ║    ║    ║  ✅ ║  ✅   ║  ✅  ║    ║  ✅  ║
║ HTTP Auth Popup (Browser Popup)   ║ ✅ ║    ║    ║    ║     ║       ║      ║    ║      ║
║ JavaScript Alert / Confirm        ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║     ║       ║      ║    ║  ✅  ║
║ JS Modal Popups (Overlay)         ║ ✅ ║ ✅ ║ ✅ ║    ║     ║       ║      ║    ║      ║
║ Advertisement Popup               ║ ✅ ║    ║    ║    ║     ║       ║      ║    ║      ║
║ iFrame / Nested Frames            ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║     ║       ║      ║    ║      ║
║ Shadow DOM                        ║ ✅ ║    ║    ║ ✅ ║  ✅ ║       ║      ║    ║      ║
║ Dynamic Loading / Explicit Waits  ║ ✅ ║ ✅ ║    ║ ✅ ║  ✅ ║  ✅   ║      ║    ║      ║
║ Dropdown (static)                 ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║     ║  ✅   ║  ✅  ║    ║  ✅  ║
║ Dropdown (dynamic/AJAX)           ║    ║    ║    ║ ✅ ║     ║       ║  ✅  ║    ║  ✅  ║
║ Checkboxes & Radio Buttons        ║ ✅ ║ ✅ ║ ✅ ║    ║     ║       ║      ║    ║      ║
║ Date Picker / Calendar            ║    ║ ✅ ║ ✅ ║ ✅ ║     ║       ║      ║    ║      ║
║ File Upload                       ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║     ║       ║      ║    ║  ✅  ║
║ File Download                     ║ ✅ ║ ✅ ║    ║    ║     ║       ║      ║    ║      ║
║ Drag and Drop                     ║ ✅ ║ ✅ ║ ✅ ║    ║     ║       ║      ║    ║      ║
║ Mouse Hover                       ║ ✅ ║ ✅ ║    ║    ║  ✅ ║       ║  ✅  ║    ║      ║
║ Right Click / Double Click        ║ ✅ ║ ✅ ║    ║ ✅ ║  ✅ ║       ║      ║    ║      ║
║ Keyboard Actions                  ║ ✅ ║    ║    ║    ║     ║       ║      ║    ║      ║
║ Scroll (vertical)                 ║    ║    ║    ║    ║  ✅ ║       ║  ✅  ║    ║      ║
║ Multiple Windows / Tabs           ║ ✅ ║ ✅ ║    ║ ✅ ║     ║       ║      ║    ║      ║
║ Dynamic Tables                    ║ ✅ ║ ✅ ║ ✅ ║ ✅ ║  ✅ ║       ║      ║    ║  ✅  ║
║ SSL Certificate Errors            ║    ║    ║    ║    ║     ║       ║      ║ ✅ ║      ║
║ Broken Links / Images             ║ ✅ ║ ✅ ║    ║    ║     ║       ║      ║    ║      ║
║ Rich Text Editor (TinyMCE/iframe) ║ ✅ ║    ║    ║    ║     ║       ║      ║    ║      ║
║ E-Commerce Full Flow              ║    ║    ║    ║    ║     ║  ✅   ║  ✅  ║    ║      ║
║ Role-Based Login Testing          ║    ║    ║    ║    ║     ║  ✅   ║      ║    ║  ✅  ║
║ Angular JS Elements               ║    ║    ║    ║    ║     ║       ║      ║    ║  ✅  ║
╚═══════════════════════════════════╩═══╩═══╩════╩════╩═════╩═══════╩══════╩════╩══════╝

Legend: Her=Herokuapp  DQA=DemoQA  LTst=LambdaTest  Let=LetCode
        UITP=UITestingPlayground  Sauce=SauceDemo  Magto=Magento  Way2A=Way2Automation
```

---

## 13. Sample Automation Scenarios for Each Site

### 🗺️ Beginner Learning Path (Follow This Order)

```
WEEK 1 — Basics
  Day 1-2 : SauceDemo → Login, navigate, add to cart, checkout
  Day 3   : The Internet → Basic auth popup, checkboxes, dropdowns
  Day 4   : DemoQA Elements → Text box, radio, checkbox, buttons
  Day 5   : DemoQA Alerts → JS alert, confirm, prompt

WEEK 2 — Intermediate
  Day 1-2 : The Internet → iFrames, nested frames, dynamic loading
  Day 3   : DemoQA Widgets → Date picker, menu, tooltips
  Day 4   : DemoQA Interactions → Drag and drop, sortable
  Day 5   : The Internet → File upload + download

WEEK 3 — Advanced
  Day 1   : BadSSL.com → SSL error handling with Chrome/Firefox options
  Day 2   : UI Testing Playground → Dynamic IDs, unstable locators
  Day 3   : OrangeHRM → Full employee management automation
  Day 4   : Magento → E-commerce with scroll + pagination + filters
  Day 5   : Way2Automation Bank → AngularJS dynamic elements

WEEK 4 — Real-World Projects
  Day 1-2 : PHPTravels → Travel booking end-to-end (like IRCTC)
  Day 3   : OrangeHRM → Build Page Object Model (POM) framework
  Day 4-5 : DemoBlaze → JS popup login + add to cart + checkout end-to-end
```

---

### Complete Site List — Quick Reference

| # | Site Name | URL | Best For | Difficulty |
|---|---|---|---|---|
| 1 | The Internet (Heroku) | `the-internet.herokuapp.com` | Everything (all concepts) | ⭐⭐⭐ |
| 2 | DemoQA | `demoqa.com` | Structured all-in-one practice | ⭐⭐⭐ |
| 3 | LambdaTest Playground | `lambdatest.com/selenium-playground` | Guided scenarios | ⭐⭐ |
| 4 | LetCode | `letcode.in/test` | Targeted challenges | ⭐⭐⭐ |
| 5 | UI Testing Playground | `uitestingplayground.com` | Advanced locator challenges | ⭐⭐⭐⭐ |
| 6 | SauceDemo (Swag Labs) | `saucedemo.com` | E-commerce flow | ⭐⭐ |
| 7 | Magento Luma | `magento.softwaretestingboard.com` | Realistic e-commerce | ⭐⭐⭐⭐ |
| 8 | nopCommerce | `demo.nopcommerce.com` | Admin + Customer portal | ⭐⭐⭐ |
| 9 | OpenCart | `demo.opencart.com` | E-commerce with admin | ⭐⭐⭐ |
| 10 | AutomationPractice | `automationpractice.pl` | Real-looking store | ⭐⭐⭐ |
| 11 | DemoBlaze | `demoblaze.com` | JS modal popups | ⭐⭐ |
| 12 | PHPTravels | `phptravels.net/demo` | Travel booking (like IRCTC) | ⭐⭐⭐⭐ |
| 13 | GoFly Demo | `demo.goflysolutions.com` | Airline booking | ⭐⭐⭐ |
| 14 | OrangeHRM | `opensource-demo.orangehrmlive.com` | HR Enterprise app | ⭐⭐⭐ |
| 15 | Guru99 Bank | `demo.guru99.com/V4/` | Banking app | ⭐⭐ |
| 16 | BadSSL | `badssl.com` | SSL certificate errors | ⭐⭐⭐ |
| 17 | Rahul Shetty Academy | `rahulshettyacademy.com/seleniumPractise` | AJAX/dynamic elements | ⭐⭐⭐ |
| 18 | SeleniumEasy | `demo.seleniumeasy.com` | All basic concepts | ⭐⭐ |
| 19 | The Internet Upload/Download | `the-internet.herokuapp.com/upload` | File operations | ⭐⭐ |
| 20 | DemoQA Upload | `demoqa.com/upload-download` | File upload & download | ⭐⭐ |
| 21 | The Internet Frames | `the-internet.herokuapp.com/frames` | iFrames / Nested frames | ⭐⭐⭐ |
| 22 | W3Schools TryIt | `w3schools.com/html/tryit.asp` | iFrame inside page | ⭐⭐ |
| 23 | jQuery UI | `jqueryui.com/droppable` | Drag and drop | ⭐⭐⭐ |
| 24 | GlobeSQA Drag-Drop | `globalsqa.com/demo-site/draganddrop` | HTML5 + jQuery drag-drop | ⭐⭐⭐ |
| 25 | Way2Automation Bank | `way2automation.com/angularjs-protractor/banking` | AngularJS + tables | ⭐⭐⭐⭐ |

---

> 📝 **Note:** Demo sites occasionally go down for maintenance. If a site is temporarily unavailable, try another from the same category. All these sites are free and have been stable for years.

> 💡 **Pro Tip for Freshers:** Always start with `the-internet.herokuapp.com` and `demoqa.com`. These two sites alone can help you master **90% of all Selenium interview questions**.

---

*📦 Maintained for: Selenium WebDriver (Java / Python / C#)*  
*🧪 Use with: TestNG / JUnit / PyTest / NUnit*  
*🗓️ Last Verified: 2025*
