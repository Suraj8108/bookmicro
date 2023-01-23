package com.example.bookmicro.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.BookingDAO;
import com.example.bookmicro.dao.FareRepository;
import com.example.bookmicro.dao.FlightBookingDAO;
import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.exceptions.FareException;
import com.example.bookmicro.service.CheckinService;
import com.example.bookmicro.service.FareService;



@Service 
public class IFareService implements FareService {
	
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	@Autowired
	private FareRepository fareRepo;
	
	@Autowired
	private BookingDAO bookingDao;
	
	@Autowired
	private FlightBookingDAO flightBookingDAO;
	
	@Override
	public Fare addFare(Fare fare) throws FareException {
	
		
		return fareRepo.save(fare);
	}

	//noOfSeats means that total number of seats that user has choosed
	@Override
	public int getFare(int fareId, int noOfSeat, String typeOfSeat) throws FareException {
		
		@SuppressWarnings("deprecation")
		Fare f = fareRepo.getById(fareId);
		Date currDate = new Date();
		long noOfDays =  getDifferenceDays(currDate,f.getDeparture())  ; 
		double sum=0;
		double multiplier1=1;
		// create logic for multiplier 2 based on the number of seats booked
		/* float multiplier2=1;    if >28 days lowest bound = 1 , else 0.7, max val = 1.5   */
		double multiplier2 = 1;
		
		FlightBooking fbook = flightBookingDAO.findByFareFareId(fareId);
		int bookedSeatLength = bookingDao.findByFlightBookingIdAndSeatClassIgnoreCase(fbook.getId(), typeOfSeat).size();
		int totalSeats = typeOfSeat.equalsIgnoreCase("business") ? 20 : 150;
		double percentFull= (bookedSeatLength / totalSeats) * 100;//will derive this val from total seats booked
		
		// code for multiplier 2
		if(percentFull<10) {
			if(noOfDays>28) {
				multiplier2=1;
			}
			else {
				multiplier2= 0.95;
			}
			
		}
		else if(percentFull>=10 && percentFull<30) {
			if(noOfDays>28) {
				multiplier2=1.1;
			}
			else {
				multiplier2= 1;
			}
		}
		else if(percentFull>=30 && percentFull<50) {
			if(noOfDays>28) {
				multiplier2=1.25;
			}
			else {
				multiplier2= 1.05;
			}
		}
		else if(percentFull>=50 && percentFull<60) {
			if(noOfDays>28) {
				multiplier2=1.30;
			}
			else {
				multiplier2= 1.15;
			}
		}
		else if(percentFull>=60 && percentFull<70) {
			if(noOfDays>28) {
				multiplier2=1.35;
			}
			else {
				multiplier2= 1.20;
			}
		}
		else if(percentFull>=70 && percentFull<80) {
			if(noOfDays>28) {
				multiplier2=1.40;
			}
			else {
				multiplier2= 1.2;
			}
		}
		else if(percentFull>=80 && percentFull<90) {
			if(noOfDays>28) {
				multiplier2=1.45;
			}
			else {
				multiplier2= 1.30;
			}
		}
		else if(percentFull>=90 && percentFull<=100) {
			if(noOfDays>28) {
				multiplier2=1.50;
			}
			else {
				multiplier2= 1.35;
			}
		}
		
		// dynamic multiplier 2 code end
		if(noOfSeat==0) {
			throw new FareException("No seat chosen");
		}
		
		//dynamic multiplier 1 code start
		
		if(noOfDays<28) {
			int daysPassed = (int) (28-noOfDays);
			multiplier1 = (float) (1+ daysPassed*0.05);
		}
		//dynamic multiplier 1 code start
		
		
		
		System.out.println("Businessss" + f.getbFare());
		System.out.println(multiplier1);
		System.out.println(multiplier2);
		
		
		
		if(typeOfSeat.equalsIgnoreCase("business"))
		{
			sum = f.getbFare()*noOfSeat*multiplier1*multiplier2;
		}
		else if(typeOfSeat.equalsIgnoreCase("economy")) {
			sum = f.geteFare()*noOfSeat*multiplier1*multiplier2;
		}
		
		else
		{
			sum = -1; 
			throw new FareException("Invalid Seat Type");// error check
		}
		
		return (int) Math.ceil(sum);
	}

	
	
