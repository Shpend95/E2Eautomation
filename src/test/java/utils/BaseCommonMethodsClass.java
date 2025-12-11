package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BaseCommonMethodsClass extends PageInitializer {

    public static WebDriver driver;
    public static WebDriverWait driverWait;


    public static void launchBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        Log.info("Opening the website");
        driver.get("http://54.198.61.50/web/index.php/auth/login");
        Log.info("Website opened successfully");
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        initializePageObject();
    }


}
