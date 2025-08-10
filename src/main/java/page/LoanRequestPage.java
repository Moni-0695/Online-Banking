package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoanRequestPage {
    private WebDriver driver;

    private By amount = By.id("amount");
    private By downPayment = By.id("downPayment");
    private By fromAccount = By.id("fromAccountId");
    private By applyBtn = By.xpath("//input[@type='submit' and @value='Apply Now']");
    private By resultParagraph = By.cssSelector("#rightPanel p");

    public LoanRequestPage(WebDriver driver) { this.driver = driver; }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/requestloan.htm");
    }

    public void setAmount(String amt) {
        driver.findElement(amount).clear();
        driver.findElement(amount).sendKeys(amt);
    }

    public void setDownPayment(String dp) {
        driver.findElement(downPayment).clear();
        driver.findElement(downPayment).sendKeys(dp);
    }

    public void selectFromAccount(String visibleText) {
        new Select(driver.findElement(fromAccount)).selectByVisibleText(visibleText);
    }

    public void clickApply() {
        driver.findElement(applyBtn).click();
    }

    public String getResult() {
        try { return driver.findElement(resultParagraph).getText(); }
        catch (Exception e) { return ""; }
    }
}