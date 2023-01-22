package com.example.bookmicro.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class FlightBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private OffsetDateTime departureDateTime;
	
	private OffsetDateTime arrivalDateTime;
	
	private long totalTime;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="flight_id" , nullable = false)
	private Flight flight;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "flightBooking")
	private Set<Booking> booking;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "flightBooking")
	private Set<Checkin> checkIn;
	///////
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fare_id")
	private Fare fare;
	///////
	public FlightBooking() {
		super();
	}
	
	
	public FlightBooking(Integer id, OffsetDateTime departureDateTime, OffsetDateTime arrivalDateTime, long totalTime,
			Flight flight, Set<Booking> booking, Set<Checkin> checkIn, Fare fare) {
		super();
		this.id = id;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalTime = totalTime;
		this.flight = flight;
		this.booking = booking;
		this.checkIn = checkIn;
		this.fare = fare;
	}


	public Integer getId() {
		return id;
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
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Set<Booking> getBooking() {
		return booking;
	}
	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}
	public Fare getFare() {
		return fare;
	}
	public void setFare(Fare fare) {
		this.fare = fare;
	}


	public Set<Checkin> getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(Set<Checkin> checkIn) {
		this.checkIn = checkIn;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
