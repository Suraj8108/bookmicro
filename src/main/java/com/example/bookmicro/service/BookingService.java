package com.example.bookmicro.service;

import com.example.bookmicro.exceptions.BookingException;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.Passenger;
import com.example.bookmicro.entity.Payment;

public interface BookingService {  
	
	public ResponseEntity<List<Booking>> getAllBookings() throws BookingException;
	
	public ResponseEntity<Booking> bookFlight(Booking book) throws BookingException;
	
	public  List<Passenger>  getBookingPassengers(int id)throws BookingException;
	
//	public String  setBookingPassengers(int id)throws BookingException;
	
	public Payment getBookingPayment(int id) throws BookingException;
	
	public Payment setBookingPayment(Payment payment, int id) throws BookingException;
	
	public FlightBooking getFlightBookingOfBooking(int id) throws BookingException;
	
//	public Booking setFlightBookingOfBooking(int id ) throws BookingException;
	
	public Booking getBookingByPnr(String pnr) throws BookingException;

}
