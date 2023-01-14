package com.example.bookmicro.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity

public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int flightId;	
	private int flightNo;
	
	private int businessFare;
	private int economyFare;
	
	private Set<String> flightWeekDays;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="route_id", referencedColumnName = "routeId")
	private Route route; 

	@JsonManagedReference
	@OneToMany(mappedBy = "flight")
	private List<FlightBooking> flightBooking;


	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightNo=" + flightNo + ", flightWeekDays=" + flightWeekDays
				+ ", route=" + route + ", business fare = "+businessFare+ ", economy fare"+economyFare+"]";
	}
	

	public Flight( int flightNo, int businessFare, int economyFare, Set<String> flightWeekDays,
			Route route, List<FlightBooking> flightBooking) {
		super();
	
		this.flightNo = flightNo;
		this.businessFare = businessFare;
		this.economyFare = economyFare;
		this.flightWeekDays = flightWeekDays;
		this.route = route;
		this.flightBooking = flightBooking;
	}






	public Flight() {
		super();
	}






	public int getFlightId() {
		return flightId;
	}






	






	public int getFlightNo() {
		return flightNo;
	}






	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}






	public int getBusinessFare() {
		return businessFare;
	}






	public void setBusinessFare(int businessFare) {
		this.businessFare = businessFare;
	}






	public int getEconomyFare() {
		return economyFare;
	}






	public void setEconomyFare(int economyFare) {
		this.economyFare = economyFare;
	}






	public Set<String> getFlightWeekDays() {
		return flightWeekDays;
	}






	public void setFlightWeekDays(Set<String> flightWeekDays) {
		this.flightWeekDays = flightWeekDays;
	}






	public Route getRoute() {
		return route;
	}






	public void setRoute(Route route) {
		this.route = route;
	}






	public List<FlightBooking> getFlightBooking() {
		return flightBooking;
	}






	public void setFlightBooking(List<FlightBooking> flightBooking) {
		this.flightBooking = flightBooking;
	}
	
	
	
}
