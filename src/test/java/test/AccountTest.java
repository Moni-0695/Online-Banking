package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AccountOverviewPage;
import page.LoginPage;
import utils.WaitUtil;

public class AccountTest extends BaseTest {

    @Test(priority = 5)
    public void accountsOverviewAndTable() {
        test = extent.createTest("Account - Overview & Table");
        // login
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();

        // wait for overview
        boolean arrived = WaitUtil.waitForUrlContains(driver, "overview.htm", 10);
        Assert.assertTrue(arrived, "Should reach overview");
        AccountOverviewPage ao = new AccountOverviewPage(driver);
        Assert.assertTrue(ao.isAtOverview(), "Accounts Overview title should be present");
        Assert.assertTrue(ao.isAccountTableDisplayed(), "Account table should display");
        test.pass("Account overview & table verified");
    }
}