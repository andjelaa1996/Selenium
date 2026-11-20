package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Selenium6 {
    static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("https://demoqa.com/");

        //WebElement card = driver.findElement(By.className("card-body"));
        //card.click();
        List<WebElement> cards = driver.findElements(By.className("card-body"));

        for(int i = 0; i < cards.size(); i++){



            if(cards.get(i).getText().equals("Book Store Application")){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cards.get(i));
                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: ‘center’, inline: ‘center’});“, card");
                Thread.sleep(3000);
                cards.get(i).click();
                break;
                //JavascriptExecutor js = (JavascriptExecutor) driver;
                //js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                //cards.get(i).click();
                //break;
            }
        }
    }
}
