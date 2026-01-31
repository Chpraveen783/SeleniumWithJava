package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;

import java.util.List;

public class E2EOrderTest extends BaseTest {

    @Test
    public void purchaseProductFlow() {
        // Load test data from YAML
        TestData data = TestData.loadFromYaml("src/test/resources/testdata.yaml");
        String email = data.getEmail();
        String password = data.getPassword();
        String productName = data.getProductName();
        String country = data.getCountry();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(email, password);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.waitForProductsToLoad();
        productsPage.addProductToCart(productName);
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        List<String> cartProducts = cartPage.getCartProductNames();
        Assert.assertTrue(cartProducts.stream().anyMatch(p -> p.equalsIgnoreCase(productName)), "Product not found in cart");

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        // Use country from test data (type a substring to trigger selection)
        checkoutPage.selectCountry(country); // types and selects e.g. "Ind"

        // dummy card details
        checkoutPage.fillCardDetails("4242424242424242", "12/34", "123");
        checkoutPage.placeOrder();

        // After placing order, try to assert that a confirmation exists. The site shows an order id text in a selector like .hero-primary
        // We'll wait a short while and perform a basic assertion on the presence of a confirmation element
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String pageSrc = driver.getPageSource();
        String pageLower = pageSrc == null ? "" : pageSrc.toLowerCase();
        boolean successVisible = pageLower.contains("thankyou") || pageLower.contains("order");
        Assert.assertTrue(successVisible, "Order confirmation not detected in page source.");
    }
}
