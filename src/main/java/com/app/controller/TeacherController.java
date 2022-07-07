package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.entity.Teacher;
import com.app.repository.TeacherRepository;

@RestController
public class TeacherController 
{
	@Autowired 
	private TeacherRepository repo;
	
	@GetMapping("/getAllStudents")
	public List<Student> getStudentById() {
		return repo.findByDueFee();
	}
	
	@GetMapping("/teacherLogin/{emailId}/{password}")
	public Teacher getStudentByEmailIdAndPassword(@PathVariable String emailId, @PathVariable String password ) {
		return repo.findByEmailIdAndPassword(emailId,password);
	}

}
