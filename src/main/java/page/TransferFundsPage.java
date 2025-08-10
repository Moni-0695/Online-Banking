package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {
    private WebDriver driver;

    private By amountField = By.id("amount");
    private By fromAccount = By.id("fromAccountId");  // Note: id is "fromAccountId" (case-sensitive)
    private By toAccount = By.id("toAccountId");      // Note: id is "toAccountId"
    private By transferBtn = By.xpath("//input[@type='submit' and @value='Transfer']");
    private By confirmationText = By.cssSelector("#rightPanel p");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/transfer.htm");
    }

    public void setAmount(String amt) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement amountElem = wait.until(ExpectedConditions.elementToBeClickable(amountField));
        amountElem.clear();
        amountElem.sendKeys(amt);
    }

    public void selectFromByValue(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fromDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));
        wait.until(d -> new Select(fromDropdown).getOptions().size() > 0);
        new Select(fromDropdown).selectByValue(value);
    }

    public void selectToByValue(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(toAccount));
        wait.until(d -> new Select(toDropdown).getOptions().size() > 0);
        new Select(toDropdown).selectByValue(value);
    }

    public void clickTransfer() {
        driver.findElement(transferBtn).click();
    }

    public String getConfirmation() {
        try {
            return driver.findElement(confirmationText).getText();
        } catch (Exception e) {
            return "";
        }
    }
}