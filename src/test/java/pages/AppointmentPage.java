package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class AppointmentPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//*[@data-testid='home-book-appointment-btn']")
    public WebElement bookAppBtn;

    @FindBy(xpath = "//select[@id='appointment-patient']")
    public WebElement selectPatient;

    @FindBy(xpath = "//select[@id='appointment-doctor']")
    public WebElement selectDoctor;

    @FindBy(xpath = "//*[@id='appointment-date']")
    public WebElement appointmentDate;

    @FindBy(xpath = "//*[@id='appointment-time']")
    public WebElement appointmentTime;

    @FindBy(xpath = "//*[@id='appointment-notes']")
    public WebElement appointmentNotes;

    @FindBy(xpath = "//*[@id='appointment-submit-btn']")
    public WebElement submitAppointment;


    @FindBy(xpath = "//*[@data-testid='appointment-success']")
    public WebElement appointmentSuccessfully;

    public AppointmentPage(){
        PageFactory.initElements(driver,this);
    }
}
