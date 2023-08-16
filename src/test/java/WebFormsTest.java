import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
    }

    @Test(priority = 1)
    public void testDropDown() {
        WebElement programmingLanguage = driver.findElement(By.cssSelector("#dropdowm-menu-1 > option[value = 'python']"));
        programmingLanguage.click();

        if (programmingLanguage.isSelected()){
            System.out.println(programmingLanguage.getText() + " is selected");
        }
    }


    @Test(priority = 2)
    public void testCheckBoxes() {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("#checkboxes > label > input[type = 'checkbox']"));

        for (WebElement item: checkBoxes) {
            if (!item.isSelected()) {
                item.click();
            }
            if (item.isSelected()) {
                System.out.println(item.getAttribute("value") + " is selected");
            }
        }
    }

    @Test(priority = 3)
    public void testRadioButton() {
        WebElement yellowBtn = driver.findElement(By.cssSelector("#radio-buttons > input[value = 'yellow']"));
        yellowBtn.click();

        if (yellowBtn.isSelected()){
            System.out.println("yellow button is clicked");
        }
    }


    @Test(priority = 4)
    public void testSelectedAndDisabled() {
        WebElement orangeOption = driver.findElement(By.cssSelector("#fruit-selects > option[value = 'orange']"));

        if (!orangeOption.isEnabled()){
            System.out.println("orange option is disabled" );
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
