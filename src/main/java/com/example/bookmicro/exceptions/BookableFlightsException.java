package com.example.bookmicro.exceptions;

public class BookableFlightsException extends RuntimeException {
	
	private static final long serialVersionUID = 6L;

	public BookableFlightsException(String msg) {
		super(msg);
	}

}
