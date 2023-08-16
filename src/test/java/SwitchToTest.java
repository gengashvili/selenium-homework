import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class SwitchToTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test(priority = 1)
    public void testIframe() {
        driver.get("http://the-internet.herokuapp.com/iframe");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement iFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mce_0_ifr")));

        driver.switchTo().frame(iFrame);

        WebElement textField = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
        textField.sendKeys("Here Goes");

        System.out.println(textField.getText());

        driver.switchTo().defaultContent();

        WebElement alignCenterIcon = driver.findElement(By.xpath("//button[@title='Align center']"));
        alignCenterIcon.click();

    }

    @Test(priority = 2)
    public void testAlert() {
        driver.get("https://demoqa.com/alerts");

        WebElement firstBtn = driver.findElement(By.id("alertButton"));
        firstBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        System.out.println("alert text : " + alert.getText());
        alert.accept();

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
