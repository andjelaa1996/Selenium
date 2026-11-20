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
public class DomaciSelenium4__ {

    WebDriver driver;
    WebDriverWait wait;

    String noviUsername = "student" + System.currentTimeMillis();
    String novaLozinka = "Lozinka" + System.currentTimeMillis();

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Enter umesto klika i reklame preko dugmeta ne smetaju
    //Registracija

    @Test(priority = 1)
    public void registracijaRazliciteLozinke() {
        driver.navigate().to("https://practice.expandtesting.com/register");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys(noviUsername);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(novaLozinka);
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        confirmPassword.sendKeys("NestoDrugo123");
        confirmPassword.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Passwords do not match"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/register"));
    }

    @Test(priority = 2)
    public void registracija() {
        driver.navigate().to("https://practice.expandtesting.com/register");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys(noviUsername);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(novaLozinka);
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        confirmPassword.sendKeys(novaLozinka);
        confirmPassword.sendKeys(Keys.ENTER);

        // posle uspesne registracije sajt nas vraca na login stranicu
        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Ostali smo na: " + driver.getCurrentUrl() + " | Poruka: " + flash.getText());
        Assert.assertTrue(flash.getText().contains("Successfully registered"), "Poruka: " + flash.getText());
    }

    //Nevalidan login

    @Test(priority = 3)
    public void pogresanUsername() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys("pogresan");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(novaLozinka);
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your username is invalid!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(priority = 4)
    public void pogresanPassword() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys(noviUsername);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("pogresna");
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(flash.getText().contains("Your password is invalid!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test(priority = 5)
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

    @Test(priority = 6)
    public void prazanUsername() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(novaLozinka);
        password.sendKeys(Keys.ENTER);

        // login ne sme da uspe - ostajemo na /login i nema Logout dugmeta
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    @Test(priority = 7)
    public void prazanPassword() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys(noviUsername);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    @Test(priority = 8)
    public void praznaOba() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.findElements(By.cssSelector("a[href='/logout']")).isEmpty());
    }

    //Validan login(registrovanim nalogom)

    @Test(priority = 9, dependsOnMethods = "registracija")
    public void validanLogin() {
        driver.navigate().to("https://practice.expandtesting.com/login");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        username.sendKeys(noviUsername);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(novaLozinka);
        password.sendKeys(Keys.ENTER);

        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"),
                "Ostali smo na: " + driver.getCurrentUrl() + " | Poruka: " + flash.getText());
        Assert.assertTrue(flash.getText().contains("You logged into a secure area!"), "Poruka: " + flash.getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
    }

    @AfterMethod
    public void obrisiKolacice() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
