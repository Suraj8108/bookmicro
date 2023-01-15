package com.example.bookmicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dao.UserDetailDAO;
import com.example.bookmicro.dto.UserDetailDto;
import com.example.bookmicro.entity.UserDetail;
import com.example.bookmicro.helper.DecryptUserDetails;
import com.example.bookmicro.helper.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserDetailController {

	@Autowired
	UserDetailDAO userDetailDAO;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	DecryptUserDetails decryptUserDetails;
	
	@GetMapping("/getMyDetails")
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
	
}
