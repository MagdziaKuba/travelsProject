package pl.seleniumdemo.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTests extends BaseTests {
    @Test
    public void searchHotel() throws InterruptedException {
        HotelSearchPage hotelSearchPage=new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("18/05/2022","11/07/2022");
        hotelSearchPage.setTravellers();
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
}


