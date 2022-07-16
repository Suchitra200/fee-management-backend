package com.app.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.SmsRequest;
import com.app.config.SmsSender;
import com.app.entity.Accountant;
import com.app.entity.Admin;
import com.app.entity.Teacher;
import com.app.repository.AccountantRepository;
import com.app.repository.AdminRepository;
import com.app.repository.TeacherRepository;
import com.app.service.EmailSenderService;

@Transactional 
@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private EmailSenderService service;

	@Autowired
	private AccountantRepository accountantRepo;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private SmsSender SmsSender;
	

	@GetMapping("/admin/{emailId}/{password}")
	public Admin getAdminByEmailIdAndPassword(@PathVariable String emailId, @PathVariable String password ) throws MessagingException {
		
		
	//	  service.sendSimpleEmail(emailId, "Logged in at" + service.getCurrentDate(),
	//	  "Logging Information");
		 
	//	SmsRequest request = new SmsRequest("8147668398", "Hello Tameem");
	//	SmsSender.sendSms(request);
		
		return adminRepo.findByEmailIdAndPassword(emailId,password);
		
		
	}

	@PostMapping("/admin/addaccountant")
	public Accountant addAccountant(@RequestBody Accountant accountant) {
		accountantRepo.save(accountant);
		return accountant;
	}
	

	@PostMapping("/admin/addTeacher")
	public void addTeacher(@RequestBody Teacher teacher) {
		teacherRepo.save(teacher);
		
	}

	
	@GetMapping("/admin/viewaccountant")
	public List<Accountant> viewAccountant() {
		return accountantRepo.findAll();
	}
	
	
	
	 @Modifying
	 @PutMapping("/adminlogin/update/{accountantId}")
		public Accountant updateAccountantDetails(@RequestBody Accountant accountant, @PathVariable Integer accountantId) {
			accountant.setAccountantId(accountantId);
			accountantRepo.save(accountant);
			return accountant;
		}
		
		
		@DeleteMapping("/adminlogin/delete/{accountantId}")
		public String deleteAccountant( @PathVariable Integer accountantId) {
			accountantRepo.deleteById(accountantId );
			return "DeletedSuccessfully";
		}
	

}
