package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

public class RegisterSteps extends BaseCommonMethodsClass {

    public static String patientName = "Loreal Pariz";
    @Given("the user is on the mount sinai hospital website")
    public void the_user_is_on_the_mount_sinai_hospital_website() throws Exception {
        launchBrowser();

    }

    @When("user clicks on register now button")
    public void user_clicks_on_register_now_button() {
        registerPage.registerNowBtn.click();
    }

    @When("user fills his information")
    public void user_fills_his_information() throws InterruptedException {
        registerPage.fullPatientName.sendKeys(patientName);
        registerPage.patientEmail.sendKeys("parisLoreal@gmail.com");
        registerPage.patientPhoneNumber.sendKeys("317-513-5458");
        registerPage.calendarBtn.click();
        Thread.sleep(1000);
        driver.findElement(By.id("patient-dob")).sendKeys("01121998");
        registerPage.patientAddress.sendKeys("123 Crescent Avenue , Paris France, 770521");
    }

    @When("user clicks on Register Patient button")
    public void user_clicks_on_register_patient_button() throws InterruptedException {
        registerPage.registerSaveBtn.click();
        Thread.sleep(1000);
    }

    @Then("user should see a successful message saying {string} plus the name of employee registered")
    public void user_should_see_a_successful_message_saying_plus_the_name_of_employee_registered(String welcome) throws InterruptedException {
        Thread.sleep(2000);
        String actualMessage = registerPage.welcomeMessage.getText();
        String expectedMessage = welcome + ", " + patientName;

        Assert.assertEquals(actualMessage, expectedMessage);

    }
}

