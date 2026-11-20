package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {
    static void main(String[] args) {


        //Sleepers - ceka toliko vremena kolimo mu prosledimo u testu
        //Cekanje je fiksno
        //Moze se koristiti tokom kreiranje tesotva
        //Ne preporucuje se u praksi da se nalazi ova vrsta cekanja u testovima

        //Implicit wait - ceka zadato vreme dok se element ne pojavi

        //Explicit wait - moramo da kreiramo objekat, sacekaj da se url promeni, da na tajmeru bude toliko i toliko ili da dugme postane klikabilno
        //Ceka zadatako vreme dok se ispuni uslov

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practicetestautomation.com/");

        //Implicit wait definisemo samo jednom, idealno bi bilo da bude na pocetku
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // broj sekundi koliko hocemo da cekamo
        //Ako podesimo na 2s, probace na 500ms, na toliko vrsi proveru
        //Ako postavimo Thread.sleep na 500ms, kao da iz druge uspe
        //kada imamo element not found, kad program prebrzo ucita, a nama se sporo ucitavaju

        //Explicit wait definisemo samo jednom ali poizivamo po potrebi
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-item-20"))));
        WebElement testLoginPageButton = driver.findElement(By.linkText("Test Login Page"));
        wait.until(ExpectedConditions.elementToBeClickable(testLoginPageButton));
        testLoginPageButton.click();
        wait.until(ExpectedConditions.urlToBe("https://practicetestautomation.com/practice-test-login/"));

    }
}
