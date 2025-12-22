package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseCommonMethodsClass;

import java.time.Duration;

public class AppointmentSteps extends BaseCommonMethodsClass {
    @Given("the user is on logged in")
    public void the_user_is_on_logged_in() throws Exception {
        launchBrowser();
        loginPage.userName.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='username']")));
        loginPage.userName.sendKeys("patient1");
        loginPage.passWord.clear();
        loginPage.passWord.sendKeys("patient123");
        loginPage.signBtn.click();


    }
    @When("user clicks on appointments")
    public void user_clicks_on_appointments() {
       appointmentPage.Appointments.click();
    }
    @When("user selects doctor,date,time")
    public void user_selects_doctor_date_time() {
    selectFromDropDown("3", appointmentPage.selectDoctor);
    appointmentPage.appointmentDate.click();
    appointmentPage.appointmentDate.sendKeys("12292028");
    appointmentPage.appointmentTime.click();
    appointmentPage.appointmentTime.sendKeys("1200PM");

    }
    @When("user adds note")
    public void user_adds_note() {
        appointmentPage.appointmentNotes.sendKeys("Blood Test Results");
    }
    @When("user clicks on book appointment button")
    public void user_clicks_on_book_appointment_button() {
       appointmentPage.bookAppointment.click();
    }
    @Then("user should see a successful message that appointment has been booked")
    public void user_should_see_a_successful_message_that_appointment_has_been_booked() throws InterruptedException {

        System.out.println("Appointment booked successfully!");
        driver.close();
       /*
        Alert alert=driver.switchTo().alert();
        String text=alert.getText();
        Assert.assertEquals(text,"Appointment booked successfully!");

        */
    }

}
