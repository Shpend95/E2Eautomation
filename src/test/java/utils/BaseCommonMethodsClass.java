package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;


public class BaseCommonMethodsClass extends PageInitializer {
    protected static WebDriverWait wait;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); //ThreadLocal<WebDriver> gives each test thread its own instance of WebDriver.

    public static void launchBrowser() throws Exception {
        String browser = ConfigReader.read("browser");         // "chrome", "edge", "firefox"
        String environment = ConfigReader.read("environment"); // "local", "docker", "grid"

        // Initialize browser based on type and environment
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                if (environment.equalsIgnoreCase("grid")) {
                    // Remote Chrome via Selenium Grid
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless"); // runs in headless mode
                    // chromeOptions.addArguments("--start-maximized"); // optional
                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions));
                } else {
                    // Local ChromeDriver (works for single-thread or ThreadLocal for parallel)
                    driver.set(new ChromeDriver());
                }
            }

            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless"); // optional
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), edgeOptions));
            }

            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), firefoxOptions));
            }

            default -> throw new Exception("Unsupported browser: " + browser);
        }

        // Navigate to the website based on environment
        // When tests are running inside a Docker container
        if (environment.equalsIgnoreCase("docker")) {
            /*
            When tests are running inside a Docker container, and the application is running on my host machine,
            I cannot use localhost inside the container.Inside the container, localhost points to the container itself, not your host.
           Docker provides a special hostname: host.docker.internal
           If the app is running in the cloud, just use its public URL. No host.docker.internal needed.
             */
            driver.get().get("http://host.docker.internal:3000/");
        } else if (environment.equalsIgnoreCase("local")) {
            // Local machine
            driver.get().get("http://localhost:3000/");
        } else if (environment.equalsIgnoreCase("grid")) {
            // Selenium Grid
            driver.get().get("http://localhost:3000/");
        } else {
            throw new Exception("Unsupported environment: " + environment);
        }

        Log.info("Website opened successfully");
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Implicit wait
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));           // Explicit wait
        driver.get().manage().window().maximize();
        initializePageObject();     // Initialize all page objects
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
