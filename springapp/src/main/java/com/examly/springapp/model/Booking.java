package com.examly.springapp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Date bookingDate;
    private int numberOfPassengers;
    private String status;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "flightId")
    @JsonBackReference
    private Flight flight;

    public Booking() {
    }

    public Booking(Date bookingDate, int numberOfPassengers, String status, Flight flight) {
        this.bookingDate = bookingDate;
        this.numberOfPassengers = numberOfPassengers;
        this.status = status;
        this.flight = flight;
    }

    public Booking(Long bookingId, Date bookingDate, int numberOfPassengers, String status, Long userId, Flight flight) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.numberOfPassengers = numberOfPassengers;
        this.status = status;
        this.userId = userId;
        this.flight = flight;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
