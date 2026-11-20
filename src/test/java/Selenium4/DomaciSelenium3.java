package Selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class DomaciSelenium3 {
    static void main(String[] args) {
        //Domaci:
        //Koristite https://the-internet.herokuapp.com/dynamic_loading/2, gde se element pojavljuje tek posle klika. Kliknite na dugme Start i sačekajte da se pojavi tekst “Hello World!“.
        //Za ovaj zadatak koristiti asertacije i waitere

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/2");
            WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start button")));
            Assert.assertEquals(startButton.getText(), "Start");


            Assert.assertTrue(driver.findElements(By.id("finish")).isEmpty(), "Element #finish ne bi trebalo da postoji pre klika");
            startButton.click();

            WebElement loading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
            Assert.assertTrue(loading.isDisplayed());
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            WebElement helloText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));

            // ---- Asertacije ----
            Assert.assertTrue(helloText.isDisplayed());
            Assert.assertEquals(helloText.getText(), "Hello World!");
            Assert.assertFalse(driver.findElement(By.cssSelector("#start button")).isDisplayed(), "Dugme Start je trebalo da nestane posle klika");
            System.out.println("Test je prosao! Pojavio se tekst: " + helloText.getText());
            driver.quit();

    }
}
