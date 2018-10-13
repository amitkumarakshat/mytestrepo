package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class StudentService {

	@Autowired
	@Qualifier("student1")
	private Student std;

	public Student getStudent() {
		return std;
	}

	public void setStudent(Student std) {
		this.std = std;
	}
	
	
}
