package Selenium5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Zadatak7 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://practicetestautomation.com/");
    }

    @BeforeMethod
    public void beforeMethod() {
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();

        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageButton.click();
    }

    @Test(priority = 1)
    public void userCanLogIn() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        String actualLogInMessage = driver.findElement(By.className("show")).getText();
        String expectedLogInMessage = "Logged In Successfully";

        Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        //Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        //WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        //Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");
    }
    @Test(priority = 2)
    public void nevalidanUsername(){
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student1";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        String expectedURL = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        //Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        String actualLogInMessage = driver.findElement(By.id("error")).getText();
        String expectedLogInMessage = "Your username is invalid!";
        Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        //Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        //WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        //Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");
    }

    @Test(priority = 3)
    public void nevalidanPassword(){

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";
        String validPassword = "Password1231";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        String expectedURL = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        //Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        String actualLogInMessage = driver.findElement(By.id("error")).getText();
        String expectedLogInMessage = "Your password is invalid!";
        Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        //Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        //WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        //Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");

    }

    @Test(priority = 3)
    public void userCanLogOut() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        String validUsername = "student";
        String validPassword = "Password123";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        submitButton.click();

        String expectedURL = "https://practicetestautomation.com/logged-in-successfully/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");

        WebElement pageHeading = driver.findElement(By.linkText("Log out"));
        pageHeading.click();
        Thread.sleep(3000);
        WebElement pageHeading2 = driver.findElement(By.tagName("h2"));
        String actualHeading = pageHeading2.getText();
        String expectedHeading = "Test login";
        Assert.assertEquals(actualHeading, expectedHeading);

        //String actualLogInMessage = driver.findElement(By.className("show")).getText();
        //String expectedLogInMessage = "Logged In Successfully";

        //Assert.assertEquals(actualLogInMessage, expectedLogInMessage);

        //Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        //WebElement message = driver.findElement(By.cssSelector(".has-text-align-center.wp-block-paragraph"));
        //Assert.assertEquals(message.getText(), "Congratulations "+validUsername+". You successfully logged in!");
    }
    @Test(priority = 4)
    public void usernameEmpty(){

    }
    @Test(priority = 5)
    public void passwordEmpty(){

    }
    @Test(priority = 6)
    public void Empty(){

    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterClass
    public void afterClass() {

    }
}
