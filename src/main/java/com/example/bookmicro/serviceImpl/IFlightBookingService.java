package com.example.bookmicro.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.BookableFlightsException;
import com.example.bookmicro.service.FlightBookingService;

@Service
public class IFlightBookingService implements FlightBookingService {
	
	@Autowired
	FlightBookingDAO flightBookingDao;

	@Override
	public ResponseEntity<List<FlightBooking>> getBookableFlights() throws BookableFlightsException {
		List<FlightBooking> flights = flightBookingDao.findAll();
		
		System.out.println(flights);
		
		return new ResponseEntity<>(flights, HttpStatus.ACCEPTED);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public FlightBooking updateBookableFlight(FlightBooking fb) throws BookableFlightsException  {
		// TODO Auto-generated method stub
		int id = fb.getId();
		FlightBooking f = flightBookingDao.getById(id);
		
		if(f == null) 
		{throw new BookableFlightsException("No such flight avalaible");}
		
		f.setArrivalDateTime(fb.getArrivalDateTime());
		f.setDepartureDateTime(fb.getDepartureDateTime());
		f.setFlight(fb.getFlight());
		f.setFare(fb.getFare());
		f.setTotalTime(fb.getTotalTime());
		return flightBookingDao.save(f);
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public Set<Booking> getBookingByFlightBookingId(int id) throws BookableFlightsException {
		// TODO Auto-generated method stub
		FlightBooking f = flightBookingDao.getById(id);
		if(f == null) throw new BookableFlightsException("No such flight avalaible");
		
		Set<Booking> bookings = f.getBooking();
		if(bookings.isEmpty()) throw new BookableFlightsException("No such booking avalaible");
		
		return bookings;
	}

	
	@Override
	public String deleteBookableFlightById(int id) throws BookableFlightsException {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		FlightBooking f = flightBookingDao.getById(id);
		if(f == null) throw new BookableFlightsException("Invalid Id");
		flightBookingDao.deleteById(id);
		return "Bookable Flight Deleted";
	}

}
