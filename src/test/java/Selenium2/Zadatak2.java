package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak2 {
    static void main(String[] args) {
        //Zadatak 2
        //Otvoriti browser i jos 5 tabova
        //Na svakom tabu otvoriti URL po zelji
        //Zatvoriti sve tabove osim onog gde je otvoren Google

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");

        String google = driver.getWindowHandle();

        JavascriptExecutor js = (JavascriptExecutor) driver; //objekat
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");

        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles()); //getWindowHandles su tabovi

        driver.switchTo().window(listaTabova.get(1));
        driver.navigate().to("https://www.netflix.com");

        driver.switchTo().window(listaTabova.get(2));
        driver.navigate().to("https://www.hbo.com");

        driver.switchTo().window(listaTabova.get(3));
        //driver.navigate().to("https://itbootcamp.rs/en/");
        driver.navigate().to("https://www.google.com");

        driver.switchTo().window(listaTabova.get(4));
        driver.navigate().to("https://www.jetbrains.com");

        driver.switchTo().window(listaTabova.get(5));
        driver.navigate().to("https://www.infostud.com");
        //Selenium 2b
        /*
        for(int i = 1; i < listaTabova.size() ; i++){
            driver.switchTo().window(listaTabova.get(i));
            driver.close();
        }*/

        //Selenium 2c
        /*
        //driver.close();
        for(String element: listaTabova){
            if(!(element.equals(google))){
                driver.switchTo().window(element);
                driver.close();
            }
        }
        driver.switchTo().window(google);
        */



        //Selenium 2a - Laksi deo, ali ne i bolji
        /*
        driver.close();
        driver.switchTo().window(listaTabova.get(4));
        driver.close();
        driver.switchTo().window(listaTabova.get(3));
        driver.close();
        driver.switchTo().window(listaTabova.get(2));
        driver.close();
        driver.switchTo().window(listaTabova.get(1));
        driver.close();
*/

        //Selenium 2d

        for(int i = 0; i < listaTabova.size() ; i++){

            driver.switchTo().window(listaTabova.get(i));
            //System.out.println("Trenutni url" + driver.getCurrentUrl());
            if(!driver.getCurrentUrl().equals("https://www.google.com/")) {
                driver.close();
            }
//sta ako nijedan nije google ili sta ako su svi google

        }
    }
}
