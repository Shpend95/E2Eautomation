package utils;

import pages.AddEmployeePage;
import pages.LoginPage;

public class PageInitializer {

    public static AddEmployeePage addEmployeePage;
    public static LoginPage loginPage;


    public static void initializePageObject() {
        addEmployeePage = new AddEmployeePage();
        loginPage = new LoginPage();
    }
}
