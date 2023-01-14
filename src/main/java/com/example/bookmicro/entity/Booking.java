package com.example.bookmicro.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;	
	
	@Column(unique=true)
	private String pnrNo;
	
	private LocalDateTime bookingDate;
	
	private String seatClass;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="flightBooking", nullable=false)
	private FlightBooking flightBooking;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="payment_id")
	private Payment payment;
	
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Passenger> passenger;

	public Booking(String pnrNo, LocalDateTime bookingDate, String seatClass, FlightBooking flightBooking,
			Payment payment, List<Passenger> passenger) {
		super();
		this.pnrNo = pnrNo;
		this.bookingDate = bookingDate;
		this.seatClass = seatClass;
		this.flightBooking = flightBooking;
		this.payment = payment;
		this.passenger = passenger;
	}

	public int getBookingId() {
		return bookingId;
	}

	

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
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

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	
	
}
