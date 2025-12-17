package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class LoginPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//a[@data-testid='nav-login']")
    public WebElement loginBtn;

    @FindBy(id = "login-email")
    public WebElement userName;

    @FindBy(id = "login-password")
    public WebElement passWord;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signBtn;

    @FindBy(xpath = " //p[contains(text(),'Welcome back, ')]")
    public WebElement welcomeMessage;
    //a[.//span[text()='Dashboard']]

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement rememberMeBtn;


    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
}
