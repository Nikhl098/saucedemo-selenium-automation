package com.nikhilqa.tests;

import com.nikhilqa.base.BaseTest;
import com.nikhilqa.pages.InventoryPage;
import com.nikhilqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "TC-A01: standard_user can log in and lands on the Products page")
    public void loginWithValidUser_landsOnProductsPage() {
        InventoryPage inventory = new LoginPage(driver).loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(inventory.isLoaded(), "Products page title not shown after login");
        Assert.assertEquals(inventory.getProductCount(), 6, "Expected 6 products on the inventory page");
    }

    @Test(description = "TC-A02: locked_out_user is blocked with the correct error message")
    public void loginWithLockedOutUser_showsError() {
        LoginPage login = new LoginPage(driver);
        login.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(login.isErrorDisplayed(), "Error banner not shown for locked_out_user");
        Assert.assertTrue(login.getErrorMessage().contains("Sorry, this user has been locked out"),
                "Unexpected error text: " + login.getErrorMessage());
    }

    @Test(description = "TC-A03: wrong password shows a credentials mismatch error")
    public void loginWithWrongPassword_showsError() {
        LoginPage login = new LoginPage(driver);
        login.loginAs("standard_user", "wrong_password");
        Assert.assertTrue(login.isErrorDisplayed(), "Error banner not shown for wrong password");
        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"),
                "Unexpected error text: " + login.getErrorMessage());
    }
}
