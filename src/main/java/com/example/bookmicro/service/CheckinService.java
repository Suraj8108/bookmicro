package com.example.bookmicro.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookmicro.dto.CheckinDetailsDto;
import com.example.bookmicro.dto.CheckinSeatsDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.PnrRequest;

@Service
public interface CheckinService {

	public CheckinDetailsDto validatePnr(PnrRequest pnrRequest);
	
	//Get all bookings for the Flight Booking details on the given departure date for checkin initialization
	public List<Booking> bookingInfoForScheduler(OffsetDateTime departureDate);
	
	public List<Checkin> getAllCheckin();
	
	public Checkin addCheckin(Checkin checkin);
	
	public Checkin updateCheckinPayment(CheckinSeatsDto checkinDto); 
	
}
