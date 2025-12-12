package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class RegisterPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//a[@id='home-register-btn']")
    public WebElement registerNowBtn;

    @FindBy(xpath = "//*[@id='patient-name']")
    public WebElement fullPatientName;

    @FindBy(xpath = "//*[@id='patient-email']")
    public WebElement patientEmail;

    @FindBy(xpath = "//*[@id='patient-phone']")
    public WebElement patientPhoneNumber;

    @FindBy(xpath = "//*[@id='patient-dob']")
    public WebElement calendarBtn;

    @FindBy(xpath = "//*[@value=\"1995-08-18\"]")
    public WebElement patientDOB;

    @FindBy(xpath = "//*[@id='patient-address']")
    public WebElement patientAddress;

    @FindBy(xpath = "//*[@id='submit-btn' and text()='Register Patient']")
    public WebElement registerSaveBtn;

    @FindBy(xpath = "//p[@data-testid='dashboard-subtitle']")
    public WebElement welcomeMessage;



    public RegisterPage() {
        PageFactory.initElements(driver,this);
    }

}
