import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

public class Firsttest {
    
    public static final String BASIC_URL= "https://ru.wikipedia.org/";
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get(BASIC_URL);

    }
    @AfterTest

    public void  afterTest(){

        driver.quit();

    }

    @DataProvider(name="search")
    public Object [][] getSearch(){
        return new Object[][]{
                {"fox"},
                {"dog"}
        };
    }

    @Test(dataProvider = "search")

    public void verifySearch(String searchValue) {

        searchForText(driver, searchValue);
        String actualUrl=driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.toLowerCase().contains(searchValue.toLowerCase()));


    }


    private void searchForText(WebDriver driver, String valueForSearch) {
        By pathToSearchInput = By.className ("vector-search-box-input");
        WebElement searchInput = driver.findElement(pathToSearchInput);
        searchInput.click();
        searchInput.sendKeys(valueForSearch);
        searchInput.sendKeys(Keys.ENTER);
    }
}
