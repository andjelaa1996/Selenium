package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Selenium6WordPress {
    static void main(String[] args) throws InterruptedException {

        //WebDriverManager.firefoxdriver().setup();
        //WebDriver driver = new FirefoxDriver();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("https://wordpress.com/");

        String validUsername = "andjelapavlovic996@gmail.com";
        String validPassword = "ITBootcamp123@";
        Thread.sleep(1000);
        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();
        Thread.sleep(10000);
        WebElement email = driver.findElement(By.id("usernameOrEmail"));
        //wait.until(ExpectedConditions.visibilityOf(email));
        email.click();
        email.sendKeys(validUsername);
        Thread.sleep(10000);
        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
        WebElement password = driver.findElement(By.id("password"));
        wait.until(ExpectedConditions.visibilityOf(password));
        Thread.sleep(1000);
        password.clear();
        password.sendKeys(validPassword);
        continueButton.click();
        wait.until(ExpectedConditions.urlContains("sites"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://"+validUsername+".wordpress.com/sites/"); //ne radi
        WebElement profile = driver.findElement(By.linkText(validUsername));
        Assert.assertTrue(profile.isDisplayed());
        driver.quit();






    }
}
