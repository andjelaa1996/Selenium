package Selenium5;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
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
public class DomaciSelenium4 {
    //Domaci:
    //https://practice.expandtesting.com/login
    //Odraditi registraciju i login koristeci anotacije

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    String validUsername = "practice";
    String validPassword = "SuperSecretPassword!";

    String loginUrl = "https://practice.expandtesting.com/login";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    public void ukloniReklame() {
        js.executeScript("document.querySelectorAll('iframe, ins.adsbygoogle, [id^=\"google_ads\"], [id^=\"aswift\"]')" +
                        ".forEach(e => e.remove());");
    }

    public void klikni(WebElement element) {
        ukloniReklame();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void login(String user, String pass) {
        driver.navigate().to(loginUrl);

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.clear();
        usernameField.sendKeys(user);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(pass);

        klikni(driver.findElement(By.cssSelector("button[type='submit']")));
    }

    public void proveriNeuspesanLogin() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());    }

    @Test(priority = 1)
    public void pogresanUsername() {
        login("pogresan", validPassword);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your username is invalid!"));
        proveriNeuspesanLogin();
    }

    @Test(priority = 2)
    public void pogresanPassword() {
        login(validUsername, "pogresna");

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your password is invalid!"));
        proveriNeuspesanLogin();
    }

    @Test(priority = 3)
    public void pogresniOba() {
        login("pogresan", "pogresna");

        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your username is invalid!"));
        proveriNeuspesanLogin();
    }


    @Test(priority = 4)
    public void prazanUsername() {
        login("", validPassword);
        proveriNeuspesanLogin();
    }

    @Test(priority = 5)
    public void prazanPassword() {
        login(validUsername, "");
        proveriNeuspesanLogin();
    }

    @Test(priority = 6)
    public void praznaOba() {
        login("", "");
        proveriNeuspesanLogin();
    }

    @Test(priority = 7)
    public void validanLogin() {
        login(validUsername, validPassword);

        wait.until(ExpectedConditions.urlContains("/secure"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("You logged into a secure area!"));

        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
    }

    @AfterMethod
    public void obrisiKolacice() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
