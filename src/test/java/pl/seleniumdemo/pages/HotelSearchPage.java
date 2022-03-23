package pl.seleniumdemo.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HotelSearchPage {
    @FindBy(xpath="//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath="//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

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

    //konstruktor
    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setCity(String cityName) throws InterruptedException {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        Thread.sleep(2000);
        hotelMatch.click();

    }

    public void setDates(String checkin, String checkout){
        checkInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
    }

    public void setTravellers(int adultsToAdd, int childToAdd) throws InterruptedException {
        travellersInput.click();
        Thread.sleep(3000);
        addTraveler(adultPlusBtn,adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);

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
        searchButton.click();
    }
}
