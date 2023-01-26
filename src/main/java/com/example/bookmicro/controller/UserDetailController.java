package com.example.bookmicro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.BookingDAO;
import com.example.bookmicro.dao.CheckinDAO;
import com.example.bookmicro.dao.UserDetailDAO;
import com.example.bookmicro.dto.UserBookingDto;
import com.example.bookmicro.dto.UserDetailDto;
import com.example.bookmicro.entity.Booking;
import com.example.bookmicro.entity.Checkin;
import com.example.bookmicro.entity.Flight;
import com.example.bookmicro.entity.FlightBooking;
import com.example.bookmicro.entity.UserDetail;
import com.example.bookmicro.helper.DecryptUserDetails;
import com.example.bookmicro.helper.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserDetailController {

	@Autowired
	UserDetailDAO userDetailDAO;
	
	@Autowired
	BookingDAO bookingDao;
	
	@Autowired
	CheckinDAO checkinDao;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	DecryptUserDetails decryptUserDetails;
	
	@PostMapping("/getMyDetails")
	public UserDetail myDetails(HttpServletRequest request ) {
		System.out.println("Hello");
		String emailId = decryptUserDetails.decryptEmailId(request);
		System.out.println(emailId);
		return userDetailDAO.findUserDetailByEmailId(emailId);
		
	}
	
	@PostMapping("/signUp")
	public UserDetailDto signUpUser(@RequestBody UserDetail userDetail) {
		
		UserDetail user = userDetailDAO.save(userDetail);
		
		UserDetailDto userDto = new UserDetailDto();
		
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmailId(user.getEmailId());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setLoyalty(user.getLoyalty());
		
		return userDto;
	}
	
	//Function to get my checkin Details remaining
	@PostMapping("/myCheckinLeft")
	public UserBookingDto getMyCheckinLeftDetails(HttpServletRequest request){
		String emailId = decryptUserDetails.decryptEmailId(request);
		List<Checkin> allCheckin = checkinDao.findByEmailId(emailId);
		
		UserBookingDto bookDto = new UserBookingDto();
		
		List<Booking> userBook = new ArrayList<>();
		List<FlightBooking> userFlightBook = new ArrayList<>();
		List<Flight> userFlightDeatil = new ArrayList<>();
		List<Checkin> userCheckIn = new ArrayList<>();
		
		for(Checkin check : allCheckin) {
			
			if(check.getPayment() == null) {
				userBook.add(check.getBooking());
				userFlightBook.add(check.getFlightBooking());
				userFlightDeatil.add(check.getFlightBooking().getFlight());
				userCheckIn.add(check);
			}
			
		}
		
		bookDto.setBookingDetails(userBook);
		bookDto.setFlightBookings(userFlightBook);
		bookDto.setFlightDetails(userFlightDeatil);
		bookDto.setUserDetails(this.myDetails(request));
		bookDto.setCheckinDetails(userCheckIn);
		
		return bookDto;
	}
	
	@PostMapping("/getMyBookings")
	public UserBookingDto getMyBookingDetails(HttpServletRequest request) {
		String emailId = decryptUserDetails.decryptEmailId(request);
		
		//In this the checkin list which has values means its checkin is done
		List<Booking> allBooking = bookingDao.findByPassengerEmailId(emailId);
		
		UserBookingDto bookDto = new UserBookingDto();
		
		List<Booking> userBook = new ArrayList<>();
		List<FlightBooking> userFlightBook = new ArrayList<>();
		List<Flight> userFlightDeatil = new ArrayList<>();
		List<Checkin> userCheckIn = new ArrayList<>();
		
		for(Booking book : allBooking) {
			userBook.add(book);
			userFlightBook.add(book.getFlightBooking());
			userFlightDeatil.add(book.getFlightBooking().getFlight());
			userCheckIn.add(checkinDao.findByBooking(book));
			
		}
		
		bookDto.setBookingDetails(userBook);
		bookDto.setFlightBookings(userFlightBook);
		bookDto.setFlightDetails(userFlightDeatil);
		bookDto.setUserDetails(this.myDetails(request));
		bookDto.setCheckinDetails(userCheckIn);
		
		return bookDto;
	}
	
}
