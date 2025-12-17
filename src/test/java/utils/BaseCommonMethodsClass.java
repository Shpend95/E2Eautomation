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

    /**
     * Launches browser based on environment configuration passed from Jenkins/Maven
     * Supports: local execution, Docker Grid execution
     *
     * @throws Exception
     */
    public static void launchBrowser() throws Exception {

        // Read configuration from Maven system properties (passed from Jenkins)
        String browser = ConfigReader.read("browser");        // "chrome", "edge", "firefox"
        String environment = ConfigReader.read("environment"); // "local", "grid"
        String appUrl = ConfigReader.read("appUrl");          // Application URL to navigate
        String seleniumHub = ConfigReader.read("seleniumHub"); // Grid hub URL (only for grid)

        // Log configuration for debugging
        Log.info("=================================================");
        Log.info("Browser Configuration:");
        Log.info("Browser: " + browser);
        Log.info("Environment: " + environment);
        Log.info("App URL: " + appUrl);
        if (environment.equalsIgnoreCase("grid")) {
            Log.info("Selenium Hub: " + seleniumHub);
        }
        Log.info("=================================================");

        // Initialize browser based on type and environment
        switch (browser.toLowerCase()) {
            case "chrome":
                if (environment.equalsIgnoreCase("grid")) {
                    // Remote Chrome via Selenium Grid
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless"); // Run headless on cloud
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    driver = new RemoteWebDriver(new URL(seleniumHub), chromeOptions);
                } else {
                    // Local ChromeDriver (works for single-thread or ThreadLocal for parallel)
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // chromeOptions.addArguments("--headless"); // Optional for local
                    driver = new ChromeDriver(chromeOptions);
                }
                break;

            case "edge":
                if (environment.equalsIgnoreCase("grid")) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(seleniumHub), edgeOptions);
                } else {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                }
                break;

            case "firefox":
                if (environment.equalsIgnoreCase("grid")) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(seleniumHub), firefoxOptions);
                } else {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                }
                break;

            default:
                throw new Exception("Unsupported browser: " + browser);
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
