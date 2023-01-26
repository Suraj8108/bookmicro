package com.example.bookmicro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{
			

			public Booking findByPnrNo(String pnrNo);
			
			public List<Booking> findByFlightBookingIdAndSeatClassIgnoreCase(Integer id, String seatClass);
			
			public List<Booking> findByPassengerEmailId(String emailId);
			
}
