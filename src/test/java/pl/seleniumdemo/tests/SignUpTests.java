package pl.seleniumdemo.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTests {


    @Test
    public void signUp() throws InterruptedException {

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Magda");
        driver.findElement(By.name("lastname")).sendKeys("Nazwisko");
        driver.findElement(By.name("phone")).sendKeys("222222222");
        //unikalny adress email
        int randomNumber=(int) (Math.random()*1000);
        String email="tester"+randomNumber+"@magda.pl";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Hasełko");
        driver.findElement(By.name("confirmpassword")).sendKeys("Hasełko");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        String lastname="Nazwisko";
        Thread.sleep(3000);
        WebElement heading=driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastname));
    }
}


