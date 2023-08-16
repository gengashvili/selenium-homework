import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class WaitsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProgressBar() {
        driver.get("https://demoqa.com/progress-bar");

        WebElement startBtn = driver.findElement(By.id("startStopButton"));
        startBtn.click();

        WebElement progressBar = driver.findElement(By.cssSelector(".progress > .progress-bar"));

        new WebDriverWait(driver, 20).until(d -> progressBar.getText().equals("100%"));

        System.out.println(progressBar.getText());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
