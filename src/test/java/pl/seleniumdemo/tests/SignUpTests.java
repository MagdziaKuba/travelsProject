package pl.seleniumdemo.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;


public class SignUpTests extends BaseTests {


    @Test
    public void signUp() throws InterruptedException {
        String lastName="Nazwisko";
        int randomNumber=(int) (Math.random()*1000);
        String email="tester"+randomNumber+"@magda.pl";

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        /*User user=new User();
        user.setFirstName("Magda");
        user.setLastName("Nazwisko");
        user.setEmail("aaaaamagda@mg.pl");
        user.setPhone("222222222");
        user.setPassword("haslooo");
*/


        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.setFirstName("Magda");
        signUpPage.setLastName(lastName);
        signUpPage.setEmail(email);
        signUpPage.setPhone("222222222");
        signUpPage.setPassword("Password");
        signUpPage.confirmPassword("Password");
        signUpPage.clickSignUpButton();

        Thread.sleep(3000);
        LoggedUserPage loggedUserPage=new LoggedUserPage(driver);
        loggedUserPage.getHeadingText();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
    }

    @Test
    public void emptyForm() throws InterruptedException {

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.clickSignUpButton();

        List<String> errors=signUpPage.getErrors();

        SoftAssert softAssert= new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }
    @Test
    public void singUpInvalidEmail() throws InterruptedException {

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage=new SignUpPage(driver);

        signUpPage.fillSignUpForm("Magda","Nazwisko","222222222", "niepoprawny","haslooo","haslooo");

        SoftAssert softAssert= new SoftAssert();
        softAssert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
        softAssert.assertAll();
    }
}


