package pageObjectPattern;

import org.testng.annotations.Test;

public class GoogleTest {
    @Test
    public void googleSearchTest(){
        GoogleHomePage googleHomePage=new GoogleHomePage();
        googleHomePage.searchInGoogle("Selenium");
    }
}
