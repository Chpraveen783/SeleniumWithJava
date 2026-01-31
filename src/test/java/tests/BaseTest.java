package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import utils.ReportManager;
import utils.ScreenshotUtil;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest testLogger;
    private static ExtentReports extent = ReportManager.getExtentReports();

    @BeforeMethod
    public void setUp(org.testng.ITestContext context, java.lang.reflect.Method method) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // create a test entry in Extent report
        String testName = method.getName();
        testLogger = extent.createTest(testName);
        testLogger.info("Starting test: " + testName);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
                testLogger.fail(result.getThrowable());
                testLogger.addScreenCaptureFromPath(screenshotPath);
            } else if (result.getStatus() == ITestResult.SKIP) {
                testLogger.skip(result.getThrowable());
            } else {
                testLogger.pass("Test passed");
            }
        } catch (Exception e) {
            // ensure reporting doesn't break teardown
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
            extent.flush();
        }
    }
}
