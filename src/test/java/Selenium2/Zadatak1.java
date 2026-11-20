package Selenium2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

    static void main(String[] args) {

        //Zadatak 1
        //Pokrenite browser, idite na Linkedin sajt, uradite refresh, idite na Joberty sajt
        //vratite se nazad i odstampajte poslednji URL na kom se nalazite
        //na kraju zatvorite driver


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); //start 3 reda

        driver.navigate().to("https://www.linkedin.com/");
        driver.navigate().refresh();
        driver.navigate().to("https://www.joberty.com/sr/");
        driver.navigate().back();
        System.out.println("Ovo je link " + driver.getCurrentUrl());
        driver.quit();


    }

}
