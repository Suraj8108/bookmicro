package com.example.bookmicro.dto;

import java.time.OffsetDateTime;
import java.util.Set;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.Route;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class FlightDetailsDto {

	private int flightNo;
	
	private Set<String> flightWeekDays;
	
	private OffsetDateTime departureDateTime;
	
	private OffsetDateTime arrivalDateTime;
	
	private long totalTime;
	
	private Route route;
	
	private Fare fare;
	
	private int busSeatAvailable;
	
	private int ecoSeatAvailable;

	public FlightDetailsDto() {
		super();
	}

	public FlightDetailsDto(int flightNo, Set<String> flightWeekDays, OffsetDateTime departureDateTime,
			OffsetDateTime arrivalDateTime, long totalTime, Route route, Fare fare, int busSeatAvailable,
			int ecoSeatAvailable) {
		super();
		this.flightNo = flightNo;
		this.flightWeekDays = flightWeekDays;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalTime = totalTime;
		this.route = route;
		this.fare = fare;
		this.busSeatAvailable = busSeatAvailable;
		this.ecoSeatAvailable = ecoSeatAvailable;
	}



	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public Set<String> getFlightWeekDays() {
		return flightWeekDays;
	}

	public void setFlightWeekDays(Set<String> flightWeekDays) {
		this.flightWeekDays = flightWeekDays;
	}

	public Fare getFare() {
		return fare;
	}

	public void setFare(Fare fare) {
		this.fare = fare;
	}

	public OffsetDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(OffsetDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public OffsetDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(OffsetDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getBusSeatAvailable() {
		return busSeatAvailable;
	}

	public void setBusSeatAvailable(int busSeatAvailable) {
		this.busSeatAvailable = busSeatAvailable;
	}

	public int getEcoSeatAvailable() {
		return ecoSeatAvailable;
	}

	public void setEcoSeatAvailable(int ecoSeatAvailable) {
		this.ecoSeatAvailable = ecoSeatAvailable;
	}


	
}
