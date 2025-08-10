package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountOverviewPage {
    private WebDriver driver;

    private By accountsOverviewTitle = By.xpath("//h1[contains(text(),'Accounts Overview')]");
    private By accountTable = By.id("accountTable");

    public AccountOverviewPage(WebDriver driver) { this.driver = driver; }

    public boolean isAtOverview() {
        try { return driver.findElement(accountsOverviewTitle).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isAccountTableDisplayed() {
        try { return driver.findElement(accountTable).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}