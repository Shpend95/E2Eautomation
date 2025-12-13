package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
import java.time.Duration;


public class BaseCommonMethodsClass extends PageInitializer {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void launchBrowser() throws Exception {
        String browser = ConfigReader.read("browser");

        if (browser.equals("chrome")) {
            //driver.set(new ChromeDriver());    //using threadLocal ".set" when not performing crossBrowser Testing with selenium gird
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            //chromeOptions.addArguments("--start-maximized");
            //chromeOptions.addArguments("--disable-headless");
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions)); //Selenium Grid (where tests connect)
        } else if (browser.equals("edge")) {
            // driver.set(new EdgeDriver());
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            //edgeOptions.addArguments("--start-maximized");
            // edgeOptions.addArguments("--disable-headless");
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), edgeOptions));
        } else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), options));
        }
        driver.get().get("http://localhost:3000/"); //Mount Sinai website( APP that im testing)
        Log.info("Website opened successfully");
        Thread.sleep(2000);
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get().manage().window().maximize();
        initializePageObject();
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.get().quit();
            Log.info("Closing website ");
        }
    }

    public static void selectFromDropDown(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void selectFromDropDown(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static void selectFromDropDown(String value, WebElement element) {
        Select select = new Select(element);
        select.selectByValue(value);
    }


}
