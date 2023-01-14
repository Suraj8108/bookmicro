package com.example.bookmicro.serviceImpl;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.dao.FlightDAO;
import com.example.bookmicro.dao.RouteDAO;
import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.FlightException;
import com.example.bookmicro.service.FlightService;

@Service
public class IFlightService implements FlightService {

	
	@Autowired
	FlightDAO flightDao;
	
	@Autowired
	RouteDAO routeDao;
	
	@Autowired
	FlightBookingDAO flightBookingDao;
	
	
	
	
	
	
	@Override
	public void startFlightBooking(LocalDateTime afterYear)  {
		// TODO Auto-generated method stub
		
		String week = afterYear.getDayOfWeek().toString().substring(0, 3);
		System.out.println(week);
		List<Flight> result = flightDao.findFlightByDays(week);
		
		List<FlightBooking> bookFlight = flightBookingDao.findAll();
		
		HashMap<Integer, HashSet<OffsetDateTime>> mapBookFlight = new HashMap<>();
		
		bookFlight.forEach(  flightDetails -> {
			
			int flightId = flightDetails.getFlight().getFlightId();
			OffsetDateTime departure = flightDetails.getDepartureDateTime();
			if(mapBookFlight.containsKey(flightId)) {
				HashSet<OffsetDateTime> departures = mapBookFlight.get(flightId);
				departures.add(departure);
				mapBookFlight.put(flightId, departures);
			}
			else {
				HashSet<OffsetDateTime> departures = new HashSet<>();
				departures.add(departure);
				mapBookFlight.put(flightId, departures);
			}
			//mapBookFlight.put(flightId, departure);
			
		});
		//Save flight for bookings
		result.forEach( flight -> {
			
			
			OffsetTime departureTime = flight.getRoute().getDepartureTime();
			OffsetTime arrivalTime = flight.getRoute().getArrivalTime();
			
			OffsetDateTime flightDeparture = OffsetDateTime.of(afterYear.getYear(), afterYear.getMonthValue(), afterYear.getDayOfMonth(), 
					departureTime.getHour(), departureTime.getMinute(), departureTime.getSecond(),
					departureTime.getNano(), departureTime.getOffset()); 
			
			int flightId = flight.getFlightId();
			if(mapBookFlight.containsKey(flightId) && mapBookFlight.get(flightId).contains(flightDeparture)) {
				return ; //This just skips the iteration
				
			}
			System.out.println(mapBookFlight);
			System.out.println();
			FlightBooking booking = new FlightBooking();
			booking.setFlight(flight);
			Fare fare = new Fare(flight.getFlightNo(),flight.getBusinessFare(),flight.getEconomyFare(),Date.from(flightDeparture.toInstant()));
			booking.setFare(fare);
			
			booking.setDepartureDateTime(flightDeparture);
			
			Duration totalTime = Duration.between(departureTime, arrivalTime);
			booking.setTotalTime(totalTime.toMinutes());
			OffsetDateTime flightArrival = flightDeparture;
			flightArrival = flightArrival.plus(totalTime);
			
			booking.setArrivalDateTime(flightArrival);
			
//			System.out.println(booking);
//			System.out.println(booking.getTotalTime().toHours());
			
			flightBookingDao.save(booking);
			
		});
		
	}

	@Override
	public void addFlight(Flight flight) throws FlightException{
		// TODO Auto-generated method stub
		if(Duration.between(flight.getRoute().getDepartureTime() ,flight.getRoute().getArrivalTime()).toMinutes()<=0){
			throw new FlightException("Arrival time on or before departure");
		}
		flightDao.save(flight);
		System.out.println(flight);
		
	}

	@Override
	public void deleteFlight(Flight flight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Flight updateFlight(Flight updtFlight) throws FlightException {
		// TODO Auto-generated method stub
		
		Optional<Flight> f = flightDao.findById(updtFlight.getFlightId());
		if(f.isEmpty()) {
			throw new FlightException("Incorrect Flight Id");
		}
		
		Flight flight1 =f.map( flight -> {
			flight.setFlightNo(updtFlight.getFlightNo());
			flight.setFlightWeekDays(updtFlight.getFlightWeekDays());
			flight.setRoute(updtFlight.getRoute());
			return flightDao.save(flight);
		}).orElseGet(() -> {
			return flightDao.save(updtFlight);
		});
		
		return flight1;
	}

	
	@Override
	public ResponseEntity<List<Flight>> getFlights() throws FlightException {
		List<Flight> flights = flightDao.findAll();
		if(flights.isEmpty()) {
			throw new FlightException("No Flights in Database");
		}
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.ACCEPTED);
	}

}
