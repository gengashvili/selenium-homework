import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class CrossBrowserTest {
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
    public void testTodoList() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

        try {
            WebElement todos = driver.findElement(By.xpath("//*[@id='container']/ul"));
            WebElement todoItem = todos.findElement(By.xpath(".//li[contains(., 'Practice magic')]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(todoItem).perform();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].querySelector('span > i.fa.fa-trash').click();", todoItem);

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.stalenessOf(todoItem));

            if (todos.findElements(By.tagName("li")).size() < 3) {
                System.out.println("todo item deleted successfulyy");
            } else {
                System.out.println("todo item did not deleted");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
