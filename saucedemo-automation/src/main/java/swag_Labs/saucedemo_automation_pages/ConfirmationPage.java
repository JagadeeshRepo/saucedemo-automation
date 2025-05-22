package swag_Labs.saucedemo_automation_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isConfirmationDisplayed() {
        return driver.findElement(By.className("complete-header")).isDisplayed();
    }
}