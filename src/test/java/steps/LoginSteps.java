package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.BaseCommonMethodsClass;
import utils.Log;

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

        WebElement dashboardMessage=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        if(dashboardMessage.isDisplayed()){
            System.out.println("Congrats, your are logged in");
        }



    }

}

