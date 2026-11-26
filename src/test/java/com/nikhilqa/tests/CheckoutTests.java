package com.nikhilqa.tests;

import com.nikhilqa.base.BaseTest;
import com.nikhilqa.pages.CartPage;
import com.nikhilqa.pages.CheckoutPage;
import com.nikhilqa.pages.InventoryPage;
import com.nikhilqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test(description = "TC-A06: full purchase flow ends with the order confirmation message")
    public void completeCheckout_orderIsConfirmed() {
        InventoryPage inventory = new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        inventory.addToCart("sauce-labs-backpack")
                 .addToCart("sauce-labs-bolt-t-shirt");

        CartPage cart = inventory.openCart();
        Assert.assertTrue(cart.isLoaded(), "Cart page not shown");
        Assert.assertEquals(cart.getItemNames().size(), 2, "Expected 2 items in the cart");

        CheckoutPage checkout = cart.proceedToCheckout()
                .fillDetails("Nikhil", "Singh", "110075");
        Assert.assertTrue(checkout.getSummaryTotal().contains("Total:"),
                "Order total not shown on the overview page");

        checkout.finishOrder();
        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!",
                "Order confirmation message mismatch");
        saveScreenshot("TC-A06-order-confirmation");
    }

    @Test(description = "TC-A07: checkout without a first name shows a validation error")
    public void checkoutWithoutFirstName_showsValidationError() {
        InventoryPage inventory = new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        inventory.addToCart("sauce-labs-backpack");

        CheckoutPage checkout = inventory.openCart().proceedToCheckout()
                .fillDetails("", "Singh", "110075");
        Assert.assertTrue(checkout.getErrorMessage().contains("First Name is required"),
                "Unexpected validation text: " + checkout.getErrorMessage());
    }
}
