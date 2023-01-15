package com.example.bookmicro.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookmicro.dao.UserDetailDAO;
import com.example.bookmicro.entity.UserDetail;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserDetailDAO userDetailDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetail user = userDetailDao.findUserDetailByEmailId(username);
		
		if(user != null) {
			return new User(user.getEmailId(), user.getPassword(), new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}
	
	

}
