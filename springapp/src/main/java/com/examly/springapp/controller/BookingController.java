package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.BookingDTO;
import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Flight;
import com.examly.springapp.service.BookingServiceImpl;
import com.examly.springapp.service.serviceimpl.FlightServiceImpl;


@RestController
@RequestMapping("api/bookings")
public class BookingController {
    BookingServiceImpl bookingServiceImpl;
    FlightServiceImpl flightServiceImpl;

    @Autowired
    public BookingController(BookingServiceImpl bookingServiceImpl, FlightServiceImpl flightServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
        this.flightServiceImpl = flightServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        Long userId = bookingDTO.getUserId();
        long flightId = bookingDTO.getFlightId();

        Booking booking = new Booking();
        booking.setUserId(userId);

        Flight flight = flightServiceImpl.getFlightById(flightId);
    
        booking.setFlight(flight);
        booking.setNumberOfPassengers(bookingDTO.getNumberOfPassengers());
        booking.setStatus(bookingDTO.getStatus());
        booking.setBookingDate(bookingDTO.getBookingDate());

        Booking booking1 = bookingServiceImpl.createBooking(booking);
        // flight.getBookings().add(booking1);

        return ResponseEntity.status(200).body(booking1);
    }

    // this need to be corrected 
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> booking = bookingServiceImpl.findAllBookings();
        if (booking.isEmpty()) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(booking);

    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Integer> updateBooking(@PathVariable Long id, @PathVariable String status) {
        int result = bookingServiceImpl.updateBookingStatus(status, id);
        if (result == 0) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        Booking booking1 = bookingServiceImpl.getBookingById(id);
        if (booking1 == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(booking1);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Object[]>> findBookingByUserId(@PathVariable Long userId) {
        List<Object[]> booking1 = bookingServiceImpl.findBookingByUserId(userId);
        if (booking1.isEmpty()) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(booking1);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> findAllBookings() {
        List<Booking> booking1 = bookingServiceImpl.findAllBookings();
        if (booking1.isEmpty()) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(booking1);
    }

}
