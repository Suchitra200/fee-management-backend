package com.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.PaypalService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

   
    @PostMapping(value = "/make/feePayment")
    public String makePayment(@RequestParam("sum") String sum) throws RazorpayException{
    	RazorpayClient razorpayClient = new RazorpayClient("rzp_live_ASOD5nE9paIWXV", "FR453NUT7x6wYNl5cGwz9pDN");
    
    	JSONObject options = new JSONObject();
    	options.put("amount", 5000);
    	options.put("currency", "INR");
    	options.put("receipt", "txn_123456");
    	Order order = razorpayClient.Orders.create(options);
    	System.out.println(order);
    	
    	return "done";
    }
    
  
    
    
    
}