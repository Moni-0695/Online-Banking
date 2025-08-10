package base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void setUp(Method method) { // only Method here
        driver = DriverFactory.initDriver();
        driver.get(BASE_URL);
        test = extent.createTest(method.getName()); // Create report test entry
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        try {
            if (test != null) {
                if (result.getStatus() == ITestResult.FAILURE) {
                    String screenshotPath = takeScreenshot(result.getName());
                    test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
                    test.addScreenCaptureFromPath(screenshotPath);
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(Status.PASS, "Test Passed");
                } else if (result.getStatus() == ITestResult.SKIP) {
                    test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
                }
            }
        } finally {
            DriverFactory.quitDriver();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extent != null) extent.flush();
    }

    private String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "reports/screenshots/" + testName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(new File("reports/screenshots").toPath());
            Files.copy(srcFile.toPath(), new File(screenshotPath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}