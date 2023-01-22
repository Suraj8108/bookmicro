package com.example.bookmicro.entity;

public class PnrRequest {

	private String pnr;
	
	private String emailId;

	public PnrRequest(String pnr, String emailId) {
		super();
		this.pnr = pnr;
		this.emailId = emailId;
	}

	public PnrRequest() {
		super();
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
