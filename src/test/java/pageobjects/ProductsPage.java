package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // changed to XPath-only locators
    private By productCards = By.xpath("//div[contains(@class,'card-body')]");
    private By toastMessage = By.xpath("//div[@id='toast-container']");
    private By cartButton = By.xpath("//*[@routerlink and contains(@routerlink,'cart')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForProductsToLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
    }

    public void addProductToCart(String productName) {
        waitForProductsToLoad();
        List<WebElement> products = driver.findElements(productCards);
        for (WebElement prod : products) {
            String name = prod.findElement(By.xpath(".//b")).getText();
            if (name.equalsIgnoreCase(productName)) {
                // locate the add-to-cart button relative to the product card using XPath
                WebElement addBtn = prod.findElement(By.xpath("//b[text()='"+productName+"']/parent::h5/parent::div/button[text()=' Add To Cart']"));
                wait.until(ExpectedConditions.elementToBeClickable(addBtn));
                try {
                    addBtn.click();
                } catch (org.openqa.selenium.ElementClickInterceptedException ex) {
                    // fallback: use JavaScript click if normal click is intercepted
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
                }
                wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'ng-animating')]")));
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void goToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartButton));
        driver.findElement(cartButton).click();
    }
}
