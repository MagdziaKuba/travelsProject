package pl.seleniumdemo.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTests extends BaseTests {


    @Test
    public void signUp() throws InterruptedException {
        String lastName="Nazwisko";
        int randomNumber=(int) (Math.random()*1000);
        String email="tester"+randomNumber+"@magda.pl";

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.setFirstName("Magda");
        signUpPage.setLastName(lastName);
        signUpPage.setEmail(email);
        signUpPage.setPhone("222222222");
        signUpPage.setPassword("Password");
        signUpPage.confirmPassword("Password");
        signUpPage.clickSignUpButton();

        Thread.sleep(3000);
        WebElement heading=driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
    }
}


