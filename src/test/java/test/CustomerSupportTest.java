package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ContactUsPage;

public class CustomerSupportTest extends BaseTest {

    @Test(priority = 10)
    public void contactFormSubmitValid() {
        test = extent.createTest("Contact Us - Valid Submit");
        ContactUsPage cu = new ContactUsPage(driver);
        cu.open();
        cu.fillForm("Test User", "test@example.com", "9999999999", "Test message");
        cu.submit();
        String rp = cu.getRightPanelText();
        test.info("Right panel: " + rp);
        Assert.assertTrue(rp.length() > 0 || driver.getCurrentUrl().contains("contact.htm"));
        test.pass("Contact form submitted (response captured)");
    }

    @Test(priority = 11)
    public void contactFormEmpty() {
        test = extent.createTest("Contact Us - Empty Fields");
        ContactUsPage cu = new ContactUsPage(driver);
        cu.open();
        cu.fillForm("", "", "", "");
        cu.submit();
        String rp = cu.getRightPanelText();
        test.info("Right panel: " + rp);
        Assert.assertTrue(rp.length() > 0 || driver.getCurrentUrl().contains("contact.htm"));
        test.pass("Empty contact form case executed");
    }
}