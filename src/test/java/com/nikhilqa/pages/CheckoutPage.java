package com.nikhilqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.nikhilqa.util.Clicks;

public class CheckoutPage {

    private final WebDriver driver;
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput  = By.id("last-name");
    private final By zipInput       = By.id("postal-code");
    private final By continueBtn    = By.id("continue");
    private final By finishBtn      = By.id("finish");
    private final By summaryTotal   = By.cssSelector("[data-test='total-label']");
    private final By confirmHeader  = By.cssSelector(".complete-header");
    private final By errorMessage   = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage fillDetails(String firstName, String lastName, String zip) {
        Clicks.type(driver, driver.findElement(firstNameInput), firstName);
        Clicks.type(driver, driver.findElement(lastNameInput), lastName);
        Clicks.type(driver, driver.findElement(zipInput), zip);
        Clicks.click(driver, driver.findElement(continueBtn));
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getSummaryTotal() {
        return driver.findElement(summaryTotal).getText();
    }

    public CheckoutPage finishOrder() {
        Clicks.click(driver, driver.findElement(finishBtn));
        return this;
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmHeader).getText();
    }
}
