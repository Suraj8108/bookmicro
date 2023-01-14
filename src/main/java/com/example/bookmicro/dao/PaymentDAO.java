package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmicro.entity.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Integer>{

}
