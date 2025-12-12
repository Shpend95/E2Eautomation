package utils;

import pages.LoginPage;
import pages.RegisterPage;

public class PageInitializer {


    public static LoginPage loginPage;
    public static RegisterPage registerPage;


    public static void initializePageObject() {
        registerPage = new RegisterPage();
        loginPage = new LoginPage();

    }
}