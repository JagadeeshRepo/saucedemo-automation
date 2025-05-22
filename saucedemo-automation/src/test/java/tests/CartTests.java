package tests;

import base.BaseTest;
import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import swag_Labs.saucedemo_automation_pages.LoginPage;
import swag_Labs.saucedemo_automation_pages.ProductsPage;

public class CartTests extends BaseTest {

    @Test
    public void addProductAndVerifyCartCount() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        productsPage.addFirstProductToCart();

        int itemCount = productsPage.getCartItemCount();
        Assert.assertEquals(itemCount, 1, "Cart item count is not correct after adding product.");
    }
}
