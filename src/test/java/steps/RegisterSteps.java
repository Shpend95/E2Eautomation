package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

public class RegisterSteps extends BaseCommonMethodsClass {

    public static String patientName="Jone Bones ";


    @Given("the user is on the mount sinai hospital website")
    public void the_user_is_on_the_mount_sinai_hospital_website() throws InterruptedException {
        launchBrowser();
    }

    @When("user clicks on register now button")
    public void user_clicks_on_register_now_button() {
        registerPage.registerNowBtn.click();
    }

    @When("user fills his information")
    public void user_fills_his_information() throws InterruptedException {
        registerPage.fullPatientName.sendKeys(patientName);
        registerPage.patientEmail.sendKeys("JJBones@gmail.com");
        registerPage.patientPhoneNumber.sendKeys("121-598-6523");
        registerPage.calendarBtn.click();
        Thread.sleep(2000);
        driver.findElement(By.id("patient-dob")).sendKeys("08181990");
        registerPage.patientAddress.sendKeys("098 Bronx St,apt 4 New york, New York, 19802");
    }

    @When("user clicks on Register Patient button")
    public void user_clicks_on_register_patient_button() {
        registerPage.registerSaveBtn.click();
    }

    @Then("user should see a successful message saying {string} plus the name of employee registered")
    public void user_should_see_a_successful_message_saying_plus_the_name_of_employee_registered(String welcome) throws InterruptedException {
        Thread.sleep(2000);

        String actualMessage=registerPage.welcomeMessage.getText();
        String expectedMessage=welcome+ ", " +patientName;

        Assert.assertEquals(actualMessage,expectedMessage);

        }
    }

