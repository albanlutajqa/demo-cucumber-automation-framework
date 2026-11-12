# Demo Cucumber Automation Framework

[![UI Tests](https://github.com/albanlutajqa/demo-cucumber-automation-framework/actions/workflows/tests.yml/badge.svg)](https://github.com/albanlutajqa/demo-cucumber-automation-framework/actions/workflows/tests.yml)

End-to-end UI test automation framework built with **Java, Selenium WebDriver and Cucumber (BDD)**, running against [SauceDemo](https://www.saucedemo.com/), a public practice e-commerce site.

Anyone can clone and run it: no private test environment and no secret credentials are needed. The full regression suite runs automatically on every push with **GitHub Actions**.

---

## Tech stack

| Area | Tools |
|---|---|
| Language | Java 17 |
| UI automation | Selenium WebDriver 4 (Selenium Manager handles the browser drivers) |
| BDD | Cucumber 7 (Gherkin) + JUnit 4 runner |
| Design pattern | Page Object Model with Page Factory |
| Build | Maven |
| CI/CD | GitHub Actions (headless Chrome) |
| Reports | Cucumber HTML/JSON report, masterthought cucumber-reporting, screenshots |

## What is tested

| Feature | Scenarios |
|---|---|
| **Login** | Valid login, locked-out user, wrong username or password, missing fields (Scenario Outline), logout |
| **Products** | All 6 products displayed (data table), sort by price low to high and high to low, sort by name Z to A |
| **Cart** | Add one product, add several products (data table), remove a product, cart badge count |
| **Checkout** | Full end-to-end purchase, price calculation (item total = sum of prices, total = item total + tax), required field validation (Scenario Outline) |

18 scenarios in total, tagged `@regression`, with the most important ones also tagged `@smoke`.

## Project structure

```
src/test
├── java/com/demo
│   ├── pages       # Page Objects: LoginPage, ProductsPage, CartPage, CheckoutPage
│   ├── steps       # Step definitions + Hooks (browser setup, screenshots, teardown)
│   ├── runners     # RegressionRunner (CI), TestRunner (local), FailedRunner (re-runs failures)
│   ├── testbase    # BaseClass (driver setup) and PageInitializer
│   └── utils       # CommonMethods (explicit waits, clicks, dropdowns, screenshots), ConfigsReader, Constants
└── resources
    ├── features    # Gherkin feature files
    └── configs     # configuration.properties
.github/workflows   # GitHub Actions pipeline
```

## Framework highlights

- **Readable BDD scenarios** that describe behavior in plain English, using Background, Scenario Outline and Data Tables.
- **Page Object Model**: locators and page actions live in page classes, so step definitions stay short.
- **Explicit waits only**: no `Thread.sleep`, which keeps tests fast and stable.
- **Configurable from the command line**: browser, headless mode and URL can be changed with `-D` options without touching the code.
- **Cross-browser**: Chrome, Firefox and Edge.
- **Screenshots** attached to the report for every scenario.
- **Re-run failed tests**: failed scenarios are saved to `target/failed.txt` and can be run again with `FailedRunner`.
- **CI pipeline**: every push runs the regression suite in headless Chrome and uploads the reports as a build artifact.

## How to run

**Prerequisites:** Java 17+, Maven 3.8+, Chrome (or Firefox / Edge)

```bash
git clone https://github.com/albanlutajqa/demo-cucumber-automation-framework.git
cd demo-cucumber-automation-framework

# Run the full regression suite
mvn verify

# Run headless
mvn verify -Dheadless=true

# Run only smoke tests
mvn verify -Dcucumber.filter.tags="@smoke"

# Run in Firefox
mvn verify -Dbrowser=firefox
```

From an IDE (Eclipse / IntelliJ) you can also run `RegressionRunner` or `TestRunner` as a JUnit test.

## Reports

After a run, open:

- `target/cucumber-default-report.html` for the Cucumber report with screenshots
- `target/cucumber-html-reports/overview-features.html` for the detailed masterthought report

On GitHub, open the **Actions** tab, select a run, and download the `cucumber-reports` artifact.

## Related project

[SIS Cucumber Automation Framework](https://github.com/albanlutajqa/sis-cucumber-automation-framework): the same framework design applied to a Student Information System, including database validation with JDBC.

## Author

**Alban Lutaj**, QA Automation Engineer
Portfolio: [albanlutajqa.github.io](https://albanlutajqa.github.io)
