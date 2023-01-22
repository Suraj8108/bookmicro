package com.example.bookmicro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Checkin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer checkinId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	private String emailId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="flightBooking", nullable=false)
	private FlightBooking flightBooking;
	
	//Seat Payment Details
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="payment_id")
	private Payment payment;

	public Checkin(Integer checkinId, Booking booking, String emailId, FlightBooking flightBooking, Payment payment) {
		super();
		this.checkinId = checkinId;
		this.booking = booking;
		this.emailId = emailId;
		this.flightBooking = flightBooking;
		this.payment = payment;
	}

	public Checkin() {
		super();
	}

	public Integer getCheckinId() {
		return checkinId;
	}

	public void setCheckinId(Integer checkinId) {
		this.checkinId = checkinId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public FlightBooking getFlightBooking() {
		return flightBooking;
	}

	public void setFlightBooking(FlightBooking flightBooking) {
		this.flightBooking = flightBooking;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
	
	
	
	
}
