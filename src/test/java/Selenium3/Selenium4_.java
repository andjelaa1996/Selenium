package Selenium3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class Selenium4_ {

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
        searchBoxBing.click();
        searchBoxBing.sendKeys("Wikipedia");

        Thread.sleep(3000);

        WebElement searchButtonBing = driver.findElement(By.id("sb_hp_go"));
        searchBoxBing.clear();
        searchButtonBing.click();

        Thread.sleep(3000);


        WebElement wikipediaLink = driver.findElement(By.linkText("Wikipedia"));
        //WebElement wikipediaLink = driver.findElement(By.linkText("Wikipedia -ai"));
        wikipediaLink.clear();
        wikipediaLink.click();


        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));

        Thread.sleep(3000); //nije problem cekanje

        WebElement searchBoxWikipedia = driver.findElement(By.id("searchInput"));
        searchBoxWikipedia.clear();
        searchBoxWikipedia.sendKeys("Nikola Tesla");
        //wikipediaLink.click();

        //Thread.sleep(3000);

        //WebElement searchButtonWikipedia = driver.findElement(By.cssSelector(".pure-button.pure-button-primary-progressive"));
        WebElement searchButtonWikipedia = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButtonWikipedia.click();
        //------------------------------------------------------------


        Thread.sleep(3000);

        //driver.getCurrentUrl();//kao zapamcen
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://en.wikipedia.org/wiki/Nikola_Tesla";
        Assert.assertEquals(actualURL,expectedURL);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Nikola Tesla - Wikipedia";
        Assert.assertEquals(actualTitle,expectedTitle);

        WebElement pageHeading = driver.findElement(By.id("firstHeading"));
        String actualHeading = pageHeading.getText();
        String expectedHeading = "Nikola Tesla";
        Assert.assertEquals(actualHeading,expectedHeading);

        WebElement pageImage = driver.findElement(By.className("infobox-image"));
        Assert.assertTrue(pageImage.isDisplayed());

        String expectedAltText = "Head-and-shoulder photograph of a slender man with dark hair and moustache, dark suit and white-collar shirt";
        //WebElement imageAlt = driver.findElement(By.tagName("img")); //trazice u citavom htmlu
        WebElement imageAlt = pageImage.findElement(By.tagName("img"));
        String actualAltText = imageAlt.getAttribute("alt");
        Assert.assertEquals(actualAltText,expectedAltText);
    }
}
