package utils;

import base.BaseTest;
import base.DriverFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // ExtentTest is created in BaseTest already
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        ExtentTest test = BaseTest.getTest();

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        test.pass("Test Passed")
            .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        ExtentTest test = BaseTest.getTest();

        String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        try {
            test.fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            test.fail("Exception while attaching screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest.getTest().skip("Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Report is initialized in BaseTest.beforeSuite()
    }

    @Override
    public void onFinish(ITestContext context) {
        // Report is flushed in BaseTest.afterSuite()
    }
}
