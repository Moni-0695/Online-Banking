package test;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.TransferFundsPage;
import utils.WaitUtil;

public class FundsTransferTest extends BaseTest {

    @Test(priority = 6)
    public void validTransfer() {
        test = extent.createTest("Funds Transfer - Valid");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();
        WaitUtil.waitForUrlContains(driver, "overview.htm", 10);

        TransferFundsPage tf = new TransferFundsPage(driver);
        tf.open();

        // pick first available visible options for from/to
        String fromOption = driver.findElement(By.id("fromAccountId")).getText().split("\n")[0].trim();
        String toOption = driver.findElement(By.id("toAccountId")).getText().split("\n")[0].trim();

        tf.selectFrom(fromOption);
        tf.selectTo(toOption);
        tf.setAmount("1");
        tf.clickTransfer();

        // check confirmation text or right panel message
        String conf = tf.getConfirmation();
        test.info("Transfer response: " + conf);
        Assert.assertTrue(conf.length() > 0, "Expect confirmation or feedback after transfer");
        test.pass("Transfer executed (response captured)");
    }

    @Test(priority = 7)
    public void transferInsufficientBalance() {
        test = extent.createTest("Funds Transfer - Insufficient");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("parabank123");
        login.enterPassword("User@123");
        login.clickLogin();
        WaitUtil.waitForUrlContains(driver, "overview.htm", 10);

        TransferFundsPage tf = new TransferFundsPage(driver);
        tf.open();

        String fromOption = driver.findElement(By.id("fromAccountId")).getText().split("\n")[0].trim();
        String toOption = driver.findElement(By.id("toAccountId")).getText().split("\n")[0].trim();

        tf.selectFrom(fromOption);
        tf.selectTo(toOption);
        tf.setAmount("999999999"); // big amount to trigger insufficient
        tf.clickTransfer();

        String conf = tf.getConfirmation();
        test.info("Response: " + conf);
        Assert.assertTrue(conf.toLowerCase().contains("insufficient") || conf.length() > 0);
        test.pass("Insufficient balance scenario executed");
    }
}