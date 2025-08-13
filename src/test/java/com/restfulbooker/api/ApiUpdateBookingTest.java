package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.config.Config;
import com.restfulbooker.api.payloads.Auth;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.BookingResponse;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiUpdateBookingTest {

    @Test
    public void testCreateAndUpdateBooking() {
        // Создаём новое бронирование
        BookingDates dates = new BookingDates();
        Booking newBooking = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(newBooking).as(BookingResponse.class);
        int bookingId = createdBookingResponse.getBookingid();

        Booking updatedBooking = Booking.builder()
                .firstname("Bob")
                .lastname("Johnson")
                .totalprice(250)
                .depositpaid(false)
                .bookingdates(dates)
                .additionalneeds("Lunch")
                .build();

        Booking createdBookingResponse2 = BookingApi.putBooking(bookingId, updatedBooking, getAuthToken()).as(Booking.class);
        System.out.println(createdBookingResponse2);

        Booking responseGetBookingById = BookingApi.getBooking(bookingId, "application/json").as(Booking.class);
        assertEquals(updatedBooking, responseGetBookingById, "Received booking is not as expected");
    }

    private String getAuthToken() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        Auth auth = new Auth.Builder()
                .setUsername(username)
                .setPassword(password)
                .build();
        Response response = AuthApi.postAuth(auth);

        String actualToken = response.getBody().jsonPath().get("token").toString();
        return actualToken;
    }
}
