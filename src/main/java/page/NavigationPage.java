package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtil;

public class NavigationPage {

    private WebDriver driver;

    // Locators for navigation links
    private By aboutUsLink = By.linkText("About Us");
    private By servicesLink = By.linkText("Services");
    private By homeLink = By.linkText("Home"); // Home navigation link
    private By logoLink = By.cssSelector("img[title='ParaBank']"); // Logo element

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to About Us page
    public void goAboutUs() {
        WaitUtil.waitForElementClickable(driver, aboutUsLink, 10).click();
    }

    // Navigate to Services page
    public void goServices() {
        WaitUtil.waitForElementClickable(driver, servicesLink, 10).click();
    }

    // Navigate to Home page
    public void goHome() {
        WaitUtil.waitForElementClickable(driver, homeLink, 10).click();
    }

    // Click the site logo (redirects to Home page)
    public void clickLogo() {
        WaitUtil.waitForElementClickable(driver, logoLink, 10).click();
    }
}