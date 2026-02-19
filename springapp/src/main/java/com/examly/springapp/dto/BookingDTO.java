package com.examly.springapp.dto;

import java.sql.Date;

public class BookingDTO {
    Long flightId;
    Long userId;
    Date bookingDate;
    int numberOfPassengers;
    String status;

    public BookingDTO() {
    }

    public BookingDTO(Long flightId, Long userId, Date bookingDate, int numberOfPassengers, String status) {
        this.flightId = flightId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.numberOfPassengers = numberOfPassengers;
        this.status = status;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
