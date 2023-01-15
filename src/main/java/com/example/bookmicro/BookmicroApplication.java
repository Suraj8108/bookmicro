package com.example.bookmicro;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages = {"com.example.bookmicro.controller", 
		"com.example.bookmicro.config", "com.example.bookmicro.dto", "com.example.bookmicro.helper"
		,"com.example.bookmicro.service", "com.example.bookmicro.serviceImpl"})
@EnableJpaRepositories(basePackages = {"com.example.bookmicro.dao"})
@EntityScan(basePackages = {"com.example.bookmicro.entity"})
public class BookmicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmicroApplication.class, args);
	}

}
