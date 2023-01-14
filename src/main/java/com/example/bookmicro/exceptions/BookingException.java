package com.example.bookmicro.exceptions;

public class BookingException extends RuntimeException{

	
	private static final long serialVersionUID = 7L;

	public BookingException(String msg) {
		super(msg);
	}
}
