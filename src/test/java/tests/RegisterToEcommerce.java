package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterToEcommerce {
    public static void main(String[] args){
        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.xpath("//a[text()='Register here']")).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("praveen");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("chee");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("praveen@mailinator.com");
        driver.findElement(By.xpath("//input[@id='userMobile']")).sendKeys("1234567890");

        Select occupation= new Select(driver.findElement(By.xpath("//select[@formcontrolname='occupation']")));
        occupation.selectByVisibleText("Engineer");

        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Praveen@123");
        driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Praveen@123");
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//*[@id='login']")).click();


    }
}
