package com.example.bookmicro.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dto.CheckinDetailsDto;
import com.example.bookmicro.dto.CheckinSeatsDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.PnrRequest;
import com.example.bookmicro.service.CheckinService;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

	@Autowired
	CheckinService checkinService;
	@PostMapping("/validatePnr")
	public CheckinDetailsDto validatePnr(@RequestBody PnrRequest pnrRequest) {
		CheckinDetailsDto checkinInfo = checkinService.validatePnr(pnrRequest);
		return checkinInfo;
	}
	
	@PostMapping("/bookingInfoForScheduler/{departureDate}")
	public List<Booking> getAllBookingsForCheckinSheduler(@PathVariable OffsetDateTime departureDate){
		
		List<Booking> allBookings = checkinService.bookingInfoForScheduler(departureDate);
		return allBookings;
		
	}
	
	@GetMapping("/getAllCheckIn")
	public List<Checkin> getAllCheckIn(){
		
		return checkinService.getAllCheckin();
		
	}
	
	@PostMapping("/addCheckin")
	public String addCheckin(@RequestBody Checkin checkin) {
		checkinService.addCheckin(checkin);
		return "Successfully Added Checkin";
	}
	
	//Get all 
	@PostMapping("/checkInSuccess")
	public Checkin updatePaymentCheckin(@RequestBody CheckinSeatsDto checkinDto) {
		Checkin checkinDetails = checkinService.updateCheckinPayment(checkinDto);
		
		return checkinDetails;
	}
}
