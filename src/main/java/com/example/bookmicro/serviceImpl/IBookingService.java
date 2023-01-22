package com.example.bookmicro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.BookingDAO;
import com.example.bookmicro.dao.PassengerDAO;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.Passenger;
import com.example.bookmicro.entity.Payment;
import com.example.bookmicro.exceptions.BookingException;
import com.example.bookmicro.service.BookingService;


@Service
public class IBookingService implements BookingService{
	
	@Autowired
	BookingDAO bookingDao;
	
	@Autowired
	PassengerDAO passengerDao;

	@Override
	public ResponseEntity<List<Booking>>getAllBookings() throws BookingException {
		// TODO Auto-generated method stub
		List<Booking> bookings = bookingDao.findAll();
		if(bookings.isEmpty()) {
			throw new BookingException("No Booking Found");
		}
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
			}

	
	@Override
	public ResponseEntity<Booking> bookFlight(Booking booking) throws BookingException {
		// TODO Auto-generated method stub
		
		List<Passenger> passengers = booking.getPassenger();
		if(passengers.size() <= 0) {
			throw new BookingException("Invalid Booking");
		}
		
//		bookingDao.save(booking);
//		
		for(Passenger passesger : passengers ) {
			passesger.setBooking(booking);
		}
		
		Booking bookDetails = bookingDao.save(booking);

		
		return new ResponseEntity<Booking>(bookDetails, HttpStatus.ACCEPTED);
	
	}

	@Override
	public List<Passenger> getBookingPassengers(int id) throws BookingException {
		// TODO Auto-generated method stub
		Booking b = bookingDao.findById(id).get();
		if(b==null) {
			throw new BookingException("No booking avalaible");
		}
		List<Passenger> passengers = b.getPassenger();
				if(passengers.isEmpty()) {
					throw new BookingException("No Passanger present in booking");
				}
		
		return passengers;
	}
	

//	@Override
//	public String setBookingPassengers(int id) throws BookingException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	@Override
	public Payment getBookingPayment(int id) throws BookingException {
		// TODO Auto-generated method stub
		Booking b = bookingDao.findById(id).get();
		if(b==null) {
			throw new BookingException("No booking avalaible");
		}
		Payment p = b.getPayment();
		if(p==null) {
			throw new BookingException("No Payment information avalaible");
		}
		
		return p;
	}

	
	@Override
	public Payment setBookingPayment(Payment payment,int id) throws BookingException {
		// TODO Auto-generated method stub
		Booking b = bookingDao.findById(id).get();
		if(b==null) {
			throw new BookingException("No booking avalaible");
		}
		
		b.getPayment().setPaymentNo(payment.getPaymentNo());
		b.getPayment().setPaymentId(payment.getPaymentId());
		b.getPayment().setPaymentOrderId(payment.getPaymentOrderId());
		b.getPayment().setRazorpaySignature(payment.getRazorpaySignature());
		b.getPayment().setPaymentStatus(payment.getPaymentStatus());
		b.getPayment().setPaymentDate(payment.getPaymentDate());
		b.getPayment().setAmount(payment.getAmount());
		
		
		bookingDao.save(b);
		
		return null;
	}

	@Override
	public FlightBooking getFlightBookingOfBooking(int id) throws BookingException {
		// TODO Auto-generated method stub
		
		Booking b = bookingDao.findById(id).get();
		if(b==null) {
			throw new BookingException("No booking avalaible");
		}
		
		
		return b.getFlightBooking();
		
		
	}

//	@Override
//	public Booking setFlightBookingOfBooking(int id) throws BookingException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Booking getBookingByPnr(String pnr) throws BookingException {
		// TODO Auto-generated method stub
		Booking b = bookingDao.findByPnrNo(pnr);//
		if(b==null) {
			throw new BookingException("No booking avalaible");
		}
		
		return b;
	}


	@Override
	public Booking getBookingById(int id) {
		// TODO Auto-generated method stub
		
		return bookingDao.findById(id).get();
	}

}
