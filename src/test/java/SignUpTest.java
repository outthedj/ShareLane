import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.NoSuchElementException;

public class SignUpTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void sendFiveDigitsToZipCodeFieldTest() {
        Faker faker = new Faker();
        String zipCode= faker.address().zipCodeByState("CA");
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
    public void accountCreatedTest() {
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
                                  // Четвертая домашка!!!!!!!!!!!!!!!!!!!!!!!
    @Test    //  Вводим в поле серч "1111" и в конце проверяем есть ли "Nothing is found :(" confirmation message
    public void nothingIsFoundTest() {
        //Open Search page
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //Input "1111" to the search field
        driver.findElement(By.name("keyword")).sendKeys("1111");
        //Click [search] button
        driver.findElement(By.cssSelector("[value=Search]")).click();
        //Check message 'Nothing is found :('
        boolean isSuccessMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
        driver.quit();
        Assert.assertTrue(isSuccessMessageShown, "Nothing is found :( message isn't shown");
    }

    @Test    // Регимся, логинимся и проверяем в конце есть ли Logout на странице
    public void loginLogoutTest() throws InterruptedException {
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip
        driver.findElement(By.name("zip_code")).sendKeys("123123");
        //Click the "continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("last_name")).sendKeys("adsasdasd");
        driver.findElement(By.name("email")).sendKeys("Alex@test.com");
        driver.findElement(By.name("password1")).sendKeys("password");
        driver.findElement(By.name("password2")).sendKeys("password1");
        //Registration
        driver.findElement(By.cssSelector("[value=Register]")).click();
        //Get new email
        String emailXpath = "/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b";
        String email = driver.findElement(By.xpath(emailXpath)).getText();
        //Get new password
        String passwordXpath = "/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]";
        String password = driver.findElement(By.xpath(passwordXpath)).getText();
        //Go to login page
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //Inputting credentials
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value=Login]")).click();
        //Waiting 5 seconds
        Thread.sleep(5000);
        boolean isHellocontains = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span")).getText().contains("Hello");
        driver.quit();
        //Is Hello existed?
        Assert.assertTrue(isHellocontains, "Nothing is found :( message isn't shown");
    }

    @Test    // Регимся, логинимся и добавлем товар в корзину. Конец завернул в try/catch
    public void addToCartWithoutLoginTest() throws InterruptedException {
        //Open Zip code page
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        //Input 5 digits zip
        driver.findElement(By.name("zip_code")).sendKeys("123123");
        //Click the "continue"
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //Input data into fields
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("last_name")).sendKeys("adsasdasd");
        driver.findElement(By.name("email")).sendKeys("Alex@test.com");
        driver.findElement(By.name("password1")).sendKeys("password");
        driver.findElement(By.name("password2")).sendKeys("password1");
        //Registration
        driver.findElement(By.cssSelector("[value=Register]")).click();
        //Get new email
        String emailXpath = "/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b";
        String email = driver.findElement(By.xpath(emailXpath)).getText();
        //Get new password
        String passwordXpath = "/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]";
        String password = driver.findElement(By.xpath(passwordXpath)).getText();
        //Go to login page
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        //Inputting credentials
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value=Login]")).click();
        //Waiting 5 seconds
        Thread.sleep(2000);
        //Finding book via Search field
        driver.findElement(By.name("keyword")).sendKeys("Peace");
        //Click [search] button
        driver.findElement(By.cssSelector("[value=Search]")).click();
        //Adding to cart
        driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table[2]/tbody/tr/td[2]/p[2]/a")).click();
        try {
            //Waiting 2 seconds
            Thread.sleep(2000);
            boolean isConfirmationMessageShown = driver.findElement(By.className("confirmation_message")).isDisplayed();
            //Is Hello existed?
            Assert.assertTrue(isConfirmationMessageShown, "Confirmation message isn't shown");
        } catch (NoSuchElementException exception){
            Assert.fail();
        }
    }
}
