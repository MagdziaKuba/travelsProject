import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class UnValidSingUpTest {
    @Test
    public void emptyForm() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        Thread.sleep(3000);
        List<String> errorsText= driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().map(WebElement::getText)
                .collect(Collectors.toList());
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertTrue(errorsText.contains("The Email field is required."));
        softAssert.assertTrue(errorsText.contains("The Password field is required."));
        softAssert.assertTrue(errorsText.contains("The Password field is required."));
        softAssert.assertTrue(errorsText.contains("The First name field is required."));
        softAssert.assertTrue(errorsText.contains("The Last Name field is required."));
        softAssert.assertAll();
    }
    @Test
    public void singUpInvalidEmail() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("email")).sendKeys("magda.pl");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        Thread.sleep(3000);
        List<String> errorsText= driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream().map(WebElement::getText)
                .collect(Collectors.toList());
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertTrue(errorsText.contains("The Email field must contain a valid email address."));
        softAssert.assertAll();
    }

}
