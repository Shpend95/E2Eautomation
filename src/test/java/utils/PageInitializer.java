package utils;

import pages.AppointmentPage;
import pages.LoginPage;
import pages.RegisterPage;

public class PageInitializer {


    public static LoginPage loginPage;
    public static RegisterPage registerPage;
    public static AppointmentPage appointmentPage;


    public static void initializePageObject() {
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        appointmentPage = new AppointmentPage();

    }
}