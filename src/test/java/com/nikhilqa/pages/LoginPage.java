package com.nikhilqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.nikhilqa.util.Clicks;

public class LoginPage {

    private final WebDriver driver;
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton  = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public InventoryPage loginAs(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        Clicks.click(driver, driver.findElement(loginButton));
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(errorMessage).isEmpty();
    }
}
