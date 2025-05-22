package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import swag_Labs.saucedemo_automation_pages.LoginPage;
import utils.ExcelUtil;
import utils.RetryAnalyzer;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = ExcelUtil.class, retryAnalyzer = RetryAnalyzer.class)
    public void validLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.login(username, password);
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
}
