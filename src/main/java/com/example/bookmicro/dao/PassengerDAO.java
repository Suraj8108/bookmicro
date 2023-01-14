package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Passenger;

@Repository
public interface PassengerDAO extends JpaRepository<Passenger, Integer>{

}
