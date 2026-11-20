package Selenium5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Zadatak8 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){

        driver = new ChromeDriver();
    }

    @Test(priority = 2)
    public void userCanLogOut(){

    }
}
