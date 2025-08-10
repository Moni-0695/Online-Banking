package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {
    private WebDriver driver;

    private By amountField = By.id("amount");
    private By fromAccount = By.id("fromAccountId");
    private By toAccount = By.id("toAccountId");
    private By transferBtn = By.xpath("//input[@type='submit' and @value='Transfer']");
    private By confirmationText = By.cssSelector("#rightPanel p");

    public TransferFundsPage(WebDriver driver) { this.driver = driver; }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/transfer.htm");
    }

    public void setAmount(String amt) {
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amt);
    }

    public void selectFrom(String visibleText) {
        new Select(driver.findElement(fromAccount)).selectByVisibleText(visibleText);
    }

    public void selectTo(String visibleText) {
        new Select(driver.findElement(toAccount)).selectByVisibleText(visibleText);
    }

    public void clickTransfer() {
        driver.findElement(transferBtn).click();
    }

    public String getConfirmation() {
        try { return driver.findElement(confirmationText).getText(); }
        catch (Exception e) { return ""; }
    }
}
