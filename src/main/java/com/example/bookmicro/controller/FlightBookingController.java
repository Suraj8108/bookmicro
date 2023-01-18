package com.example.bookmicro.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.dto.FlightDetailsDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.BookableFlightsException;
import com.example.bookmicro.service.FlightBookingService;


@RestController
@RequestMapping("/flightBooking")
public class FlightBookingController {

	
	
	@Autowired
	FlightBookingService fbs;
	
	@GetMapping("/getBookableFlights")
	public ResponseEntity<List<FlightBooking>> getBookableFlights() {
		
		return fbs.getBookableFlights();
	}
	
	@PostMapping("/UpdateFlight")
	public FlightBooking updateFlight(@RequestBody FlightBooking fb) throws BookableFlightsException {
		return fbs.updateBookableFlight(fb);
		
	}
	
	@GetMapping("/{id}/getAllBookings")
	public Set<Booking> getBookings( @PathVariable("id") int id){
		return fbs.getBookingByFlightBookingId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteFlightBookingById(@PathVariable("id") int id) {
		return fbs.deleteBookableFlightById(id);
	}     
	
	@GetMapping("/getSearchFlights")
	public ResponseEntity<List<FlightDetailsDto>> getSearchFlights() {
		
		return fbs.getSearchFlights();
		
	}
	
	//THis gets all the flight that has departure Airport as given in path Variable
	@GetMapping("/getSearchFlightsByDeparture/{departureAirport}")
	public ResponseEntity<Set<String>> getSearchFlightsDeparture(@PathVariable String departureAirport) {
		
		return new ResponseEntity(fbs.getSearchFlightByDepartureAirport(departureAirport), HttpStatus.OK);
		
	}
	
	
	//THis gets all the flight that has Arrival Airport as given in path Variable
	@GetMapping("/getSearchFlightsByArrival/{arrivalAirport}")
	public ResponseEntity<Set<String>> getSearchFlightsArrival(@PathVariable String arrivalAirport) {
		
		return new ResponseEntity(fbs.getSearchFlightByArrivalAirport(arrivalAirport), HttpStatus.OK);
		
	}
	
} 
