package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("input[value='Log In']");
    private By rightPanelParagraph = By.cssSelector("#rightPanel p");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getRightPanelText() {
        try { return driver.findElement(rightPanelParagraph).getText(); }
        catch (Exception e) { return ""; }
    }
}
