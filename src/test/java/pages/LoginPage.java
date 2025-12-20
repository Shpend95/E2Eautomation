package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseCommonMethodsClass;

public class LoginPage extends BaseCommonMethodsClass {

    @FindBy(xpath = "//a[@data-testid='nav-login']")
    public WebElement loginBtn;

    @FindBy(name = "username")
    public WebElement userName;

    @FindBy(name = "password")
    public WebElement passWord;

    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    public WebElement signBtn;

    @FindBy(xpath = "//span[contains(text(),'Welcome')]")
    public WebElement welcomeMessage;
    //a[.//span[text()='Dashboard']]

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement rememberMeBtn;


    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
}
