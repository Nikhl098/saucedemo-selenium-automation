package com.nikhilqa.tests;

import com.nikhilqa.base.BaseTest;
import com.nikhilqa.pages.InventoryPage;
import com.nikhilqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTests extends BaseTest {

    private InventoryPage login() {
        return new LoginPage(driver).loginAs("standard_user", "secret_sauce");
    }

    @Test(description = "TC-A04: adding two products shows badge count 2 on the cart icon")
    public void addTwoProducts_cartBadgeShowsTwo() {
        InventoryPage inventory = login();
        inventory.addToCart("sauce-labs-backpack")
                 .addToCart("sauce-labs-bike-light");
        Assert.assertEquals(inventory.getCartBadgeCount(), 2, "Cart badge should show 2");
    }

    @Test(description = "TC-A05: sorting by price low to high lists prices in ascending order")
    public void sortByPriceLowToHigh_pricesAreAscending() {
        InventoryPage inventory = login().sortBy("lohi");
        List<Double> prices = inventory.getVisiblePrices();
        Assert.assertFalse(prices.isEmpty(), "No prices found after sorting");
        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i - 1),
                    "Prices not ascending at index " + i + ": " + prices);
        }
    }
}
