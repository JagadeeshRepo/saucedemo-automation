package swag_Labs.saucedemo_automation_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButtons = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private By cartBadge = By.xpath("//a[@class='shopping_cart_link']");
    private By cartIcon = By.className("shopping_cart_link");

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public int getCartItemCount() {
        String countText = driver.findElement(cartBadge).getText();
        return Integer.parseInt(countText);
    }

    public void addProductToCart(String productName) {
        String formattedName = productName.toLowerCase().replaceAll(" ", "-");
        String addToCartButtonId = "add-to-cart-" + formattedName;
        driver.findElement(By.id(addToCartButtonId)).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public void dismissChromePasswordPopup() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("let btn = document.querySelector('button[aria-label=\"OK\"]'); if(btn) btn.click();");
        } catch (Exception e) {
            // Optional: log the exception or handle it
        }
    }
}
