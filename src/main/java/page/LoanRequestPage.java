package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoanRequestPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By amount = By.id("amount");
    private By downPayment = By.id("downPayment");
    private By fromAccount = By.id("fromAccountId");
    private By applyBtn = By.xpath("//input[@type='button' and @value='Apply Now']");
    private By resultParagraph = By.cssSelector("#rightPanel p");

    public LoanRequestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://parabank.parasoft.com/parabank/requestloan.htm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));
    }

    public void setAmount(String amt) {
        WebElement amountField = wait.until(ExpectedConditions.elementToBeClickable(amount));
        amountField.clear();
        amountField.sendKeys(amt);
    }

    public void setDownPayment(String dp) {
        WebElement downPaymentField = wait.until(ExpectedConditions.elementToBeClickable(downPayment));
        downPaymentField.clear();
        downPaymentField.sendKeys(dp);
    }

    public void selectFirstFromAccount() {
        WebElement fromDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));
        Select select = new Select(fromDropdown);
        if (select.getOptions().size() > 0) {
            select.selectByIndex(0);
        } else {
            throw new RuntimeException("No accounts available in 'fromAccountId'");
        }
    }

    public void clickApply() {
        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(applyBtn));
        applyButton.click();
    }

    public String getResult() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(resultParagraph)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}