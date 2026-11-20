package Selenium3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DomaciSelenium2_2 {

    static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        //nevalidni kredencijali
        String invalidUsername = "tomsmithh";
        String invalidPassword = "SuperSecretPassword!!!!";

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys(invalidUsername);
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys(invalidPassword);
        Thread.sleep(1000);
        WebElement login = driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        login.click();
        //----------------------

        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        WebElement flash = driver.findElement(By.id("flash"));
        String text = flash.getText();
        Assert.assertTrue(text.contains("Your username is invalid!"));

        Thread.sleep(3000);

        WebDriverManager.chromedriver().setup();
        WebDriver driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.navigate().to("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        //nevalidan username ili prazan, validan password
        //String invalidUsername1 = "tomsmithh";
        String invalidUsername1 = "";
        String validPassword1 = "SuperSecretPassword!";

        WebElement username1 = driver1.findElement(By.id("username"));
        username1.clear();
        username1.sendKeys(invalidUsername1);
        Thread.sleep(1000);
        WebElement password1 = driver1.findElement(By.id("password"));
        password1.clear();
        password1.sendKeys(validPassword1);
        Thread.sleep(1000);
        WebElement login1 = driver1.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        login1.click();
        //----------------------

        Thread.sleep(3000);

        Assert.assertEquals(driver1.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        WebElement flash1 = driver1.findElement(By.id("flash"));
        String text1 = flash1.getText();
        Assert.assertTrue(text1.contains("Your username is invalid!"));


        Thread.sleep(3000);

        WebDriverManager.chromedriver().setup();
        WebDriver driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.navigate().to("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        //validan username, nevalidan password ili prazan
        String validUsername2 = "tomsmith";
        //String invalidPassword2 = "SuperSecretPassword!!!!!!!";
        String invalidPassword2 = "";

        WebElement username2 = driver2.findElement(By.id("username"));
        username2.clear();
        username2.sendKeys(validUsername2);
        Thread.sleep(1000);
        WebElement password2 = driver2.findElement(By.id("password"));
        password2.clear();
        password2.sendKeys(invalidPassword2);
        Thread.sleep(1000);
        WebElement login2 = driver2.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));
        login2.click();
        //----------------------

        Thread.sleep(3000);

        Assert.assertEquals(driver2.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);

        WebElement flash2 = driver2.findElement(By.id("flash"));
        String text2 = flash2.getText();
        Assert.assertTrue(text2.contains("Your password is invalid!"));
        driver2.quit();
    }
}
