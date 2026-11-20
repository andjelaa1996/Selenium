package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Selenium2 {
    static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver; //objekat
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");

        driver.navigate().to("https://www.youtube.com"); //na tabu sa indeksom nula iliti prvi

        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());

        System.out.println("Lista tabova: " +  listaTabova); //u konzoli kako izgleda lista tabova ID stringovi

        driver.switchTo().window(listaTabova.get(1));

        driver.navigate().to("https://www.linkedin.com");

        driver.switchTo().window(listaTabova.get(2));

        driver.navigate().to("https://www.joberty.com");

        driver.switchTo().window(listaTabova.get(3));

        driver.navigate().to("https://www.google.com");

        driver.close(); //samo poslednji prozor

        //driver.quit(); // citav browser bi zatvorio







    }
}
