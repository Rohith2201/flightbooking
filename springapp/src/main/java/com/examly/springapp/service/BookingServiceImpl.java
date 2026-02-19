package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.SeatsExceededException;
import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Flight;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.repository.FlightRepo;

@Service
public class BookingServiceImpl implements BookingService {
    BookingRepo bookingRepo;
    FlightRepo flightRepo;

    @Autowired
    BookingServiceImpl(BookingRepo bookingRepo, FlightRepo flightRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Flight opFlight = booking.getFlight();
        if (opFlight.getTotalSeats() < booking.getNumberOfPassengers()) {
            throw new SeatsExceededException("No available seats for this flight");
        }
        opFlight.setTotalSeats(opFlight.getTotalSeats() - booking.getNumberOfPassengers());
        opFlight.getBookings().add(booking);
        flightRepo.save(opFlight);
        
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
        // return bookingRepo.findAllBookings();
    }

    @Override
    public Booking getBookingById(Long id) {
        Optional<Booking> opt = bookingRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        Optional<Flight> opt = flightRepo.findById(id);
        if (opt.isPresent()) {
            Flight flight = opt.get();
            Booking book = bookingRepo.save(booking);
            flight.getBookings().add(book);
            flightRepo.save(flight);
            return book;
        }
        return null;
    }

    @Override
    public List<Booking> findByUserId(Long userId) {

        return bookingRepo.findByUserId(userId);
    }

    @Override
    public List<Object[]> findBookingByUserId(Long userId) {
        return bookingRepo.findBookingByUserId(userId);
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public int updateBookingStatus(String status, long bookingId) {
        return bookingRepo.updateBookingStatus(status, bookingId);
    }

}
