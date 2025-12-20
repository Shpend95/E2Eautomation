package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

public class LoginSteps extends BaseCommonMethodsClass {
    @Given("patient is navigated to the website")
    public void patient_is_navigated_to_the_website() throws Exception {
       launchBrowser();
       Thread.sleep(2000);
    }
    @When("patient enters valid email and password")
    public void patient_enters_valid_email_and_password() {
        loginPage.userName.clear();
        loginPage.userName.sendKeys("patient3");
        loginPage.passWord.clear();
        loginPage.passWord.sendKeys("patient123");
    }
    @When("patient clicks on login button")
    public void patient_clicks_on_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign in')]"))).click();

    }
    @Then("patient is successfully logged in")
    public void patient_is_successfully_logged_in() {
        String userLogin=loginPage.userName.getText();
        String actualMessage=loginPage.welcomeMessage.getText();
        String expectedMessage=actualMessage + "" +userLogin;
        Assert.assertEquals(actualMessage,expectedMessage);

        driver.close();
    }

}

