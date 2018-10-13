package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*@Service(value="testService1")
public class TestService {

	@Autowired
	private TestDAOInterface testDAO;

	
	public void logic() {
		testDAO.meth();
	}
	
	
}*/

public class TestService {

	private TestDAO testDAO;

	public void setTestDAO(TestDAO testDAO) {
		System.out.println("---SETTER METHOD of TestService-----------");
		this.testDAO = testDAO;
	}

	public void logic() {
		testDAO.meth();
	}
}
