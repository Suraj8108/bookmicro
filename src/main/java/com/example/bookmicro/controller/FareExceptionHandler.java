package com.example.bookmicro.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.bookmicro.exceptions.FareException;





@ControllerAdvice
public class FareExceptionHandler extends ResponseEntityExceptionHandler{
	
	 
	    @ExceptionHandler(value = { FareException.class })
	    
	            protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
	                String bodyOfResponse = ex.getMessage();
	                
	                return handleExceptionInternal(ex, bodyOfResponse, 
	                  new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	            }
	}