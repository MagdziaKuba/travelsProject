package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void setPhone(String phone){
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email){
        emailInput.sendKeys(email);
    }

    public void setPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void confirmPassword(String confirmpassword){
        confirmpasswordInput.sendKeys(confirmpassword);
    }

    public void clickSignUpButton(){
        signUpButton.click();
    }

    public List<String> getErrors() throws InterruptedException {
        Thread.sleep(3000);
        return errors.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void fillSignUpForm(String firstName, String lastName, String phone, String email,String password, String  confirmpassword){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmpasswordInput.sendKeys(confirmpassword);
        signUpButton.click();
    }

    public void fillSignUpFormWithUser(User user){
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmpasswordInput.sendKeys(user.getPassword());
        signUpButton.click();
    }
}
