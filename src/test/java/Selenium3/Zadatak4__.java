package Selenium3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak4__ {

    static void main(String[] args) throws InterruptedException {

        //Zadatak 4
        //Na linku https://practicetestautomation.com/ otici do login stranice, uradite log in i dodajte asertacije

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

        String validUsername = "student";
        String  validPassword = "Password123";

        WebElement username = driver.findElement(By.id("username"));
        username.clear();
        username.sendKeys("student");
        Thread.sleep(1000);

        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        password.sendKeys("Password123");
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        Thread.sleep(3000);

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(actualURL,expectedURL);
        //Assert.assertEquals(driver.getCurrentUrl(),expectedURL);
        //Assert.assertEquals(driver.getCurrentUrl(),"https://practicetestautomation.com/logged-in-successfully/");
        Thread.sleep(3000);
        WebElement pageHeading = driver.findElement(By.className("post-title"));
        String actualHeading = pageHeading.getText();
        String expectedHeading = "Logged In Successfully";
        Assert.assertEquals(actualHeading, expectedHeading);

        //System.out.println(driver.getTitle()); //sta pise u browseru







    }



}
