package com.example.bookmicro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmicro.entity.Route;

@Repository
public interface RouteDAO extends JpaRepository<Route, Integer>{
	
}
