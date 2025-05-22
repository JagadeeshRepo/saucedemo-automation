package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    // Initialize ExtentReports only once
    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReports/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            // Optional: add system info or config here
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Jagadeesh");
        }
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            initReport();
        }
        return extent;
    }
}
