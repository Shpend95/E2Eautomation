package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseCommonMethodsClass;

public class AddEmployeeSteps extends BaseCommonMethodsClass {
    @Given("user has navigated to the website")
    public void user_has_navigated_to_the_website() throws InterruptedException {
        launchBrowser();
        Thread.sleep(3000);

    }
    @Given("the user is successfully logged in")
    public void the_user_is_successfully_logged_in() throws InterruptedException {
        WebElement userName= driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passWord= driver.findElement(By.xpath("//input[@name='password']"));
        userName.sendKeys("hrm_user");
        passWord.sendKeys("Hrm_user@123");
        WebElement clickBtn= driver.findElement(By.xpath("//button[@type='submit']"));
        clickBtn.click();
        Thread.sleep(3000);
    }
    @When("user clicks on PIM")
    public void user_clicks_on_pim() {
        addEmployeePage.pimBtn.click();

    }
    @When("user clicks on ADD button")
    public void user_clicks_on_add_button() {
        addEmployeePage.addBtn.click();
    }
    @When("user enters username, middlename,password")
    public void user_enters_username_middlename_password() {
        addEmployeePage.firstName.sendKeys("Poland");
        addEmployeePage.middleName.sendKeys("Water");
        addEmployeePage.lastName.sendKeys("Spring");
    }
    @When("user clicks on SAVE button")
    public void user_clicks_on_save_button() {
        addEmployeePage.saveBtn.click();
    }
    @Then("user should see an confirmation message saying {string}")
    public void user_should_see_an_confirmation_message_saying(String string) {
        System.out.println("you have successfully added an employee, BRAVO !!");

        driver.close();
    }

}
