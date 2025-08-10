package test;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.LoanRequestPage;
import utils.WaitUtil;

public class LoanRequestTest extends BaseTest {

    @Test(priority = 8)
    public void requestLoanValid() {
        test = extent.createTest("Loan Request - Valid");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();
        WaitUtil.waitForUrlContains(driver, "overview.htm", 10);

        LoanRequestPage lr = new LoanRequestPage(driver);
        lr.open();

        lr.setAmount("5000");
        lr.setDownPayment("500");

        // select first account
        String acct = driver.findElement(By.id("fromAccountId")).getText().split("\n")[0].trim();
        lr.selectFromAccount(acct);
        lr.clickApply();

        String res = lr.getResult();
        test.info("Loan response: " + res);
        Assert.assertTrue(res.length() > 0, "Expect loan response");
        test.pass("Loan request processed");
    }

    @Test(priority = 9)
    public void requestLoanMissingInfo() {
        test = extent.createTest("Loan Request - Missing Info");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();
        WaitUtil.waitForUrlContains(driver, "overview.htm", 10);

        LoanRequestPage lr = new LoanRequestPage(driver);
        lr.open();
        lr.setAmount("");
        lr.setDownPayment("");
        lr.clickApply();

        String res = lr.getResult();
        test.info("Loan missing info response: " + res);
        Assert.assertTrue(res.length() > 0 || driver.getCurrentUrl().contains("requestloan.htm"));
        test.pass("Loan missing info case executed");
    }
}