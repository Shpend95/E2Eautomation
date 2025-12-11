package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;


public class AddEmployeePage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//span[contains(.,'PIM')]")
    public WebElement pimBtn;

    @FindBy(xpath = "//i[contains(@class,'bi-plus')]/parent::button")
    public WebElement addBtn;


    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    public WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastName;


    @FindBy(xpath = "//button[@type='submit' and contains(@class,'oxd-button--secondary')]")
    public WebElement saveBtn;


    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }

}