	@Override   //to do seat fare for the economy seats
	public Integer getSeatFares(Integer[] seats) throws FareException {
		
		int sum=0;
		for(int i=0;i<seats.length;i++) {
			if(seats[i]<0) {
				throw new FareException("invalid seat");
			}
			boolean mid = (seats[i]-19)%3 == 0;
			boolean win = ((seats[i]-21)%6 == 0) ||((seats[i]-26)%6 == 0);
		
			
			if(seats[i]>170 || seats[i]<21) {
				throw new FareException("invalid seat");
			}
			else if (mid) {
				sum =sum +0;
			}
			else if(win) {
				sum = sum +200;
			}
			else {
				sum = sum +250;
			}
			
		}
		
		return sum;
	}
	
	
	

	@Override
	public int getGst(int totalFare, String typeOfSeat ) throws FareException {

		
		if(typeOfSeat.equalsIgnoreCase("business")) {
			return (int) Math.ceil(totalFare*0.12);
		}
		else if(typeOfSeat.equalsIgnoreCase("economy")) {
			return (int) Math.ceil(totalFare*0.05);
		}
		else {
			throw new FareException("Invalid Seat Type");
		}
		
		 
	}

	@Override
	public int totalFare(int fare, int seatsFare, int gst) throws FareException {
		if(fare==0) {
			throw new FareException("Fare is 0");
		}
		return (fare + seatsFare + gst);
	}

	
	@Override
	public void deleteFareById(int fareId) throws FareException {
		
		
		fareRepo.deleteById(fareId);
	}
	

	@Override
	public Fare updateFare(int id,Fare fare) throws FareException {
	
		@SuppressWarnings("deprecation")
		Fare f = fareRepo.getById(id);
		if(f==null) {
			throw new FareException("Invalid FareID");
		}
		f.setbFare(fare.getbFare());
		f.setDeparture(fare.getDeparture());
		f.seteFare(fare.geteFare());
		f.setFlightNo(fare.getFlightNo());
		
		return  fareRepo.save(f);
		
		
	}

	@Override
	public List<Fare> getAll() throws FareException {
		// TODO Auto-generated method stub
		return fareRepo.findAll();
	}

	
	public Integer getEconomySeatFare(int seat) {
		int sum=0;
			if(seat<0) {
				throw new FareException("invalid seat");
			}
			boolean mid = (seat - 19)%3 == 0;
			boolean win = ((seat - 21)%6 == 0) ||((seat-26)%6 == 0);
		
			
			if(seat>170 || seat <21) {
				throw new FareException("invalid seat");
			}
			else if (mid) {
				sum =sum +0;
			}
			else if(win) {
				sum = sum +250;
			}
			else {
				sum = sum +200;
			}
		
		return sum;
	}
	
	public Integer getBusinessSeatFare(int seat) {
		int sum=0;
		boolean win = (seat%4 == 1 || seat % 4 == 0);
		boolean left = seat%4 == 2;
	
		
		if(seat<0 || seat > 21) {
			throw new FareException("invalid seat");
		}
		else if (win) {
			sum =sum + 400;
		}
		else if(left) {
			sum = sum + 200;
		}
		else {
			sum = sum + 250;
		}
	
		return sum;
		
		
	}
	
	//Map of strings
	@Override
	public Map<String, Integer> getAllSeatsFare(int fareId) {
		// TODO Auto-generated method stub
		Map<String, Integer> allseat = new HashMap<>();
		for(int i = 1; i <= 20; i++) {
			int fare = this.getBusinessSeatFare(i);
			allseat.put("b" + String.valueOf(i), fare);
		}
		for(int i = 21; i <= 170; i++) {
			int fare = this.getEconomySeatFare(i);
			allseat.put("e"+String.valueOf(i), fare);
		}
		
		return allseat;
	}
	
	public List<Integer> getAllSeatsFareList(int fareId) {
		// TODO Auto-generated method stub
		List<Integer> allseat = new ArrayList<>();
		for(int i = 1; i <= 20; i++) {
			int fare = this.getBusinessSeatFare(i);
			allseat.add(fare);
		}
		for(int i = 21; i <= 170; i++) {
			int fare = this.getEconomySeatFare(i);
			allseat.add(fare);
		}
		
		return allseat;
	}

	
}

