package com.example.bookmicro.serviceImpl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.BookingDAO;
import com.example.bookmicro.dao.CheckinDAO;
import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.dao.PassengerDAO;
import com.example.bookmicro.dto.CheckinDetailsDto;
import com.example.bookmicro.dto.CheckinSeatsDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.Passenger;
import com.example.bookmicro.entity.PnrRequest;
import com.example.bookmicro.service.CheckinService;

@Service
public class ICheckinService implements CheckinService{

	@Autowired
	BookingDAO bookingDao;
	
	@Autowired
	CheckinDAO checkinDao;
	
	@Autowired
	PassengerDAO passengerDao;
	
	@Autowired
	FlightBookingDAO flightBookingDao;
	
	public Checkin getCheckinByPnr(String pnrNo) {
		List<Checkin> allCheckin = checkinDao.findAll();
		
		for(Checkin checkin : allCheckin) {
			if(checkin.getBooking().getPnrNo().equals(pnrNo)) {
				return checkin;
			}
		}
		return null;
	}
	
	
	public Set<String> getBookedSeatsByFlightBookingId(int flightBookingId, String seatClass){
		
		FlightBooking flightDetails = flightBookingDao.findById(flightBookingId).get();
		Set<Booking> bookings = flightDetails.getBooking();
		
		Set<String> bookedSeats =  new HashSet<>();
		for(Booking booking : bookings) {
			
			if(booking.getSeatClass().equals(seatClass)) {
				List<Passenger> passengers = booking.getPassenger();
				for(Passenger passenger : passengers) {
					
					if(passenger.getSeatNo() != null && !passenger.getSeatNo().isEmpty() ) {
						bookedSeats.add(passenger.getSeatNo());
					}
				}
			}
			
		}
		
		return bookedSeats;
	}
	
	@Override
	public CheckinDetailsDto validatePnr(PnrRequest pnrRequest) {
		// TODO Auto-generated method stub
		Booking bookingDetails = bookingDao.findByPnrNo(pnrRequest.getPnr());
		String errorMsg;
		if(bookingDetails == null) {
			throw new RuntimeException("PNR number is Invalid");
		}
		
		if(!bookingDetails.getPassenger().get(0).getEmailId().equals(pnrRequest.getEmailId())) {
			throw new RuntimeException("Invalid EmailId");
		}
		
		Checkin myCheckin = this.getCheckinByPnr(pnrRequest.getPnr());
		
		if(myCheckin == null) {
			throw new RuntimeException("CheckIn not yet started It will start 24hrs prior to Flight Departure");
		}
		
		int flightBookingId = myCheckin.getBooking().getFlightBooking().getId();
		
		CheckinDetailsDto checkinDto = new CheckinDetailsDto();
		
		checkinDto.setBookingDetails(myCheckin.getBooking());
		checkinDto.setFlightBooking(myCheckin.getBooking().getFlightBooking());
		checkinDto.setFlight(myCheckin.getBooking().getFlightBooking().getFlight());
		Set<String> economyBooked = this.getBookedSeatsByFlightBookingId(flightBookingId, "economy");
		Set<String> businessBooked = this.getBookedSeatsByFlightBookingId(flightBookingId, "business");
		checkinDto.setSeatsEconomyBooked(economyBooked);
		checkinDto.setSeatsBussinessBooked(businessBooked);
		checkinDto.setCheckInDetails(myCheckin);
		
		
		return checkinDto;
	}


	@Override
	public List<Booking> bookingInfoForScheduler(OffsetDateTime departureDate) {
		// TODO Auto-generated method stub.
		
		List<FlightBooking> bookableFlights = flightBookingDao.findAll();
		
		List<FlightBooking> flights = new ArrayList<>();
		for(FlightBooking bFlight : bookableFlights ) {
			if(bFlight.getDepartureDateTime().toLocalDate().compareTo(departureDate.toLocalDate()) == 0) {
				flights.add(bFlight);
			}
		}
		//List of bookings whose Checkin needs to be initaliazed
		List<Booking> bookings = new ArrayList<>();
		for(FlightBooking flight : flights) {
			bookings.addAll(flight.getBooking());
		}
		return bookings;
	}


	@Override
	public List<Checkin> getAllCheckin() {
		// TODO Auto-generated method stub
		return checkinDao.findAll();
	}


	@Override
	public Checkin addCheckin(Checkin checkin) {
		// TODO Auto-generated method stub
		Booking booking = bookingDao.findById(checkin.getBooking().getBookingId()).get();
		
		checkin.setBooking(booking);
		return checkinDao.save(checkin);
	}


	@Override
	public Checkin updateCheckinPayment(CheckinSeatsDto checkinDto) {
		// TODO Auto-generated method stub
		
		Checkin checkIn = checkinDao.findById(checkinDto.getCheckin().getCheckinId()).get();
		checkIn.setPayment(checkinDto.getCheckin().getPayment());
		Checkin result = checkinDao.save(checkIn);
		
		Booking booking = bookingDao.findById(result.getBooking().getBookingId()).get();
		
		Map<Integer, String> seatPassenger = new HashMap<>();
		
		for(Passenger pass : checkinDto.getPassengers()) {
			seatPassenger.put(pass.getPassengerId(), pass.getSeatNo());
		}
		
		for(Passenger passenger : booking.getPassenger()) {
			Passenger dbPass = passengerDao.findById(passenger.getPassengerId()).get();
			dbPass.setSeatNo(seatPassenger.get(passenger.getPassengerId()));
			
			passengerDao.save(dbPass);
		}
	
		bookingDao.save(booking);
		
		return result;
	}
	
	

}
