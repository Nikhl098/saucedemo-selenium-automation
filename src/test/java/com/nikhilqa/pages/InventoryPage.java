package com.nikhilqa.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.nikhilqa.util.Clicks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final By title          = By.cssSelector(".title");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By cartBadge      = By.cssSelector(".shopping_cart_badge");
    private final By cartLink       = By.cssSelector(".shopping_cart_link");
    private final By sortDropdown   = By.cssSelector("[data-test='product-sort-container']");
    private final By itemPrices     = By.cssSelector(".inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElementLocated(title, "Products"));
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    /** Adds a product to the cart by its data-test suffix, e.g. "sauce-labs-backpack". */
    public InventoryPage addToCart(String productId) {
        Clicks.click(driver, driver.findElement(By.cssSelector("[data-test='add-to-cart-" + productId + "']")));
        return this;
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public CartPage openCart() {
        Clicks.click(driver, driver.findElement(cartLink));
        return new CartPage(driver);
    }

    public InventoryPage sortBy(String optionValue) {
        new Select(driver.findElement(sortDropdown)).selectByValue(optionValue);
        return this;
    }

    public List<Double> getVisiblePrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : driver.findElements(itemPrices)) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }
        return prices;
    }
}
