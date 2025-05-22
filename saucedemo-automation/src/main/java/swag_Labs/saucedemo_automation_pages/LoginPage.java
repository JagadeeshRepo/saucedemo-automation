package swag_Labs.saucedemo_automation_pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	private Logger logger = LogManager.getLogger(LoginPage.class);

	private By usernameInput = By.id("user-name");
	private By passwordInput = By.id("password");
	private By loginButton = By.id("login-button");
	private By errorMessage = By.cssSelector("h3[data-test='error']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		logger.info("Logging in with username: " + username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
		logger.info("Clicked on login button");
	}

	public boolean isErrorDisplayed() {
		try {
			return driver.findElement(errorMessage).isDisplayed();
		} catch (Exception e) {
			logger.warn("Login error message not displayed.");
			return false;
		}
	}
}
