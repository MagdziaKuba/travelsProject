package pl.seleniumdemo.tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;


public class HotelSearchTests extends BaseTests {
    @Test
    public void searchHotel() throws InterruptedException, IOException {
        ExtentTest test= extentReports.createTest("Search Hotel Test");

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");

        test.log(Status.PASS,"Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("18/05/2022","11/07/2022");
        test.log(Status.PASS,"Setting data done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(2,5);
        test.log(Status.PASS,"Setting travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"performing search done");

        test.log(Status.PASS,"Screenshot", MediaEntityBuilder.createScreenCaptureFromPath("src/test/resources/screenshots/screenshot.png").build());


        ResultsPage resultsPage=new ResultsPage(driver);
        List<String> hotelNames=resultsPage.getHotelNames();
        //List<String> hotelNames=driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b")).stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());

        System.out.println(hotelNames.size());
        //zapis lambda
        //hotelNames.forEach(el -> System.out.println(el));
        hotelNames.forEach(System.out::println);
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        test.log(Status.PASS,"assertions passed");

    }

    @Test
    public void searchHotels() throws InterruptedException {

        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setDates("18/05/2022","11/07/2022");

        Thread.sleep(3000);
        hotelSearchPage.setTravellers(3,4);
        hotelSearchPage.performSearch();

        //driver.findElement(By.xpath("//button[text()=' Search']")).click();
        //String noFoundText= driver.findElement(By.xpath("/html/body/div[5]/div[5]/div[1]/div[3]/div/div/h2")).getText();
        //$x("//div[@class='itemscontainer']//h2")
        ResultsPage resultsPage=new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }
}


