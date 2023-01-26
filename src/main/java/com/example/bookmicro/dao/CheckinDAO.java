package com.example.bookmicro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;

@Repository
public interface CheckinDAO extends JpaRepository<Checkin, Integer>{

	public List<Checkin> findByEmailId(String emailId);
	
	public Checkin findByBooking(Booking book);
	
}
