import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class NoResultsFound extends BaseTest{
    @Test
    public void searchHotel() throws InterruptedException {



        Thread.sleep(3000);
        driver.findElement(By.name("checkin")).sendKeys("18/05/2022");
        driver.findElement(By.name("checkout")).sendKeys("11/07/2022");

        //dok.
        driver.findElement(By.id("travellersInput")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        String noFoundText= driver.findElement(By.xpath("/html/body/div[5]/div[5]/div[1]/div[3]/div/div/h2")).getText();
        //$x("//div[@class='itemscontainer']//h2")
        System.out.println(noFoundText);
        Assert.assertEquals(noFoundText, "No Results Found");
    }
}
