package com.example.bookmicro.entity;

public class OrderRazorpay {

	private long amount;
	
	private String description;

	public OrderRazorpay(long amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public OrderRazorpay() {
		super();
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
