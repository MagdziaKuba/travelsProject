package pl.seleniumdemo.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HotelSearchPage {
    @FindBy(xpath="//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath="//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    /*@FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;*/

    @FindBy(name="checkin")
    private WebElement checkInput;

    @FindBy(name="checkout")
    private WebElement checkoutInput;

    @FindBy(id="travellersInput")
    private WebElement travellersInput;

    @FindBy(id="adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id="childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath="//button[text()=' Search']")
    private WebElement searchButton;


    @FindBy(xpath="//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath="//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;



    //pola
    private WebDriver driver;

    private static final Logger logger= LogManager.getLogger();

    //konstruktor
    public HotelSearchPage(WebDriver driver){

        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public void setCity(String cityName) throws InterruptedException {
        //System.out.println("Setting city "+ cityName);
        logger.info("Setting city "+ cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath= String.format("//span[@class='select2-match' and text()='%s']", cityName);
        Thread.sleep(2000);
        driver.findElement(By.xpath(xpath)).click();
        //System.out.println("Setting city done");
        logger.info("Setting city done");
        //hotelMatch.click();

    }

    public void setDates(String checkin, String checkout){
        logger.info("Setting dates "+checkin +" - "+checkout);
        checkInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("Setting dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd) throws InterruptedException {
        logger.info("Adding adults: "+adultsToAdd+" and kids: "+childToAdd);
        travellersInput.click();
        Thread.sleep(3000);
        addTraveler(adultPlusBtn,adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("Adding travelers done");
        /*for(int z=0; z<adultsToAdd; z++){
            adultPlusBtn.click();
        }

        for(int j=0; j<childToAdd; j++){
            childPlusBtn.click();
        }*/

    }

    public void addTraveler(WebElement travelerBtn, int numberOfTravelers){
        for(int j=0; j<numberOfTravelers; j++){
            travelerBtn.click();
        }
    }
    public void performSearch(){
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
    }

    public void openSignUpForm(){
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }
}
