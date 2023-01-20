package com.example.bookmicro.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.dto.FlightDetailsDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.BookableFlightsException;
import com.example.bookmicro.service.FareService;
import com.example.bookmicro.service.FlightBookingService;

@Service
public class IFlightBookingService implements FlightBookingService {
	
	@Autowired
	FlightBookingDAO flightBookingDao;
	
	@Autowired
	FareService fs;

	@Override
	public ResponseEntity<List<FlightBooking>> getBookableFlights() throws BookableFlightsException {
		List<FlightBooking> flights = flightBookingDao.findAll();
		
		flights.stream().forEach((k) -> {
            int fareId = k.getFare().getFareId();
            k.getFare().setbFare( fs.getFare(fareId, 1, "business"));
            k.getFare().seteFare( fs.getFare(fareId, 1, "economy"));
//            flightBookingDao.save(k);
        });
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

	@Override
	public ResponseEntity<List<FlightDetailsDto>> getSearchFlights() {
		// TODO Auto-generated method stub
		List<FlightBooking> flights = flightBookingDao.findAll();
		List<FlightDetailsDto> flightDetails = new ArrayList<>();
		
		for(FlightBooking fbook : flights) {
			FlightDetailsDto fd = new FlightDetailsDto();
			
			fd.setFlightBookingId(fbook.getId());
			fd.setFlightNo(fbook.getFlight().getFlightNo());
			fd.setFlightWeekDays(fbook.getFlight().getFlightWeekDays());
			fd.setRoute(fbook.getFlight().getRoute());
			fd.setFare(fbook.getFare());
			fd.setDepartureDateTime(fbook.getDepartureDateTime());
			fd.setArrivalDateTime(fbook.getArrivalDateTime());
			fd.setTotalTime(fbook.getTotalTime());
			
			int ecoSeats = 150;
			int busSeats = 20;
			for(Booking booking : fbook.getBooking()) {
				if(booking.getSeatClass().equals("economy")) {
					ecoSeats -= booking.getPassenger().size();
				}
				else {
					busSeats -= booking.getPassenger().size();
				}
			}
			fd.setEcoSeatAvailable(ecoSeats);
			fd.setBusSeatAvailable(busSeats);
			
			if(ecoSeats >0 || busSeats > 0) {
				flightDetails.add(fd);
			}
			
		}
		//System.out.println(flights);
		
		return new ResponseEntity<>(flightDetails, HttpStatus.OK);
	}
	
	@Override
	public Set<String> getSearchFlightByDepartureAirport(String airportName) {
		// TODO Auto-generated method stub
		List<FlightBooking> flights = flightBookingDao.findAll();
		Set<String> arrivalAirport = new HashSet<>();
		
		for(FlightBooking fbook : flights) {
			String departureAirport = fbook.getFlight().getRoute().getDepartureAirport();
			if(departureAirport.toLowerCase().equals(airportName.toLowerCase())) {
				arrivalAirport.add(fbook.getFlight().getRoute().getArrivalAirport());
			}
		}
		return arrivalAirport;
	}

	@Override
	public Set<String> getSearchFlightByArrivalAirport(String airportName) {
		// TODO Auto-generated method stub
		List<FlightBooking> flights = flightBookingDao.findAll();
		Set<String> departureAirport = new HashSet<>();
		
		for(FlightBooking fbook : flights) {
			String arrivalAirport = fbook.getFlight().getRoute().getArrivalAirport();
			if(arrivalAirport.toLowerCase().equals(airportName.toLowerCase())) {
				departureAirport.add(fbook.getFlight().getRoute().getDepartureAirport());
			}
		}
		
		return departureAirport;
		
	}
	
	

}
