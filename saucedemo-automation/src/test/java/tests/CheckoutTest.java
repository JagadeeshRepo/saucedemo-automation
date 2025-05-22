package tests;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;
import base.DriverFactory;
import swag_Labs.saucedemo_automation_pages.CartPage;
import swag_Labs.saucedemo_automation_pages.CheckoutPage;
import swag_Labs.saucedemo_automation_pages.ConfirmationPage;
import swag_Labs.saucedemo_automation_pages.LoginPage;
import swag_Labs.saucedemo_automation_pages.ProductsPage;

public class CheckoutTest extends BaseTest {

	@Test
	public void completeCheckoutTest() {

		// Login
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("standard_user", "secret_sauce");

		// Handle popup if present
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
			WebElement popupCloseBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".popup-close-btn")));
			popupCloseBtn.click();
		} catch (TimeoutException e) {
			// Popup didn't appear, continue
		}

		// Add product to cart
		ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
		productsPage.dismissChromePasswordPopup(); // Add this line
		productsPage.addProductToCart("Sauce Labs Backpack");
		productsPage.clickCartIcon();

		// Validate cart count
		CartPage cartPage = new CartPage(getDriver());
		//Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart item count mismatch");
		cartPage.clickCheckoutButton();

		// Enter mock customer data
		CheckoutPage checkoutPage = new CheckoutPage(getDriver());
		checkoutPage.enterCustomerInformation("Jack", "Jagadeesh", "963290");
		checkoutPage.clickContinue();
		checkoutPage.clickFinish();

		// Confirm order completion
		ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
		Assert.assertTrue(confirmationPage.isConfirmationDisplayed(), "Order confirmation not displayed");
	}

}
