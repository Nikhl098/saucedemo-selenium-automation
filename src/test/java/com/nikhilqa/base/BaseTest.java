package com.nikhilqa.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * Base class for all SauceDemo UI tests.
 * Starts a fresh headless Chrome for every test and saves a screenshot
 * on failure (and of the final page state on the checkout test).
 */
public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    protected static final Path SCREENSHOT_DIR = Paths.get("screenshots");

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1366,900");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                saveScreenshot(result.getMethod().getMethodName());
            }
            driver.quit();
        }
    }

    protected void saveScreenshot(String name) {
        try {
            Files.createDirectories(SCREENSHOT_DIR);
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path target = SCREENSHOT_DIR.resolve(name + ".png");
            Files.write(target, png);
            System.out.println("Screenshot saved: " + target.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
        }
    }
}
