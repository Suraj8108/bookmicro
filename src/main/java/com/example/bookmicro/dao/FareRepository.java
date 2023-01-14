package com.example.bookmicro.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bookmicro.entity.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare,Integer> {
	

}
