package org.example.utils;

import org.example.config.AppConfig;
import org.example.model.Auth;
import org.example.model.booking.Booking;
import org.example.model.booking.BookingDates;

import static org.example.utils.RandomUtils.*;

public class EntityManager {
    public static Auth generateAuth() {
        return Auth.builder()
                .username(AppConfig.get().username())
                .password(AppConfig.get().password())
                .build();
    }

    public static Booking generateBooking() {
        return Booking.builder()
                .firstName(generateName())
                .lastName(generateLastName())
                .totalPrice(generatePrice())
                .bookingDates(generateBookingDates())
                .depositPaid(true)
                .additionalNeeds(generateAdditionalNeeds())
                .build();
    }

    private static BookingDates generateBookingDates() {
        return BookingDates.builder()
                .checkin(DateUtils.getCurrentDate(3))
                .checkout(DateUtils.getCurrentDate(7))
                .build();
    }
}
