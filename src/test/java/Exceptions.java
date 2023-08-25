import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Exceptions {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            this.driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            this.driver = new FirefoxDriver(options);

        } else {
            System.out.println("browser name is not correct");
        }
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
