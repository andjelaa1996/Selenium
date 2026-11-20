package Selenium3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak4__logout {


    static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practicetestautomation.com/");

        Thread.sleep(1000);

        //WebElement clickAI = driver.findElement(By.className("formkit-close"));
        //clickAI.click();

        //Thread.sleep(3000);

        WebElement clickPractice = driver.findElement(By.id("menu-item-20"));
        //clickPractice.clear();
        clickPractice.click();

        WebElement login = driver.findElement(By.linkText("Test Login Page"));
        login.click();
        Thread.sleep(1000);

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("student");
        Thread.sleep(1000);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123");
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        Thread.sleep(3000);
        WebElement pageHeading = driver.findElement(By.linkText("Log out"));
        pageHeading.click();

        WebElement pageHeading2 = driver.findElement(By.linkText("Test login"));
        String actualHeading = pageHeading2.getText();
        String expectedHeading = "Test login";
        Assert.assertEquals(actualHeading, expectedHeading);

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(actualURL,expectedURL);
    }

}
