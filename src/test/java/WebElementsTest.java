import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebElementsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddRemoveElements() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElementBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));

        for (int i = 0; i < 3; i++) {
            addElementBtn.click();
        }

        WebElement lastDeleteBtn1 = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[last()]"));
        System.out.println("last delete button text : " + lastDeleteBtn1.getText());

        List<WebElement> deleteBtnList = driver.findElements(By.cssSelector("button[class^= 'added']"));
        WebElement lastDeleteBtn2 = deleteBtnList.get(deleteBtnList.size() - 1);
        System.out.println("last delete button text : " + lastDeleteBtn2.getText());

        WebElement lastDeleteBtn3 = driver.findElement(By.xpath("//button[contains(@class, 'manually') and contains(text(), 'Delete')][last()]"));
        System.out.println("last delete button text : " + lastDeleteBtn3.getText());

    }

    @Test
    public void testChallengingDom() {
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        WebElement loremHeading = driver.findElement(By.xpath("//th[contains(text(), 'Lorem')]"));
        int indexOfLoremHeading = loremHeading.findElements(By.xpath("preceding-sibling::th")).size() + 1;

        WebElement trElement = driver.findElement(By.xpath("//tr[td[contains(text(), 'Apeirian9')]]"));
        WebElement loremValue  = trElement.findElement(By.xpath("./td["+indexOfLoremHeading+"]"));

        System.out.println(loremValue.getText());

    }


    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
