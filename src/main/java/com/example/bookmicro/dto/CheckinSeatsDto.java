package com.example.bookmicro.dto;

import java.util.List;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.Passenger;
import com.example.bookmicro.entity.Payment;

public class CheckinSeatsDto {

	//CheckIn to update the Payment
	
	private Checkin checkin;
	
	//This is to save the booking 
	private List<Passenger> passengers;

	public CheckinSeatsDto(Checkin checkin, List<Passenger> passengers) {
		super();
		this.checkin = checkin;
		this.passengers = passengers;
	}

	public CheckinSeatsDto() {
		super();
	}

	public Checkin getCheckin() {
		return checkin;
	}

	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	
	
	
}
