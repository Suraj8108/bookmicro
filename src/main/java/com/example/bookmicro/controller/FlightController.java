package com.example.bookmicro.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.dao.FlightDAO;
import com.example.bookmicro.dao.RouteDAO;
import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.Route;
import com.example.bookmicro.enums.Days;
import com.example.bookmicro.exceptions.FlightException;
import com.example.bookmicro.service.FlightService;

@RestController
@EnableScheduling
@Transactional
@RequestMapping("/flight")
public class FlightController {
	
	
	@Autowired
	FlightService fs;
	
	
	
	@GetMapping("/getAllflights")
	public ResponseEntity<List<Flight>> getFlights() throws FlightException {
		
		return fs.getFlights();
	}
	
	@PostMapping("/addFlight")
	public String addFlight(@RequestBody Flight flight) throws FlightException {
		System.out.println("Hi");
		fs.addFlight(flight);
		return "Successfully Added";
	}
	
	
	@PutMapping("/updateFlight")
	public Flight updateFlight(@RequestBody Flight updtFlight) throws FlightException {
		
		return fs.updateFlight(updtFlight);
		
	}
	
	
	

	
	@Scheduled(fixedDelay = 60000)
	public void flightBookingInitalizer() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime afterYear = now.plusYears(1);
		
		fs.startFlightBooking(afterYear);
		
	}
	
	@GetMapping("/previousBookFlights")
	public String previouseBookFlights() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime afterYear = now.plusYears(1);
		
		while( !now.equals(afterYear)) {
			fs.startFlightBooking(now);
			now = now.plusDays(1);
			
		}
		
		return "Successfully Inserterd Previous Years data";
	}

}
