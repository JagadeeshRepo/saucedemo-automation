package swag_Labs.saucedemo_automation_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getCartItemsCount() {
		return driver.findElements(By.className("cart_item")).size();
	}

	public void clickCheckoutButton() {
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
	}
}