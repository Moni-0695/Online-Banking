package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageUtil {

    /**
     * Take screenshot and return path to file
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dest = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + ts + ".png";
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File target = new File(dest);
            FileUtils.copyFile(src, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}