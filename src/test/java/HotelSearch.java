import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearch extends BaseTest{
    @Test
    public void searchHotel() throws InterruptedException {

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
        //zapisanie za pomocą lambda
        //driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el ->el.click());
        //inny sposób zapisu
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        //dok.
        driver.findElement(By.id("travellersInput")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        //pobieranie nazw hoteli
        //pobrało 2 z 4, nie wszystkie się załadowały
        //List<String> hotelNames=driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream().map(el -> el.getText()).collect(Collectors.toList());
        //pobrało 4 z 4
        List<String> hotelNames=driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());

        System.out.println(hotelNames.size());
        //zapis lambda
        //hotelNames.forEach(el -> System.out.println(el));
        hotelNames.forEach(System.out::println);
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }
}
