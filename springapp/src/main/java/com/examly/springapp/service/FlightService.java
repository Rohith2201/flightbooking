package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Flight;

public interface FlightService {
    Flight addFlight(Flight flight);

    Flight updateFlight(Long flightId, Flight flight);

    List<Flight> getAllFlights();

    Flight getFlightById(Long flightId);

    boolean deleteFlightById(Long flightId);
}
