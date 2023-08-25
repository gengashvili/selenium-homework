import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class AutoComplete {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSearchBox() {
        driver.get("https://www.google.com/");

        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        searchBox.sendKeys("Automation");

        WebDriverWait wait =  new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Alh6id\"]/div[1]/div/ul")));
        List<WebElement> suggestions = driver.findElements(By.xpath("//*[@id=\"Alh6id\"]/div[1]/div/ul/li"));

        WebElement lastElement = suggestions.get(suggestions.size() - 1);
        lastElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"APjFqb\"]")));
        WebElement clickedSuggestion = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));

        System.out.println("clicked on last suggestion: " + clickedSuggestion.getText());
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
