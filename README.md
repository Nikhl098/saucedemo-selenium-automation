# SauceDemo Selenium automation (Java, TestNG, Page Object Model)

UI test automation for [Swag Labs / SauceDemo](https://www.saucedemo.com/). It automates the manual test cases from my [Swag Labs test suite](https://nikhl098.github.io/swag-labs.html).

**Stack:** Java 11, Selenium 4.26, TestNG 7.10, Maven, headless Chrome.

## Latest run: 7 passed, 0 failed (26 Nov 2026)

| ID | Test | Result |
|----|------|--------|
| TC-A01 | standard_user logs in and sees 6 products | Pass |
| TC-A02 | locked_out_user gets the locked-out error | Pass |
| TC-A03 | wrong password shows the mismatch error | Pass |
| TC-A04 | adding 2 products shows cart badge 2 | Pass |
| TC-A05 | price sort low to high is ascending | Pass |
| TC-A06 | full checkout ends with "Thank you for your order!" | Pass |
| TC-A07 | checkout without first name shows validation error | Pass |

![Order confirmation captured by TC-A06](docs/order-confirmation.png)

## Structure
- `base/BaseTest`: starts a fresh headless Chrome per test, saves a screenshot on failure.
- `pages/`: Page Objects (`LoginPage`, `InventoryPage`, `CartPage`, `CheckoutPage`).
- `tests/`: TestNG classes (`LoginTests`, `InventoryTests`, `CheckoutTests`).
- `util/Clicks`: DOM-level click and input helpers (see notes).

## Run
```
mvn test
```
Needs Chrome and a matching ChromeDriver (Selenium Manager downloads one automatically if it is not on PATH).

## Notes from building it
- **Limitation, please read:** the page objects click and type through JavaScript (`Clicks` helper: `element.click()` and a native value setter plus an `input` event), not through standard WebDriver `click()` / `sendKeys()`. I first tried the standard calls with explicit waits. On 26 Nov 2026 that failed 2 of 7 in one run and 3 of 7 in another (cart and add-to-cart clicks did not register in headless Chrome; see `docs/standard-click-experiment.txt`). I did not find a root cause, so the helper is a workaround. It means these 7 tests check page behaviour and business flow, but they do not prove that a real user's mouse and keyboard input works. A real-browser run (non-headless) was not tried.
- Tests use `data-test` attributes where the site provides them.

## Test data
Only the public demo credentials published by the demo sites themselves are used (SauceDemo's login page lists its test users; Restful Booker's API docs list the admin demo login). No private credentials are in this repo.

## Test evidence
`docs/surefire-TestSuite.txt` and `docs/maven-run-final.log` are the actual Maven/Surefire output of the final run (7 run, 0 failures).

## Credits
Built with AI assistance (Instinct); tests were run and debugged against the live site, and the results above come from that run.
