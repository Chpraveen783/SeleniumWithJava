package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class StandaloneTest {
    public static void main(String[] args){
        String productName="IPHONE 13 PRO";
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("praveen@mailinator.com");
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Praveen@123");
        driver.findElement(By.xpath("//input[@id='login']")).click();
        driver.findElement(By.xpath("//b[text()='"+productName+"']/parent::h5/following-sibling::button[2]")).click();


    }
}
