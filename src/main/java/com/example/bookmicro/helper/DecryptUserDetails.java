package com.example.bookmicro.helper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class DecryptUserDetails {

	@Autowired
	JwtUtil jwtUtil;
	
	public String decryptEmailId(HttpServletRequest request) {
		
		String commonToken = request.getHeader("Authorization").substring(7);
		return jwtUtil.extractUsername(commonToken);
	}
}
