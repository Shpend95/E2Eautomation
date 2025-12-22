package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;


public class BaseCommonMethodsClass extends PageInitializer {
    protected static WebDriverWait wait;
    protected static WebDriver driver;

    public static void launchBrowser() throws Exception {
        String browser = ConfigReader.read("browser");
        String environment = ConfigReader.read("environment");
        boolean headless = Boolean.parseBoolean(ConfigReader.read("headless"));

        String appUrl = null;     // Where the app is running, what im testing
        String remoteUrl = null;   //Where im testing

        switch (environment.toLowerCase()) {
            case "local":
                appUrl = ConfigReader.read("appUrl");
                break;
            case "grid":
                appUrl = ConfigReader.read("gridAppUrl");
                remoteUrl = ConfigReader.read("seleniumHub");
                break;
            case "docker-grid":
                appUrl = ConfigReader.read("dockerGridAppUrl");
                remoteUrl = ConfigReader.read("dockerSeleniumHub");
                break;
            case "cloud":
                appUrl = ConfigReader.read("cloudAppUrl");
                remoteUrl = ConfigReader.read("cloudSeleniumHub");
                break;
            default:
                throw new IllegalArgumentException("Invalid environment: " + environment);
        }

        Log.info("Browser Configuration:");
        Log.info("Browser: " + browser);
        Log.info("Environment: " + environment);
        Log.info("App URL: " + appUrl);
        Log.info("Headless: " + headless);
        if (remoteUrl != null) {
            Log.info("Selenium Hub: " + remoteUrl);
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                if (environment.equalsIgnoreCase("local")) {
                    // LOCAL: Run Chrome on your machine
                    ChromeOptions localOptions = new ChromeOptions();
                    if (headless) {
                        localOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(localOptions);
                } else {
                    // GRID/DOCKER-GRID/CLOUD: Run Chrome remotely
                    ChromeOptions remoteOptions = new ChromeOptions();
                    remoteOptions.addArguments("--headless");
                    remoteOptions.addArguments("--no-sandbox");
                    remoteOptions.addArguments("--disable-dev-shm-usage");
                    driver = new RemoteWebDriver(new URL(remoteUrl), remoteOptions);
                }
                break;

            case "edge":
                if (environment.equalsIgnoreCase("local")) {
                    EdgeOptions localOptions = new EdgeOptions();
                    if (headless) {
                        localOptions.addArguments("--headless");
                    }
                    driver = new EdgeDriver(localOptions);
                } else {
                    EdgeOptions remoteOptions = new EdgeOptions();
                    remoteOptions.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(remoteUrl), remoteOptions);
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Implicit wait
        Log.info("Navigating to: " + appUrl);
        driver.get(appUrl);
        //wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")); //Waits until the entire page is fully loaded (all HTML, CSS, JS, images)
        initializePageObject();
        Log.info("Browser launched successfully!");
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            Log.info("Closing browser");
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
