package com.example.bookmicro.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	private String paymentNo;
	
	private String paymentMode;
	
	private String paymentStatus;
	
	private LocalDateTime paymentDate;
	
	
//	private Integer seatPayment = -1;
//	
//	private Integer seatPaymentID = -1;
    
	
	
	
	
	public Payment(Integer paymentId, String paymentNo, String paymentMode, String paymentStatus, LocalDateTime paymentDate) {
		super();
		this.paymentId = paymentId;
		this.paymentNo = paymentNo;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		
	}

	public Payment() {
	super();
}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

//	public Integer getSeatPayment() {
//		return seatPayment;
//	}
//
//	public void setSeatPayment(Integer seatPayment) {
//		this.seatPayment = seatPayment;
//	}
//
//	public Integer getSeatPaymentID() {
//		return seatPaymentID;
//	}
//
//	public void setSeatPaymentID(Integer seatPaymentID) {
//		this.seatPaymentID = seatPaymentID;
//	}
//	
//	
	

	
	
}
