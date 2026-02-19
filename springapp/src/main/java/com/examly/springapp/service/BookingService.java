package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Booking;

public interface BookingService {
  Booking createBooking(Booking booking);

  Booking getBookingById(Long id);

  List <Booking> getAllBookings();

  List <Booking> findByUserId(Long userId);

  List<Object[]> findBookingByUserId(Long userId);

  List<Booking>findAllBookings();

  Booking updateBooking(Long id, Booking booking);

  int updateBookingStatus(String status, long bookingId);

}
