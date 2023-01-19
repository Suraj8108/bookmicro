package com.example.bookmicro.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmicro.entity.OrderRazorpay;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/order")
public class RazorpayOrderController {
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody OrderRazorpay orderInfo) throws RazorpayException {
		
		var client = new RazorpayClient("rzp_test_pt82f4jFDDF6I0", "YmWdK5eoUA4QfGltdQYRkyHg");
		
		 JSONObject response = new JSONObject();
		 response.put("amount", orderInfo.getAmount()*100);
		 response.put("currency", "INR");
		 response.put("receipt", "txn_235425");
		 
		 Order order =  client.Orders.create(response);

		 System.out.println(order);
		return order.toString();
	}

}
