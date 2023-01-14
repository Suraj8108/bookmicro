package com.example.bookmicro.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.exceptions.FlightException;

public interface FlightService {
	
	
	public void startFlightBooking(LocalDateTime afterYear) ;
	public void addFlight(Flight flight) throws FlightException;
	public void deleteFlight(Flight flight);
	public Flight updateFlight(Flight updtFlight)throws FlightException;
	public ResponseEntity<List<Flight>> getFlights() throws FlightException;
	
}
