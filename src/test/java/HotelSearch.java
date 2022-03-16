import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HotelSearch {
    @Test
    public void searchHotel() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("18/05/2022");
        //driver.findElement(By.name("checkout")).sendKeys("11/07/2022");
        /*driver.findElement(By.name("checkin")).click();
        driver.findElement(By.xpath("//td[@class='day ' and text()='17']")).click();*/
        driver.findElement(By.name("checkout")).click();
        //driver.findElement(By.xpath("//td[@class='day ' and text()='22']")).click();
        //musi byc findElements do stream()
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el ->el.click());
        //dok.
    }
}
