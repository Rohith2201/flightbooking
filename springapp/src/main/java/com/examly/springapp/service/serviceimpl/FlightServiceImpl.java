package com.examly.springapp.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Flight;
import com.examly.springapp.repository.FlightRepo;
import com.examly.springapp.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    FlightRepo flightRepo;

    @Autowired
    public FlightServiceImpl(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    @Override
    public Flight getFlightById(Long flightId) {
        Optional<Flight> optFlight = flightRepo.findById(flightId);
        if (optFlight.isPresent()) {
            return optFlight.get();
        }
        return null;
    }

    @Override
    public Flight updateFlight(Long flightId, Flight flight) {
        Optional<Flight> optFlight = flightRepo.findById(flightId);
        if (optFlight.isPresent()) {
            Flight f = optFlight.get();
            f.setFlightNumber(flight.getFlightNumber());
            f.setAirline(flight.getAirline());
            f.setDepartureLocation(flight.getDepartureLocation());
            f.setArrivalLocation(flight.getArrivalLocation());
            f.setDepartureTime(flight.getDepartureTime());
            f.setArrivalTime(flight.getArrivalTime());
            f.setPrice(flight.getPrice());
            f.setTotalSeats(flight.getTotalSeats());
            flightRepo.save(f);
            return f;
        }
        return null;
    }

    @Override
    public boolean deleteFlightById(Long flightId) {
        Optional<Flight> optFlight = flightRepo.findById(flightId);
        if (optFlight.isPresent()) {
            flightRepo.deleteById(flightId);
            return true;
        }
        return false;
    }

}

