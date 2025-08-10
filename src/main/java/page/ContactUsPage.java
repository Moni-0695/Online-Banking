package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private WebDriver driver;

    private By name = By.id("name");
    private By email = By.id("email");
    private By phone = By.id("phone");
    private By message = By.id("message");
    private By sendBtn = By.xpath("//input[@type='submit' and @value='Send to Customer Care']");
    private By rightPanel = By.cssSelector("#rightPanel p");

    public ContactUsPage(WebDriver driver) { this.driver = driver; }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/contact.htm");
    }

    public void fillForm(String nm, String em, String ph, String msg) {
        driver.findElement(name).clear(); driver.findElement(name).sendKeys(nm);
        driver.findElement(email).clear(); driver.findElement(email).sendKeys(em);
        driver.findElement(phone).clear(); driver.findElement(phone).sendKeys(ph);
        driver.findElement(message).clear(); driver.findElement(message).sendKeys(msg);
    }

    public void submit() { driver.findElement(sendBtn).click(); }

    public String getRightPanelText() {
        try { return driver.findElement(rightPanel).getText(); } catch (Exception e) { return ""; }
    }
}