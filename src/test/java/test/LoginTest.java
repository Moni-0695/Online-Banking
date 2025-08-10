package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.WaitUtil;

public class LoginTest extends BaseTest {

    @Test(priority = 2)
    public void validLogin() {
        test = extent.createTest("Login - Valid Credentials");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();

        // wait for URL containing overview
        boolean ok = WaitUtil.waitForUrlContains(driver, "overview.htm", 10);
        test.info("URL after login: " + driver.getCurrentUrl());
        Assert.assertTrue(ok, "Expected redirect to accounts overview");
        test.pass("Valid login successful");
    }

    @Test(priority = 3)
    public void invalidPassword() {
        test = extent.createTest("Login - Invalid Password");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("wrongpass");
        login.clickLogin();
        String rp = login.getRightPanelText();
        test.info("Right panel: " + rp);
        Assert.assertTrue(rp.length() > 0, "Expect server error or message for invalid password");
        test.pass("Invalid password case executed");
    }

    @Test(priority = 4)
    public void emptyFieldsLogin() {
        test = extent.createTest("Login - Empty Fields");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("");
        login.enterPassword("");
        login.clickLogin();
        String rp = login.getRightPanelText();
        test.info("Right panel: " + rp);
        Assert.assertTrue(rp.length() > 0 || driver.getCurrentUrl().contains("index.htm"));
        test.pass("Empty fields handled");
    }
}