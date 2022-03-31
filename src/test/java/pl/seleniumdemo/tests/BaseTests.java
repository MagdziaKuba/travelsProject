package pl.seleniumdemo.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

public class BaseTests {
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        //WebDriverManager.chromedriver().setup();
        //driver=new ChromeDriver();
        driver= DriverFactory.getDriver("firefox");
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

