package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.FlightBooking;

@Repository
public interface FlightBookingDAO extends JpaRepository<FlightBooking, Integer>{

}
