package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // changed to XPath-only locators
    private By cartItems = By.xpath("//div[contains(@class,'cartSection')]//h3");
    private By checkoutButton = By.xpath("//div[contains(@class,'subtotal')]/ul/li/button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public List<String> getCartProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems));
        List<WebElement> items = driver.findElements(cartItems);
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
