package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.Auth;
import com.restfulbooker.api.payloads.AuthResponse;
import com.restfulbooker.api.payloads.BookingResponse;
import com.restfulbooker.api.payloads.lombok.Booking;
import com.restfulbooker.api.payloads.lombok.BookingDates;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiStatusCodeTest {

    @Epic("Booking API")
    @Feature("Check Status code")
    @Story("Positive get bookings")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение букингов с валидными данными")
    public void getBookingShouldReturn200() {
        Response response = BookingApi.getBookings();

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Check Status code")
    @Story("Positive get booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение букинга с валидными данными")
    public void getBookingIdShouldReturn200() {
        Response response = BookingApi.getBooking(1, "application/json");

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Check Status code")
    @Story("Negative get booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение букинга с невалидными данными")
    public void getBookingIdWithBadAcceptShouldReturn418() {
        Response response = BookingApi.getBooking(1, "text/plain");

        Assertions.assertEquals(418, response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Check Status code")
    @Story("Positive create booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Создание букинга с валидными данными")
    public void postBookingReturns200() {
        BookingDates dates = new com.restfulbooker.api.payloads.lombok.BookingDates();
        Booking payload = com.restfulbooker.api.payloads.lombok.Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        Response response = BookingApi.postBooking(payload);

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Check Status code")
    @Story("Positive delete booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Удаление букинга с валидными данными")
    public void deleteBookingReturns201() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth.Builder()
                .setUsername("admin")
                .setPassword("password123")
                .build();

        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(
                createdBookingResponse.getBookingid(),
                authResponse.getToken());

        Assertions.assertEquals(201, deleteResponse.getStatusCode());
    }
}
