package org.example.test.booking_test;

import org.example.asserts.ApiAsserts;
import org.example.model.booking.Booking;
import org.example.test.BaseTest;
import org.testng.annotations.Test;

import static org.example.utils.EntityManager.generateBooking;
import static org.testng.Assert.assertTrue;

public class BookingTest extends BaseTest {
    Booking expectedBooking;

    @Test(description = "Создание бронирования (запрос CreateBooking) и проверка того, что бронирование сохранено с верно указанными данными (запрос GetBooking)")
    void createBookingTest() {
        expectedBooking = generateBooking();
        bookingController.createBooking(expectedBooking);
        ApiAsserts.assertThat(bookingController.getResponse()).isCorrectResponseCode(200);

        Booking actualBooking = bookingController.getBookingById(expectedBooking.getId());
        ApiAsserts.assertThat(bookingController.getResponse()).isCorrectResponseCode(200);
        assertTrue(actualBooking.isEquals(expectedBooking), "Bookings is not equal");
    }

}
