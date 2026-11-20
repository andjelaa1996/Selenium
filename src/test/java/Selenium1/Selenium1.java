package Selenium1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium1 {
    static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        //u ovoj komandi postavljam dirver

        WebDriver driver = new ChromeDriver();
        //U ovoj komandi kreiram objekat kom cu davati komande

        driver.navigate().to("https://www.google.com");
        //driver.get("https://www.google.com");

        driver.manage().window().maximize();

        System.out.println(driver.getCurrentUrl());

        driver.navigate().to("https://www.youtube.com");

        System.out.println(driver.getCurrentUrl());

        driver.navigate().back();

        driver.navigate().refresh();

        driver.navigate().forward();

        driver.quit();

    }
}
