# Selenium + Cucumber + TestNG + Rest Assured Automation Framework

An end-to-end UI + API test automation framework built entirely with your
current stack: **Selenium WebDriver, Java, TestNG, Cucumber (BDD), Rest Assured,
and Maven**, wired into a **GitHub Actions CI pipeline**.

Tested against [saucedemo.com](https://www.saucedemo.com) (UI) and
[reqres.in](https://reqres.in) (API) — both public, stable, free targets.

## Why this framework

| Decision | Reason |
|---|---|
| **Page Object Model** (`pages/`) | Selectors and interactions live in one class per page. UI changes get fixed in one place instead of every test that touches that page. |
| **Cucumber BDD** (`features/*.feature`) | Test cases are written in Gherkin (Given/When/Then), readable by non-technical stakeholders (PMs, manual QA) — a real differentiator over plain TestNG/JUnit tests. |
| **WebDriverManager** | Automatically downloads/matches the right ChromeDriver/GeckoDriver version — no manual driver binary management, a common pain point interviewers ask about. |
| **ThreadLocal in DriverFactory** | Lets tests run in parallel without different threads sharing (and corrupting) the same browser session. |
| **ConfigReader + config.properties** | URLs aren't hardcoded — same suite can point at dev/staging/prod by changing one file or passing a system property. |
| **Rest Assured API tests, separate from UI tests** | API tests don't need a browser, so they run in seconds — good as a fast smoke-test gate in CI before the slower UI suite runs. |
| **Cucumber Scenario Outline** (see `login.feature`) | Data-driven testing — one scenario validated against multiple input combinations without copy-pasting. |
| **Screenshot-on-failure in Hooks** | Automatically attaches a screenshot to the Cucumber report when a scenario fails — huge for debugging without re-running locally. |
| **TestNG suite (`testng.xml`) running UI + API as separate `<test>` blocks** | Lets you run just the UI suite or just the API suite independently — and run both in parallel. |


## Running it in Eclipse

1. **File > Import > Maven > Existing Maven Projects** → select this folder → Finish.
   Eclipse will read `pom.xml` and download all dependencies automatically.
2. Wait for the Maven build to finish (check the bottom-right progress bar).
3. To run everything: right-click `testng.xml` → **Run As > TestNG Suite**.
4. To run just one class: right-click `TestRunner.java` or `UsersApiTest.java` → **Run As > TestNG Test**.
5. Reports land in `target/cucumber-reports/cucumber.html` (open it in a browser) and `target/surefire-reports/`.

### Running from command line instead (works the same as CI)
```bash
mvn clean test
mvn clean test -Dbrowser=firefox
mvn clean test -Dheadless=true
```

## CI/CD

`.github/workflows/selenium-tests.yml` runs on every push/PR to `main`, plus a nightly
scheduled regression run. It runs the suite **headless** (no visible browser, since CI
has no display) and uploads the Cucumber HTML report and TestNG/Surefire results as
downloadable build artifacts — visible right on the GitHub Actions run page.

## What I'd extend next
- Extent Reports adapter for a richer HTML report than Cucumber's default
- Cross-browser matrix in CI (chrome + firefox as separate jobs)
- Docker container for the test runner, for environment parity
- Jira integration: auto-file a bug when a scenario fails in the nightly run
