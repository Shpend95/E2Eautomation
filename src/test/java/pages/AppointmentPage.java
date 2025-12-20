package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class AppointmentPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//button[contains(text(),'Appointments')]")
    public WebElement Appointments;

    @FindBy(xpath = "//select[@id='appointment-patient']")
    public WebElement selectPatient;

    @FindBy(xpath = "//select[@name='doctorId']")
    public WebElement selectDoctor;

    @FindBy(xpath = "//input[@name='appointmentDate']")
    public WebElement appointmentDate;

    @FindBy(xpath = "//input[@name='appointmentTime']")
    public WebElement appointmentTime;

    @FindBy(xpath = "//input[@name='notes']")
    public WebElement appointmentNotes;

    @FindBy(xpath = "//button[contains(text(),'Book Appointment')]")
    public WebElement bookAppointment;


    @FindBy(xpath = "//*[@data-testid='appointment-success']")
    public WebElement appointmentSuccessfully;

    public AppointmentPage(){
        PageFactory.initElements(driver,this);
    }
}
