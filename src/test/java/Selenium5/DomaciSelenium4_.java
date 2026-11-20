package Selenium5;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DomaciSelenium4_ {
    //Domaci:
    //https://practice.expandtesting.com/login
    //Odraditi registraciju i login koristeci anotacije
    WebDriver driver;
    WebDriverWait wait;

    // JEDNOM, pre svih testova
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Umesto klika na dugme Login pritiskamo ENTER u polju za lozinku.
    // Forma se posalje isto kao klikom, a reklame preko dugmeta ne smetaju.

    // ---------------- NEVALIDNI (idu prvi) ----------------

    @Test(priority = 1)
    public void pogresanUsername() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("pogresan");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your username is invalid!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(priority = 2)
    public void pogresanPassword() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("practice");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("pogresna");
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your password is invalid!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(priority = 3)
    public void pogresniOba() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("pogresan");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("pogresna");
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your username is invalid!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(priority = 4)
    public void prazanUsername() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        password.sendKeys(Keys.ENTER);

        // login ne sme da uspe - ostajemo na /login i nema Logout dugmeta
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    @Test(priority = 5)
    public void prazanPassword() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("practice");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    @Test(priority = 6)
    public void praznaOba() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    // ---------------- VALIDAN (ide poslednji) ----------------

    @Test(priority = 7)
    public void validanLogin() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("practice");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        password.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.urlContains("/secure"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("You logged into a secure area!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
    }

    // posle SVAKOG testa - brise sesiju (izloguje nas)
    @AfterMethod
    public void obrisiKolacice() {
        driver.manage().deleteAllCookies();
    }

    // JEDNOM, na kraju
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
