package com.nikhilqa.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.nikhilqa.util.Clicks;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final By title        = By.cssSelector(".title");
    private final By itemNames    = By.cssSelector(".inventory_item_name");
    private final By checkoutBtn  = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElementLocated(title, "Your Cart"));
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(itemNames)) {
            names.add(el.getText());
        }
        return names;
    }

    public CheckoutPage proceedToCheckout() {
        Clicks.click(driver, driver.findElement(checkoutBtn));
        return new CheckoutPage(driver);
    }
}
