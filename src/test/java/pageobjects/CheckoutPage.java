package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // changed to XPath-only locators
    private By placeOrderButton = By.xpath("//a[contains(@class,'action__submit') or contains(normalize-space(.),'Place Order')]");
    private By cardIframe = By.xpath("//iframe[starts-with(@name,'__privateStripeFrame')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectCountry(String country) {
         By countryInput = By.xpath("//input[@placeholder='Select Country']");
         By countryResults = By.xpath("//section[contains(@class,'ta-results')]//button/span[text()=' "+country+"']");

        wait.until(ExpectedConditions.elementToBeClickable(countryInput)).sendKeys(country);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countryResults));
        WebElement first = driver.findElements(countryResults).get(0);
        first.click();
    }

    public void fillCardDetails(String cardNumber, String expiry, String cvv) {
        // many apps place card fields inside an iframe (Stripe). Attempt to switch if present.
        if (!driver.findElements(cardIframe).isEmpty()) {
            WebElement iframe = driver.findElement(cardIframe);
            driver.switchTo().frame(iframe);
            // basic attempt; selectors may need adjustment depending on iframe structure
            try {
                WebElement cardNumberInput = driver.findElement(By.xpath("//input[@name='cardnumber']"));
                cardNumberInput.sendKeys(cardNumber);
                driver.findElement(By.xpath("//input[@name='exp-date']")).sendKeys(expiry);
                driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys(cvv);
            } catch (Exception e) {
                // fallback: try common placeholders
                try {
                    driver.findElement(By.xpath("//input[@placeholder='Card number']")).sendKeys(cardNumber);
                } catch (Exception ignored) {}
            }
            driver.switchTo().defaultContent();
        } else {
            // fallback: try to find card fields in main DOM
            try {
                driver.findElement(By.xpath("//input[@placeholder='Card Number']")).sendKeys(cardNumber);
                driver.findElement(By.xpath("//input[@placeholder='MM / YY']")).sendKeys(expiry);
                driver.findElement(By.xpath("//input[@placeholder='CVV']")).sendKeys(cvv);
            } catch (Exception e) {
                // ignore - test may still try to place order
            }
        }
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
}
