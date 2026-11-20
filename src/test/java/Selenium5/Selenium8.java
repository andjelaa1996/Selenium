package Selenium5;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
public class Selenium8 {
    @BeforeClass
    public void beforeClass(){
        System.out.println("ISPIS IZ BEFORE CLASS");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("ISPIS IZ BEFORE METODE");
    }
    @Test
    public void test1(){
        System.out.println("Ovo je test 1");
    }
    @Test
    public void test2(){
        System.out.println("Ovo je test 2");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AFTER Method");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("AFTER Class");
    }
}
