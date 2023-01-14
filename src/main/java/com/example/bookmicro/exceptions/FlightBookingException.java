package com.example.bookmicro.exceptions;

public class FlightBookingException extends RuntimeException{
	private static final long serialVersionUID = 5L;

	public FlightBookingException(String msg) {
		super(msg);
	}
}
