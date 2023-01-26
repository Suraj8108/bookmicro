package com.example.bookmicro.dto;

import java.util.List;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.UserDetail;

//Containes LoggedIn user flight Booked Details
public class UserBookingDto {

	private List<Booking> bookingDetails;
	private List<FlightBooking> flightBookings;
	private List<Flight> flightDetails;
	private List<Checkin> checkinDetails;
	private UserDetail userDetails;
	
	public UserBookingDto() {
		super();
	}

	

	public UserBookingDto(List<Booking> bookingDetails, List<FlightBooking> flightBookings, List<Flight> flightDetails,
			List<Checkin> checkinDetails, UserDetail userDetails) {
		super();
		this.bookingDetails = bookingDetails;
		this.flightBookings = flightBookings;
		this.flightDetails = flightDetails;
		this.checkinDetails = checkinDetails;
		this.userDetails = userDetails;
	}



	public List<Booking> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<Booking> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public List<FlightBooking> getFlightBookings() {
		return flightBookings;
	}

	public void setFlightBookings(List<FlightBooking> flightBookings) {
		this.flightBookings = flightBookings;
	}

	public List<Checkin> getCheckinDetails() {
		return checkinDetails;
	}

	public void setCheckinDetails(List<Checkin> checkinDetails) {
		this.checkinDetails = checkinDetails;
	}

	public UserDetail getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetail userDetails) {
		this.userDetails = userDetails;
	}



	public List<Flight> getFlightDetails() {
		return flightDetails;
	}



	public void setFlightDetails(List<Flight> flightDetails) {
		this.flightDetails = flightDetails;
	}
	
	
	
}

