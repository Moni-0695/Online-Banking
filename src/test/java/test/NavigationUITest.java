package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.NavigationPage;

public class NavigationUITest extends BaseTest {

    @Test(priority = 12)
    public void navigationAndLogo() {
        test = extent.createTest("Navigation & UI Testing");
        NavigationPage nav = new NavigationPage(driver);

        // Home
        nav.goHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.htm"));
        test.info("Home OK");

        // About
        nav.goAboutUs();
        Assert.assertTrue(driver.getCurrentUrl().contains("about.htm"));
        test.info("About OK");

        // Services
        nav.goServices();
        Assert.assertTrue(driver.getCurrentUrl().contains("services.htm"));
        test.info("Services OK");

        // Logo click to home
        nav.clickLogo();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.htm"));
        test.pass("Navigation and logo validated");
    }
}