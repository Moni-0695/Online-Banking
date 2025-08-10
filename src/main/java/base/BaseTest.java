package base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected final String BASE_URL = "https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC";

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        extent = ExtentReportManager.createInstance("reports/ParaBank_Spark_Report.html");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extent != null) extent.flush();
    }
}