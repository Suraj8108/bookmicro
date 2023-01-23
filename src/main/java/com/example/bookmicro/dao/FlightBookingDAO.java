package com.example.bookmicro.dao;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.FlightBooking;

@Repository
public interface FlightBookingDAO extends JpaRepository<FlightBooking, Integer>{

	public List<FlightBooking> findByDepartureDateTime(OffsetDateTime departureDateTime);
	
	public FlightBooking findByFareFareId(int fareId);
	
	
}
