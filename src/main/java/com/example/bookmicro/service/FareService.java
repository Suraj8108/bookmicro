package com.example.bookmicro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.exceptions.FareException;

@Service
public interface FareService {
	public Fare addFare(Fare fare) throws FareException   ;
	

	public int getFare(int fareId,int noOfSeat, String typeOfSeat) throws FareException ; 
	// can put the below two functions in the utility
	public Integer getSeatFares(Integer[] seats)throws FareException;
	public int getGst(int totalFare /* without Gst*/, String typeOfSeat) throws FareException; //sum of getFare and getSeatFare
	public int totalFare(int fare, int seatsFare,int gst)throws FareException;
	//
	public List<Fare> getAll()throws FareException;
	
	public void deleteFareById(int fareId)throws FareException;
	
	public Fare updateFare(int id,Fare fare)throws FareException;
}
