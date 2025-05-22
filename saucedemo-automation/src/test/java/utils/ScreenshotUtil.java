package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String testName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String destPath = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + "_" + timestamp + ".png";
	    System.out.println("User dir: " + System.getProperty("user.dir"));

	    File dest = new File(destPath);
	    try {
	        Files.createDirectories(dest.getParentFile().toPath());
	        Files.copy(src.toPath(), dest.toPath());
	        System.out.println("Screenshot saved at: " + destPath);  // <-- print for debug
	    } catch (IOException e) {
	        System.out.println("Error saving screenshot:");
	        e.printStackTrace();
	    }
	    return destPath;
	}

}
