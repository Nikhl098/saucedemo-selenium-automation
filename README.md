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
- SauceDemo's current React build ignored normal WebDriver clicks in headless Chrome (the click landed but the handler did not run), and the checkout form kept only the first typed field. `Clicks` clicks and sets values through the DOM API and fires the `input` event. Both are documented in the helper.
- Tests use `data-test` attributes where the site provides them.

## Credits
Built with AI assistance (Instinct); tests were run and debugged against the live site, and the results above come from that run.
