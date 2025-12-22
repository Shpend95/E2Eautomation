package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

import java.time.Duration;

public class LoginSteps extends BaseCommonMethodsClass {
    String username="";

    @Given("patient is navigated to the website")
    public void patient_is_navigated_to_the_website() throws Exception {
       launchBrowser();

    }
    @When("patient enters valid email and password")
    public void patient_enters_valid_email_and_password() throws InterruptedException {
        Thread.sleep(1000);
        loginPage.userName.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']")));
        loginPage.userName.sendKeys("patient2");
        username=loginPage.userName.getText();
        loginPage.passWord.clear();
        loginPage.passWord.sendKeys("patient123");

    }
    @When("patient clicks on login button")
    public void patient_clicks_on_login_button() throws InterruptedException {
        loginPage.signBtn.click();

    }
    @Then("patient is successfully logged in")
    public void patient_is_successfully_logged_in() {
        String actualMessage=loginPage.welcomeMessage.getText();
        String expectedMessage=actualMessage + "" +username;
        Assert.assertEquals(actualMessage,expectedMessage);

        driver.close();
    }

}

