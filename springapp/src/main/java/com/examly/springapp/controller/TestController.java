package com.examly.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Flight;
import com.examly.springapp.service.serviceimpl.FlightServiceImpl;

@RestController 
@RequestMapping("/api/test")

public class TestController {
    FlightServiceImpl flightService;

    TestController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/welcome")

    public String welcomemessage() {

        return "Welcome to the Flight Booking Application";
    }

    @GetMapping("/flights")
    public List<Flight> getAllDetails() {
        List<Flight> flights = new ArrayList<>();
        // flights.add(new Flight(null, "Ae32hhhg", "Air Asia", "Mumbai", "Hyderabad", "20:30", "08:10", 4500.50, 200));
        return flights;
    }

}

