package com.test;

import org.springframework.stereotype.Repository;

@Repository(value="testDAO")
public class TestDAO implements TestDAOInterface{

	@Override
	public void meth() {
		System.out.println("--------------------METH------------------------");
		
	}

}
