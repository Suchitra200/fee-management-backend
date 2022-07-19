package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.EmailSenderService;

@RestController
@RequestMapping(value = "/send")
public class EmailController 
{
	
	@Autowired
	private EmailSenderService emailSender;
	
	@GetMapping("/feesEmail/{emailId}/{sum}")
	public void sendEmail(@PathVariable String emailId, 
			@PathVariable String sum)
	{
		System.out.println("mail send");
		String body = "Please pay the Fee amount" + sum;
		emailSender.sendSimpleEmail(emailId,body, "Fee Balance");
	}

}
