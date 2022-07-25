package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.app.config.StringPrefixedSequenceIdGenerator;
import com.sun.istack.NotNull;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "book_seq")
    @GenericGenerator(
        name = "book_seq", 
        strategy = "com.app.config.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "a00")
        })
	private String studentId;
	private String studentName;
	private String gender;
	private String emailId;
	private String course;
	private int fee;
	private int paid;
	private int duefees;
	private long contactno;
	private String address;
	
	@NotNull
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public Student(String studentId) {
		// TODO Auto-generated constructor stub
		super();
		this.studentId = studentId;
	}
	
	public Student() {
		
	};


	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId2) {
		this.studentId = studentId2;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}


	public int getPaid() {
		return paid;
	}


	public void setPaid(int paid) {
		this.paid = paid;
	}


	public int getDuefees() {
		return duefees;
	}


	public void setDuefees(int duefees) {
		this.duefees = duefees;
	}


	public long getContactno() {
		return contactno;
	}


	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}