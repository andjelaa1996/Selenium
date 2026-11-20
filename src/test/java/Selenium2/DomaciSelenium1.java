package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DomaciSelenium1 {
    static void main(String[] args) throws InterruptedException {
        //Domaci:
        //Otvoriti youtube i pustiti pesmu po izboru

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.youtube.com/");
        Thread.sleep(3000);
        WebElement lokatorPoCSS1 = driver.findElement(By.cssSelector(".ytSearchboxComponentInput.yt-searchbox-input.title"));
        lokatorPoCSS1.sendKeys("tnt");
        lokatorPoCSS1.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement lokatorPoCSS2 = driver.findElement(By.linkText("AC/DC - T.N.T. (Official Audio)"));
        lokatorPoCSS2.click();
    }
}
