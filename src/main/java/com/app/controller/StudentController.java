package com.app.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
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
	public Optional<Student> getStudentById(@PathVariable String studentId) {
		return repo.findByStudentId(studentId);
	}



}
