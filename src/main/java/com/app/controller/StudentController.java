package com.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.app.entity.Accountant;
import com.app.entity.Admin;
import com.app.entity.Student;
import com.app.repository.AccountantRepository;
import com.app.repository.StudentRepository;


@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository repo;
	
	
	@GetMapping("/studentlogin/{emailId}/{password}")
	public Student getStudentByEmailIdAndPassword(@PathVariable String emailId, @PathVariable String password ) {
		return repo.findByEmailIdAndPassword(emailId,password);
	}
	
	@GetMapping("/studentlogin/{studentId}")
	public Optional<Student> getStudentById(@PathVariable Integer studentId) {
		return repo.findByStudentId(studentId);
	}



}
