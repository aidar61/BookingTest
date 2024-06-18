package org.example.test;

import org.example.controllers.BookingController;
import org.example.model.Auth;
import org.testng.annotations.BeforeSuite;

import static org.example.config.AppConfig.BASE_URL;
import static org.example.utils.EntityManager.generateAuth;

public class BaseTest {
    protected BookingController bookingController;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        bookingController = new BookingController(BASE_URL);
    }
}
