import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void sendFiveDigitsToZipCodeFieldTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Check that 'Register' button is shown
        boolean isRegisterButtonDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isRegisterButtonDisplayed, "Register button is not shown");
    }

    @Test
    public void sendFourDigitsToZipCodeFieldTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 4 digits zip
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        //Click the "continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Check that error message is shown
        boolean isErrorMessageIsShown = driver.findElement(By.className("error_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isErrorMessageIsShown, "Error message is not shown");
    }

    @Test
    public void accountCreated() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //Click the "continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("last_name")).sendKeys("adsasdasd");
        driver.findElement(By.name("email")).sendKeys("Alex@test.com");
        driver.findElement(By.name("password1")).sendKeys("password");
        driver.findElement(By.name("password2")).sendKeys("password1");
        //Click register button
        driver.findElement(By.cssSelector("[value=Register]")).click();
        //Check message 'Account is created'
        boolean isSuccessMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessMessageShown, "Success message isn't shown");
    }

    @Test
    public void nothingIsFoundTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Open Search page
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //Input "1111" to the search field
        driver.findElement(By.name("keyword")).sendKeys("111");
        //Click [search] button
        driver.findElement(By.cssSelector("[value=Search]")).click();
        //Check message 'Nothing is found :('
        boolean isSuccessMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessMessageShown, "Nothing is found :( message isn't shown");
    }
}
