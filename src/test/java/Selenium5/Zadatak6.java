package Selenium5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Zadatak6 {

    //Zadatak6
    //Uraditi log in (zadatak 4 klasa) koristeci anotacije
    //Na linku https://practicetestautomation.com/ otici do login stranice, uradite log in i dodajte asertacije
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void beforeMethode(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.navigate().to("https://practicetestautomation.com/");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-item-20"))).click();
        wait.until(ExpectedConditions.urlContains("/practice/"));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Test Login Page"))).click();
        wait.until(ExpectedConditions.urlContains("practice-test-login"));
    }
    //Provera da li smo na login stranici
    @Test(priority = 1)
    public void testLogin() {

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");
        Assert.assertTrue(driver.findElement(By.id("username")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("submit")).isDisplayed());
    }

    //Provera sa validnim kredencijalima username=student i password=Password123
    @Test(priority = 2)
    public void validniKredencijali(){

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("student");
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("Password123");
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        wait.until(ExpectedConditions.urlContains("/logged-in-successfully/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");

    }
    @Test(priority = 3)
    public void nevalidanUsername(){

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("student1");
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("Password123");
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        wait.until(ExpectedConditions.urlContains("/practice-test-login/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

    }
    @AfterMethod
    public void quit(){
        driver.quit();
    }




}
