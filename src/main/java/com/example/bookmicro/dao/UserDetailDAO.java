package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.UserDetail;

@Repository
public interface UserDetailDAO extends JpaRepository<UserDetail, Integer>{

	public UserDetail findUserDetailByEmailId(String emailId);
}
