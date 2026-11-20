package Slack;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SeleniumDomaci2Napredniji {
    public static void main(String[] args) throws InterruptedException {

        //Tri niza iste duzine: na istom indeksu je jedna kombinacija.
        //Validna kombinacija je namerno POSLEDNJA - posle uspesnog logina jer se javlja problem sa prozorom "Change your password"
        String [] usernames = {
                "tomsmith",     // 0: validan
                "tomsmith",     // 1: validan
                "tomsmithh",    // 2: nevalidan
                "",             // 3: prazan
                "tomsmithh",    // 4: nevalidan
                "",             // 5: prazan
                "tomsmithh",    // 6: nevalidan
                "",             // 7: prazan
                "tomsmith"      // 8: validan
        };

        String [] passwords = {
                "SuperSecretPassword!!!!",  // 0: nevalidan
                "",                         // 1: prazan
                "SuperSecretPassword!",     // 2: validan
                "SuperSecretPassword!",     // 3: validan
                "SuperSecretPassword!!!!",  // 4: nevalidan
                "",                         // 5: prazan
                "",                         // 6: prazan
                "SuperSecretPassword!!!!",  // 7: nevalidan
                "SuperSecretPassword!"      // 8: validan
        };

        String [] ocekivanePoruke = {
                "Your password is invalid!",       // 0
                "Your password is invalid!",       // 1
                "Your username is invalid!",       // 2
                "Your username is invalid!",       // 3
                "Your username is invalid!",       // 4
                "Your username is invalid!",       // 5
                "Your username is invalid!",       // 6
                "Your username is invalid!",       // 7
                "You logged into a secure area!"   // 8
        };

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        for (int i = 0; i < usernames.length; i++) {
            System.out.println("Kombinacija " + i + ": username='" + usernames[i]
                    + "', password='" + passwords[i] + "'");

            driver.navigate().to("https://the-internet.herokuapp.com/login");
            Thread.sleep(1000);

            WebElement username = driver.findElement(By.id("username"));
            username.clear();
            username.sendKeys(usernames[i]);

            WebElement password = driver.findElement(By.id("password"));
            password.clear();
            password.sendKeys(passwords[i]);

            WebElement login = driver.findElement(By.cssSelector("button[type='submit']"));
            login.click();
            Thread.sleep(3000);

            //Asertacije
            WebElement flash = driver.findElement(By.id("flash"));
            String text = flash.getText();
            Assert.assertTrue(text.contains(ocekivanePoruke[i]),
                    "Kombinacija " + i + " - dobijena poruka: " + text);

            if (ocekivanePoruke[i].equals("You logged into a secure area!")) {
                //uspesan login
                Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");

                String heading = driver.findElement(By.className("subheader")).getText();
                Assert.assertEquals(heading,
                        "Welcome to the Secure Area. When you are done click logout below.");

                //Logout
                //proveravamo da Logout dugme postoji
                WebElement logout = driver.findElement(By.cssSelector("a[href='/logout']"));
                Assert.assertTrue(logout.isDisplayed(), "Logout dugme se ne vidi");

                //Klik ne radi zbog Chrome prozora "Change your password" i zato otvaramo adresu na koju dugme vodi
                driver.navigate().to("https://the-internet.herokuapp.com/logout");
                Thread.sleep(2000);

                //posle logout-a vraceni smo na login stranicu
                Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
                String logoutText = driver.findElement(By.id("flash")).getText();
                Assert.assertTrue(logoutText.contains("You logged out of the secure area!"),
                        "Logout poruka nije dobra: " + logoutText);
                System.out.println("Logout je okej");
            } else {
                //neuspesan login - ostajemo na login stranici
                Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/login");
            }

            System.out.println("Okej");
        }

        System.out.println("Sve kombinacije su prosle");
        driver.quit();
    }
}
