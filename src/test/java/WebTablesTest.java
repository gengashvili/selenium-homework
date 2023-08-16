import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebTablesTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testTable() {
        driver.get("https://techcanvass.com/Examples/webtable.html");

        WebElement table = driver.findElement(By.xpath("//table[@id= 't01']"));

        WebElement hondaRow = table.findElement(By.xpath("//tr[td[contains (text(), 'Honda')]]"));

        WebElement hondaPrice = hondaRow.findElement(By.xpath(".//td[3]"));

        System.out.println("honda price : "  + hondaPrice.getText());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
