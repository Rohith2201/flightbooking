package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Flight;
import com.examly.springapp.service.serviceimpl.FlightServiceImpl;

@RestController
@RequestMapping("api/flights")
public class FlightController {
    FlightServiceImpl flightService;

    @Autowired
    public FlightController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.addFlight(flight);
        if (newFlight == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(newFlight);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        Flight newFlight = flightService.updateFlight(flightId, flight);
        if (newFlight == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(newFlight);

    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllDetails() {
        List<Flight> flights = flightService.getAllFlights();
        if (flights == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(200).body(flights);

    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getItemById(@PathVariable Long flightId) {
        Flight flight1 = flightService.getFlightById(flightId);
        if (flight1 == null)
            return ResponseEntity.status(500).build();
        return ResponseEntity.status(200).body(flight1);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Boolean> deleteItemById(@PathVariable Long flightId) {
        if (flightService.deleteFlightById(flightId)) {
            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(500).build();
    }

}
