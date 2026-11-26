package com.nikhilqa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * SauceDemo's current React build does not process CDP-synthesised clicks
 * in headless Chrome (navigator.webdriver = true): the click lands on the
 * right element but the onClick handler never fires. Clicking through the
 * DOM API works. Standard click()/sendKeys() with explicit waits failed 2-3 of 7 tests
 * (docs/standard-click-experiment.txt). This is a workaround, not a user-equivalent interaction. This helper centralises that workaround so every page
 * object clicks the same, documented way.
 */
public final class Clicks {

    private Clicks() {}

    public static void click(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Sets a React-controlled input and fires the input event. On the checkout form
     * the site keeps only the first field typed via sendKeys, so values are set natively.
     */
    public static void type(WebDriver driver, WebElement element, String text) {
        ((JavascriptExecutor) driver).executeScript(
            "const e=arguments[0];Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value')"
            + ".set.call(e,arguments[1]);e.dispatchEvent(new Event('input',{bubbles:true}));", element, text);
    }
}
