package Selenium5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Zadatak9 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");
        WebElement popupButton = driver.findElement(By.cssSelector("button[type='submit']"));
        popupButton.click();
        Thread.sleep(3000);
        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

    }

    @Test(priority = 1)
    public void praznaKorpa(){
        WebElement itemName = driver.findElement(By.id("productTitle"));
        String itemNameText = itemName.getText();
        WebElement cartNumber = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartNumber.getText(), "0");
        cartNumber.click();
        WebElement emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());
        driver.navigate().back();
    }
    @Test(priority = 2)
    public void dodavanjeKnjige(){
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
        WebElement addedToCartMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(addedToCartMessage.isDisplayed());
        WebElement cartNumber = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartNumber.getText(), "1");

    }

    @AfterMethod
    public void cookies(){
        driver.manage().deleteAllCookies();
    }
    @AfterClass
    public void quit(){
       driver.quit();
    }


}
