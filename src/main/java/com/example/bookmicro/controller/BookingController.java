package com.example.bookmicro.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.BookingDAO;
import com.example.bookmicro.dao.PassengerDAO;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.Passenger;
import com.example.bookmicro.entity.Payment;
import com.example.bookmicro.exceptions.BookingException;
import com.example.bookmicro.service.BookingService;


@RestController
@RequestMapping("/book")
public class BookingController {
	
	@Autowired
	BookingService bs;
	
	@GetMapping("/getBook")
	public ResponseEntity<List<Booking>> getBooking() throws BookingException{
		
		return bs.getAllBookings();
	}
	
	@PostMapping("/bookFlight")
	public ResponseEntity<Booking> bookFlight(@RequestBody Booking booking) throws BookingException {
		
	return bs.bookFlight(booking);
		
	}
	
	@GetMapping("/get/{id}/passengers")
	public List<Passenger> getPassengers(@PathVariable("id") int id) throws BookingException {
		return bs.getBookingPassengers(id);
	}
	
	@GetMapping("/get/{id}/paymentDetails")
	public Payment getPaymentDetails(@PathVariable("id") int id)  throws BookingException {
		return bs.getBookingPayment(id);
	}
	
	@PutMapping("/set/{id}/paymentDetails")
	public Payment setPaymentDetails(@PathVariable("id") int id,@RequestBody Payment payment)  throws BookingException{
		return bs.setBookingPayment(payment, id);
		
	}
	
	@GetMapping("/get/{id}/fligthDetails")
	public FlightBooking getFlightBookingOfBooking(int id) throws BookingException {
		return bs.getFlightBookingOfBooking(id);
		
	}
	
	@PostMapping("/getByPnr")
	public Booking getBookingByPnr(@RequestParam String pnr) throws BookingException{
		return bs.getBookingByPnr(pnr);
		
	}
	
	@GetMapping("/getFlightByBookId/{bookingId}")
	public FlightBooking getFlightDetailsByBookingId(@PathVariable int bookingId) {
		Booking booking = bs.getBookingById(bookingId);
		return booking.getFlightBooking();
	}
	
	@GetMapping("/getAllPassenger")
	public List<Passenger> getAllPassengers(){
		return bs.getAllPassenger();
	}
	
	
	
}
