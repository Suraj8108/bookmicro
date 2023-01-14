package com.example.bookmicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.PaymentDAO;
import com.example.bookmicro.entity.Payment;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentDAO paymentDao;
	
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<Payment>> getAllPayments(){
		List<Payment> payments = paymentDao.findAll();
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.ACCEPTED);
	}
	

}
