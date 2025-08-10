package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    private By firstName = By.name("customer.firstName");
    private By lastName = By.name("customer.lastName");
    private By address = By.name("customer.address.street");
    private By city = By.name("customer.address.city");
    private By state = By.name("customer.address.state");
    private By zip = By.name("customer.address.zipCode");
    private By phone = By.name("customer.phoneNumber");
    private By ssn = By.name("customer.ssn");
    private By username = By.name("customer.username");
    private By password = By.name("customer.password");
    private By confirmPassword = By.name("repeatedPassword");
    private By registerBtn = By.xpath("//input[@type='submit' and @value='Register']");
    private By successHeader = By.xpath("//h1[contains(text(),'Welcome') or contains(text(),'Customer')]");

    public RegisterPage(WebDriver driver) { this.driver = driver; }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillRegistrationForm(String fName, String lName, String addr, String cityVal, String stateVal,
                                     String zipVal, String phoneVal, String ssnVal, String user, String pwd) {
        driver.findElement(firstName).clear(); driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).clear(); driver.findElement(lastName).sendKeys(lName);
        driver.findElement(address).clear(); driver.findElement(address).sendKeys(addr);
        driver.findElement(city).clear(); driver.findElement(city).sendKeys(cityVal);
        driver.findElement(state).clear(); driver.findElement(state).sendKeys(stateVal);
        driver.findElement(zip).clear(); driver.findElement(zip).sendKeys(zipVal);
        driver.findElement(phone).clear(); driver.findElement(phone).sendKeys(phoneVal);
        driver.findElement(ssn).clear(); driver.findElement(ssn).sendKeys(ssnVal);
        driver.findElement(username).clear(); driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear(); driver.findElement(password).sendKeys(pwd);
        driver.findElement(confirmPassword).clear(); driver.findElement(confirmPassword).sendKeys(pwd);
    }

    public void submit() {
        driver.findElement(registerBtn).click();
    }

    public boolean isRegistrationSuccessful() {
        try { return driver.findElement(successHeader).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public String getRightPanelText() {
        try { return driver.findElement(By.cssSelector("#rightPanel p")).getText(); }
        catch (Exception e) { return ""; }
    }
}