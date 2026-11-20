package Selenium3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DomaciSelenium2_1 {
    static void main(String[] args) throws InterruptedException {

        //Domaci:
        //Na sajtu https://the-internet.herokuapp.com/login testirati logovanje koristeci validne i nevalidne kredencijale.
        //Koristiti asertacije.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        //Validni kredencijali
        String validUsername = "tomsmith";
        String validPassword = "SuperSecretPassword!";

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys(validUsername);
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys(validPassword);
        Thread.sleep(1000);
        WebElement login = driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        login.click();
        //----Asertacije----

        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        Thread.sleep(1000);

        WebElement flash = driver.findElement(By.cssSelector(".flash.success"));
        String text = flash.getText();
        Assert.assertTrue(text.contains("You logged into a secure area!"));

        WebElement pageHeading = driver.findElement(By.className("subheader"));
        String actualHeading = pageHeading.getText();
        String expectedHeading = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(actualHeading, expectedHeading);
        driver.quit();









    }
}
