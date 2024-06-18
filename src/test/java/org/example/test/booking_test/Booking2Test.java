package org.example.test.booking_test;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.asserts.ApiAsserts;
import org.example.model.booking.Booking;
import org.example.test.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.example.utils.EntityManager.generateBooking;

@Slf4j
public class Booking2Test extends BaseTest {
    Booking expectedBooking;

    @BeforeClass(alwaysRun = true)
    void beforeClass() {
        expectedBooking = generateBooking();
        bookingController.createBooking(expectedBooking);
        ApiAsserts.assertThat(bookingController.getResponse()).isCorrectResponseCode(200);
    }

    @Test(description = "Фильтрация списка ID с созданными бронированиями (запрос GetBookingIds) на основе созданного бронирования. Проверка фильтрации по имени")
    void testFiltersIdsByName() {
        String[] ids = bookingController.getBookingIdsByName(expectedBooking.getFirstName(), expectedBooking.getLastName());
        ApiAsserts.assertThat(bookingController.getResponse()).isCorrectResponseCode(200);

        Assertions.assertThat(ids)
                .withFailMessage("In received id's not contains expected %s", expectedBooking.getId())
                .contains(expectedBooking.getId());
        log.info("In filtered id's contains expected (created booking)");
        log.info("Actual {}, Expected {}", Arrays.toString(ids), expectedBooking.getId());
    }

    @Test(description = "Фильтрация списка ID с созданными бронированиями (запрос GetBookingIds) на основе созданного бронирования. Проверка фильтрации по дате заезда/вызда")
    void testFiltersIdsByCheckinOut() {
        String[] ids = bookingController.getBookingIdsByCheckinCheckoutDates(expectedBooking.getBookingDates());
        ApiAsserts.assertThat(bookingController.getResponse()).isCorrectResponseCode(200);

        Assertions.assertThat(ids)
                .withFailMessage("In received id's not contains expected %s", expectedBooking.getId())
                .contains(expectedBooking.getId());
        log.info("In filtered id's contains expected (created booking)");
        log.info("Actual {}, Expected {}", Arrays.toString(ids), expectedBooking.getId());
    }
}
