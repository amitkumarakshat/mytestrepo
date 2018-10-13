package com.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class VehicleImpl implements Vehicle{
	
	@PostConstruct
	public void init(){
		System.out.println("----INIT method called");
	}
	
   public void meth(){
	   System.out.println("GOOD");
   }
	
   @PreDestroy
   public void cleanup(){
	   System.out.println("-------------Destroy method called--------");
   }
}
