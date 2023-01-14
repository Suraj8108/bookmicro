package com.example.bookmicro.dto;

public class FareSeatsDto {
	
	Integer[] seats ;

	public FareSeatsDto() {
		super();
	}

	public FareSeatsDto(Integer[] seats) {
		super();
		this.seats = seats;
	}

	public Integer[] getSeats() {
		return seats;
	}

	public void setSeats(Integer[] seats) {
		this.seats = seats;
	}
	

}
