package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {

    public static WebDriver driver;
    public static WebDriverWait wait;

        @Given("user is navigated to the website")
        public void user_is_navigated_to_the_website() throws InterruptedException {
          driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://54.198.61.50/web/index.php/auth/login");
            Thread.sleep(3000);


        }
        @When("user enters valid username and password")
        public void user_enters_valid_username_and_password() {
            WebElement userName= driver.findElement(By.xpath("//input[@name='username']"));
            WebElement passWord= driver.findElement(By.xpath("//input[@name='password']"));
            userName.sendKeys("hrm_user");
            passWord.sendKeys("Hrm_user@123");
        }
        @When("user clicks on login button")
        public void user_clicks_on_login_button() {
            WebElement clickBtn= driver.findElement(By.xpath("//button[@type='submit']"));
            clickBtn.click();
        }
        @Then("user is successfully logged in")
        public void user_is_successfully_logged_in() {
            System.out.println("You are logged in");
        }
    }

