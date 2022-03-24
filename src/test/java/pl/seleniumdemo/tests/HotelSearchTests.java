package pl.seleniumdemo.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import java.util.List;


public class HotelSearchTests extends BaseTests {
    @Test
    public void searchHotel() throws InterruptedException {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("18/05/2022","11/07/2022");
        hotelSearchPage.setTravellers(2,5);
        hotelSearchPage.performSearch();


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


