import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class CommandsTest {
    private  WebDriver driver;

    public CommandsTest() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testDynamicControls() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enableButton.click();

        Thread.sleep(4000);

        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        WebElement enabledText = driver.findElement(By.id("message"));

        if(inputField.isEnabled()) {
            System.out.println("input field became enable");
        } else{
            System.out.println("input field is not enable");
        }

        if (enabledText.isDisplayed()) {
            System.out.println("enable text is displayed and its value is : " + enabledText.getText());
        } else {
            System.out.println("enable text is not displayed");
        }

        String buttonText = enableButton.getText();

        if (buttonText.equals("Disable")) {
            System.out.println("button text has changed from Enable from Disable");
        } else {
            System.out.println("button text did not changed");
        }

        inputField.sendKeys("Bootcamp");
        System.out.println("input field value is : "+inputField.getAttribute("value"));
        inputField.clear();

    }

    @Test(priority = 2)
    public void testDragAndDrop() {
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

        if(columnA.getLocation().y == columnB.getLocation().y) {
            System.out.println("Y coordinate of column A and column B are the same");
        } else {
            System.out.println("Y coordinate of column A and column B are not the same");
        }

        driver.close();

    }




}

