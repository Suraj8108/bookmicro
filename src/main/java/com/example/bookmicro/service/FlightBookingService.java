package com.example.bookmicro.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.BookableFlightsException;

@Service
public interface FlightBookingService {
	
	public ResponseEntity<List<FlightBooking>> getBookableFlights() throws BookableFlightsException;
	
	public FlightBooking updateBookableFlight(FlightBooking fb) throws BookableFlightsException ;
	
	public Set<Booking> getBookingByFlightBookingId(int id) throws BookableFlightsException;
	
	public String deleteBookableFlightById(int id) throws BookableFlightsException;

}
