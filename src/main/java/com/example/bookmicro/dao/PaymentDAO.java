package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Payment;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer>{

}
