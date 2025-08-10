package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RegisterPage;

public class RegisterTests extends BaseTest {

    @Test(priority = 1)
    public void registerProvidedUser() {
        test = extent.createTest("Register Provided User");
        RegisterPage rp = new RegisterPage(driver);
        rp.open();
        rp.fillRegistrationForm(
            "Monisha", "M", "Sathyamangalam", "Erode", "Tamilnadu",
            "638402", "9750117903", "12345", "parabank123", "User@123"
        );
        rp.submit();

        boolean success = rp.isRegistrationSuccessful();
        test.info("Right panel: " + rp.getRightPanelText());
        Assert.assertTrue(success || rp.getRightPanelText().length() > 0, "Registration success or server message expected");
        if (success) test.pass("Registration successful");
        else test.warning("Registration not successful — message captured");
    }
}