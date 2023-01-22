package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Checkin;

@Repository
public interface CheckinDAO extends JpaRepository<Checkin, Integer>{

	
}
