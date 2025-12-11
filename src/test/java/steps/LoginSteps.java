package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseCommonMethodsClass;
import utils.Log;

public class LoginSteps extends BaseCommonMethodsClass {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Given("user is navigated to the website")
    public void user_is_navigated_to_the_website() throws InterruptedException {
        launchBrowser();

    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        Log.info("Entering credentials");
        loginPage.userName.sendKeys("hrm_user");
        Log.debug("Username entered: hrm_user");  // Won't show (level=info in config)
        loginPage.passWord.sendKeys("Hrm_user@123");

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginBtn.click();
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        System.out.println("You are logged in");

        if (loginPage.dashboardBtn.isDisplayed()) {
            Log.info("Login successful");
        } else {
            Log.error("Login failed - logout button not found");
        }
    }
}

