import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exceptions {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAlert() {
        driver.get("https://demoqa.com/alerts");

                try {

                    WebElement button = driver.findElement(By.id("timerAlertButton"));
                    button.click();

                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.alertIsPresent());


                    try {
                        Alert alert = driver.switchTo().alert();
                        alert.accept();

                    } catch (NoAlertPresentException noAlert) {

                        System.out.println("NoAlertPresentException occurred.");
                    }

                    driver.navigate().refresh();


                    button.click();

                    try {

                        wait.until(ExpectedConditions.alertIsPresent());
                        Alert alert = driver.switchTo().alert();
                        alert.accept()
                        ;
                    } catch (StaleElementReferenceException staleElement) {

                        System.out.println("StaleElementReferenceException occurred.");
                    }
                } catch (TimeoutException e) {

                    System.out.println("TimeOutException occurred.");
                }
            }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
