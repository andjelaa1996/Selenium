package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak3_ {
    static void main(String[] args) throws InterruptedException {
        //Zadatak 3
        //Otvoriti bing pretrazivac, pretrazite 'Wikipedia'
        //otvorite wikipedia stranicu i na njoj uradite pretragu 'Nikola Tesla'

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.bing.com");
        //Thread.sleep(3000);
        WebElement searchBoxBing = driver.findElement(By.className("sb_form_q"));
        //searchBoxBing.click();
        searchBoxBing.sendKeys("Wikipedia");

        Thread.sleep(3000);

        WebElement searchButtonBing = driver.findElement(By.id("sb_hp_go"));
        searchBoxBing.clear();
        searchButtonBing.click();

        Thread.sleep(3000);


        WebElement wikipediaLink = driver.findElement(By.linkText("Wikipedia"));
        //WebElement wikipediaLink = driver.findElement(By.linkText("Wikipedia -ai"));
        wikipediaLink.click();


        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));

        Thread.sleep(3000); //nije problem cekanje

        WebElement searchBoxWikipedia = driver.findElement(By.id("searchInput"));
        searchBoxWikipedia.clear();
        searchBoxWikipedia.sendKeys("Nikola Tesla");

        Thread.sleep(3000);

        //WebElement searchButtonWikipedia = driver.findElement(By.cssSelector(".pure-button.pure-button-primary-progressive"));
        WebElement searchButtonWikipedia = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButtonWikipedia.click();

    }
}
