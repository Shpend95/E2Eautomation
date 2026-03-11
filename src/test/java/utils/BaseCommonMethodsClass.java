package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseCommonMethodsClass extends PageInitializer {
    protected static WebDriverWait wait;
    protected static WebDriver driver;

    public static void launchBrowser() throws Exception {

        //public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
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
                appUrl = ConfigReader.read("websiteAPP");
                remoteUrl = ConfigReader.read("dockerSeleniumHub");
                break;
            case "cloud":
                appUrl = ConfigReader.read("cloudAppUrl");
                remoteUrl = ConfigReader.read("cloudSeleniumHub");
                break;
            default:
                throw new IllegalArgumentException("Invalid environment: " + environment);
        }

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
                    ChromeOptions localOptions = new ChromeOptions();
                    if (headless) {
                        localOptions.addArguments("--headless");
                    }
                    localOptions.addArguments("--no-sandbox");
                    localOptions.addArguments("--disable-dev-shm-usage");
                    driver = new ChromeDriver(localOptions);
                } else {
                    // GRID/DOCKER/CLOUD: Run Chrome remotely
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Log.info("Navigating to: " + appUrl);
        driver.get(appUrl);
        initializePageObject();
        Log.info("Browser launched successfully!");
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            Log.info("Closing browser");
        }
    }


    public static void selectFromDropDown(WebElement element, int index){
        Select select=new Select(element);
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
    public static void sendText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
    }

    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        return wait;
    }
    public static void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
    }


    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts=(TakesScreenshot) driver;
        byte[] picBytes=ts.getScreenshotAs(OutputType.BYTES); //
        File sourceFile=ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File
                    (Constants.SCREENSHOT_FILEPATH+fileName+
                            " "+ getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }



}
