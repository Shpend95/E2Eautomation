package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseCommonMethodsClass;

public class AppointmentSteps extends BaseCommonMethodsClass {

    @Given("the user is on dashboard page")
    public void the_user_is_on_dashboard_page() throws Exception {
        launchBrowser();

    }

    @When("user clicks on  the book appointment button")
    public void user_clicks_on_the_book_appointment_button() {
        wait.until(ExpectedConditions.elementToBeClickable(appointmentPage.bookAppBtn)).click();
    }

    @When("user selects patient,doctor,date,time")
    public void user_selects_patient_doctor_date_time() throws InterruptedException {
        appointmentPage.selectPatient.click();
        selectFromDropDown("3", appointmentPage.selectPatient);
        appointmentPage.selectDoctor.click();
        selectFromDropDown(appointmentPage.selectDoctor, "Dr. Emily Rodriguez - Pediatrics");
        appointmentPage.appointmentDate.click();
        Thread.sleep(1000);
        driver.findElement(By.id("appointment-date")).sendKeys("12222026");
        //driver.get().findElement(By.id("appointment-date")).sendKeys("12222026"); get is used when using threadlocal driver
        appointmentPage.appointmentTime.click();
        Thread.sleep(1000);
        driver.findElement(By.id("appointment-time")).sendKeys("1145PM");

    }

    @When("user adds note")
    public void user_adds_note() {
        appointmentPage.appointmentNotes.sendKeys("Blood test");
    }

    @When("user clicks on book appointment button")
    public void user_clicks_on_book_appointment_button() {
        appointmentPage.submitAppointment.click();
    }

    @Then("user should see a successful message that appointment has been booked")
    public void user_should_see_a_successful_message_that_appointment_has_been_booked() {
        String actualMessage = appointmentPage.appointmentSuccessfully.getText();
        if (actualMessage.contains("Appointment booked successfully!")) {
            System.out.println("Appointment is scheduled , Please don't forget to bring your ID");
        }
    }

}
