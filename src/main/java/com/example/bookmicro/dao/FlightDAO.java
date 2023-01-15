package com.example.bookmicro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Integer> {
	
	//Get all Flight By Days
	@Query(value = "SELECT * FROM FLIGHT F where :day = ANY(F.flight_week_days)", nativeQuery = true)
	public List<Flight> findFlightByDays(String day);
}
