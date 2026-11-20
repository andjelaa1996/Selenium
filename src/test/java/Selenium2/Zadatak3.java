package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak3 {
    static void main(String[] args) throws InterruptedException {
        //Zadatak 3
        //Otvoriti bing pretrazivac, pretrazite 'Wikipedia'
        //otvorite wikipedia stranicu i na njoj uradite pretragu 'Nikola Tesla'

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.bing.com");
        Thread.sleep(3000);

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        WebElement lokatorPoClass = driver.findElement(By.className("sb_form_q"));
        lokatorPoClass.sendKeys("Wikipedia");
        lokatorPoClass.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement xpath = driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a"));
        xpath.click();
        Thread.sleep(3000);
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
        Thread.sleep(3000);
        WebElement lokatorPoID = driver.findElement(By.id("searchInput"));
        Thread.sleep(3000);
        lokatorPoID.sendKeys("Nikola Tesla");
        lokatorPoID.sendKeys(Keys.ENTER);
    }
}
