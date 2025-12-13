package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class LoginPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//input[@name='username']")
    public WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passWord;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[.//span[text()='Dashboard']]")
    public WebElement dashboardBtn;


    public LoginPage(){
        PageFactory.initElements(driver.get(),this);
    }
}
