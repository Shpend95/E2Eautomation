package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;
import utils.Log;

import java.time.Duration;

public class LoginSteps extends BaseCommonMethodsClass {
    String username="";


    @Given("patient is navigated to the website")
    public void patient_is_navigated_to_the_website() throws Exception {
      launchBrowser();

    }
    @When("patient enters valid email and password")
    public void patient_enters_valid_email_and_password() throws InterruptedException {
        loginPage.userName.sendKeys("hrm_user");
        username=loginPage.userName.getText();
        loginPage.passWord.clear();
        loginPage.passWord.sendKeys("Hrm_user@123");

    }
    @When("patient clicks on login button")
    public void patient_clicks_on_login_button() throws InterruptedException {
        loginPage.signBtn.click();
        Log.info("logged in successfully");



    }
    @Then("patient is successfully logged in")
    public void patient_is_successfully_logged_in() {
        WebElement message=driver.findElement(By.xpath("//header/div[1]/div[1]/span/h6"));
        String actualMessage=message.getText();
        String expectedMessage=actualMessage;
        Assert.assertEquals(actualMessage,expectedMessage);

        driver.close();
    }

}

