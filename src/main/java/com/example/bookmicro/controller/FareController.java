package com.example.bookmicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.dto.FareSeatsDto;
import com.example.bookmicro.entity.Fare;
import com.example.bookmicro.exceptions.FareException;
import com.example.bookmicro.service.FareService;



@RestController
@CrossOrigin
@RequestMapping("/fare")
public class FareController {
	
	@Autowired
	private FareService fs;
	
	
	@PostMapping("/addFare")
	public Fare addFare(@RequestBody Fare fare) throws FareException{
		return fs.addFare(fare);
	}
	
	@GetMapping("/getFare/{id}/{seats}/{Class}")
	public int getFare(@PathVariable("id") int id,@PathVariable("seats") int seats,@PathVariable("Class") String Class) throws FareException
	{
		return fs.getFare(id, seats, Class);
	}

	
	@PostMapping("/getSeatFare")
	public Integer createInventory(@RequestBody FareSeatsDto S) throws FareException{
		return fs.getSeatFares(S.getSeats());
	}
	
	
	
	
	@GetMapping("/getGst/{total}/{Class}")
	public int getGst(@PathVariable("total") int total,@PathVariable("Class") String Class) throws FareException
	{
		return fs.getGst(total, Class);
	}
	
	@GetMapping("/getTotal/{total}/{seatFare}/{gst}")
	public int getTotal(@PathVariable("total") int total,@PathVariable("seatFare") int seatFare,@PathVariable("gst") int gst) throws FareException
	{
		return fs.totalFare(total, seatFare, gst);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteFare(@PathVariable("id") int id) throws FareException {
		fs.deleteFareById(id);
	}
	
	@PutMapping("updateFare/{fareId}")
	public Fare updateFare(@PathVariable("id") int id,@RequestBody Fare fare) throws FareException {
		return fs.updateFare(id, fare);
		
	}
	
	@GetMapping("/get/all")
	public List<Fare> getAllFares() throws FareException{
		return fs.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
