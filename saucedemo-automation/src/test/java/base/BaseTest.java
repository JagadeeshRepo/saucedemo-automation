package base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReportManager;

public class BaseTest {

    protected ExtentReports extent;
    protected static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        ExtentReportManager.initReport();
        extent = ExtentReportManager.getInstance();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, Method method) {
        DriverFactory.initDriver(browser);
        extent = ExtentReportManager.getInstance();
        ExtentTest test = extent.createTest(method.getDeclaringClass().getSimpleName() + " : " + method.getName());
        testThread.set(test);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        ExtentTest test = testThread.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}