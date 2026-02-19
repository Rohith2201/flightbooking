package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Booking;

import jakarta.transaction.Transactional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

   List <Booking> findByUserId(Long userId);

   @Query("select b.flight.flightNumber, b.bookingDate, b.numberOfPassengers, b.status from Booking b where b.userId = :userId")
   List<Object[]> findBookingByUserId(Long userId);

   // @Query("select b.bookingId, u.userId, u.username, f.flightNumber, u.email, b.bookingDate, b.status from Booking b join User u on b.userId = u.userId join b.flight f")
   // List<Booking>findAllBookings();

   @Modifying
   @Transactional
   @Query("UPDATE Booking b SET b.status = :newStatus WHERE b.bookingId = :bookingId")
   int updateBookingStatus(@Param("newStatus") String newStatus, @Param("bookingId") Long bookingId);
   
}
