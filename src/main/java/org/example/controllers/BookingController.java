package org.example.controllers;

import org.example.api.HttpStructure;
import org.example.model.booking.Booking;
import org.example.model.booking.BookingDates;
import org.example.utils.JsonUtils;

import java.util.Arrays;
import java.util.Map;

import static org.example.application.BookingApplication.BOOKING;

public class BookingController extends HttpStructure {
    public BookingController(String url) {
        super(url);
    }

    public void createBooking(Booking booking) {
        this.response = super.post(getEndpoint(BOOKING), booking.toJson());
        BookingResponseBody responseBody = JsonUtils.deserialize(this.response, BookingResponseBody.class);
        if (responseBody != null) {
            booking.setId(responseBody.getId());
        }
    }

    public Booking getBookingById(String id) {
        this.response = super.get(getEndpoint(BOOKING, id));
        return JsonUtils.deserialize(this.response, Booking.class);
    }

    public String[] getBookingIdsByName(String firstName, String lastName) {
        this.response = super.get(getEndpoint(
                BOOKING, formatParameter(
                        Map.of(
                                "firstname", firstName,
                                "lastname", lastName
                        ))
        ));
        BookingResponseBody[] responseBody = JsonUtils.deserialize(this.response, BookingResponseBody[].class);
        if (responseBody != null) {
            return Arrays.stream(responseBody).map(BookingResponseBody::getId).toArray(String[]::new);
        }
        return null;
    }

    public String[] getBookingIdsByCheckinCheckoutDates(BookingDates dates) {
        this.response = super.get(getEndpoint(
                BOOKING, formatParameter(
                        Map.of(
                                "checkin", dates.getCheckin(),
                                "checkout", dates.getCheckout()
                        ))
        ));
        BookingResponseBody[] responseBody = JsonUtils.deserialize(this.response, BookingResponseBody[].class);
        if (responseBody != null) {
            return Arrays.stream(responseBody).map(BookingResponseBody::getId).toArray(String[]::new);
        }
        return null;
    }

}
