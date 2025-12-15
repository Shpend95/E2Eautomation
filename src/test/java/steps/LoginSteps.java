package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

public class LoginSteps extends BaseCommonMethodsClass {
    @Given("patient is navigated to the website")
    public void patient_is_navigated_to_the_website() throws Exception {
       launchBrowser();
    }
    @When("patient enters valid email and password")
    public void patient_enters_valid_email_and_password() {
        loginPage.loginBtn.click();
        loginPage.userName.sendKeys("patient@test.com");
        loginPage.passWord.sendKeys("Patient123");
    }
    @When("patient clicks on login button")
    public void patient_clicks_on_login_button() {
        loginPage.signBtn.click();
    }
    @Then("patient is successfully logged in")
    public void patient_is_successfully_logged_in() {
        String userLogin=loginPage.userName.getText();
        String actualMessage=loginPage.welcomeMessage.getText();
        String expectedMessage=actualMessage + "" +userLogin;
        Assert.assertEquals(actualMessage,expectedMessage);
    }

}

