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
    // public static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); //ThreadLocal<WebDriver> gives each test thread its own instance of WebDriver.

    protected static WebDriverWait wait;
    protected static WebDriver driver;

    public static void launchBrowser() throws Exception {
        String browser = ConfigReader.read("browser");        // "chrome", "edge", "firefox"
        String environment = ConfigReader.read("environment"); // "local", "grid" "cloud"
        boolean headless = Boolean.parseBoolean(ConfigReader.read("headless"));

        String appUrl = null;     // Where the app is running, what im testing
        String remoteUrl = null;   //Where im testing

        // Determine URLs based on environment
        switch (environment.toLowerCase()) {
            case "local":
                appUrl = ConfigReader.read("appUrl");
                break;
            case "grid":
                appUrl = ConfigReader.read("appUrl");
                remoteUrl = ConfigReader.read("seleniumHub");
                break;
            case "docker-grid":
                appUrl = ConfigReader.read("dockerGridUrl");
                remoteUrl = ConfigReader.read("dockerSeleniumHub");
                break;
            case "cloud":
                appUrl = ConfigReader.read("cloudUrl");
                remoteUrl = ConfigReader.read("cloudSeleniumHub");
                break;
            default:
                throw new IllegalArgumentException("Invalid environment: " + environment);
        }

        // Log configuration
        Log.info("=================================================");
        Log.info("Browser Configuration:");
        Log.info("Browser: " + browser);
        Log.info("Environment: " + environment);
        Log.info("App URL: " + appUrl);
        Log.info("Headless: " + headless);
        if (remoteUrl != null) {
            Log.info("Selenium Hub: " + remoteUrl);
        }
        Log.info("=================================================");

        // Initialize browser
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

            case "firefox":
                if (environment.equalsIgnoreCase("local")) {
                    FirefoxOptions localOptions = new FirefoxOptions();
                    if (headless) {
                        localOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(localOptions);
                } else {
                    FirefoxOptions remoteOptions = new FirefoxOptions();
                    remoteOptions.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(remoteUrl), remoteOptions);
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Common driver configuration
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait

        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to the application
        Log.info("Navigating to: " + appUrl);
        driver.get(appUrl);

        // Wait for page to load
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        // Initialize all page objects
        initializePageObject(); // Your existing method

        Log.info("Browser launched successfully!");
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            Log.info("Closing browser");
        }
    }

    // Other existing methods...


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
