package com.example.bookmicro.dto;

import jakarta.persistence.Column;

public class UserDetailDto {
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private long phoneNumber;
		
	private long loyalty;

	public UserDetailDto() {
		super();
	}
	
	public UserDetailDto(String firstName, String lastName, String emailId, long phoneNumber, long loyalty) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.loyalty = loyalty;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(long loyalty) {
		this.loyalty = loyalty;
	}
	
	
}
