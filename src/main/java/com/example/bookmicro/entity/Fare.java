package com.example.bookmicro.entity;


import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Fare {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer fareId;
	///////
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="id" , nullable = false) private FlightBooking fb;
	 */
	/////////
	private int flightNo;
	private int bFare;
	private int eFare;
	private Date departure;
	
	
	
	
	public Fare() {
		super();
	}




	public Fare(int flightNo, int bFare, int eFare, Date departure) {
		super();
		this.flightNo = flightNo;
		this.bFare = bFare;
		this.eFare = eFare;
		this.departure = departure;
	}




	public Integer getFareId() {
		return fareId;
	}


	public int getFlightNo() {
		return flightNo;
	}


	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}


	public int getbFare() {
		return bFare;
	}



	public void setbFare(int bFare) {
		this.bFare = bFare;
	}




	public int geteFare() {
		return eFare;
	}




	public void seteFare(int eFare) {
		this.eFare = eFare;
	}




	public Date getDeparture() {
		return departure;
	}




	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	
	
	
	
	

}
