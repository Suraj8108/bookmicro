package com.example.bookmicro.dto;

import java.util.List;
import java.util.Set;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.FlightBooking;

public class CheckinDetailsDto {

	private Checkin checkInDetails;
	
	private FlightBooking flightBooking;
	
	private Booking bookingDetails;
	
	private Set<String> seatsEconomyBooked; 
	
	private Set<String> seatsBussinessBooked;
	
	private Flight flight;

	public CheckinDetailsDto() {
		super();
	}

	



	public CheckinDetailsDto(Checkin checkInDetails, FlightBooking flightBooking, Booking bookingDetails,
			Set<String> seatsEconomyBooked, Set<String> seatsBussinessBooked, Flight flight) {
		super();
		this.checkInDetails = checkInDetails;
		this.flightBooking = flightBooking;
		this.bookingDetails = bookingDetails;
		this.seatsEconomyBooked = seatsEconomyBooked;
		this.seatsBussinessBooked = seatsBussinessBooked;
		this.flight = flight;
	}





	public FlightBooking getFlightBooking() {
		return flightBooking;
	}

	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}

	public Booking getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(Booking bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public Set<String> getSeatsEconomyBooked() {
		return seatsEconomyBooked;
	}

	public void setSeatsEconomyBooked(Set<String> seatsEconomyBooked) {
		this.seatsEconomyBooked = seatsEconomyBooked;
	}

	public Set<String> getSeatsBussinessBooked() {
		return seatsBussinessBooked;
	}

	public void setSeatsBussinessBooked(Set<String> seatsBussinessBooked) {
		this.seatsBussinessBooked = seatsBussinessBooked;
	}

	public Checkin getCheckInDetails() {
		return checkInDetails;
	}

	public void setCheckInDetails(Checkin checkInDetails) {
		this.checkInDetails = checkInDetails;
	}





	public Flight getFlight() {
		return flight;
	}





	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	
}
