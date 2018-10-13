package com.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class VehicleImpl2 implements Vehicle{
	

	@PostConstruct
	public void init(){
		System.out.println("----INIT method called111111111");
	}
   public void meth(){
	   System.out.println("BAD");
   }
   
	
   @PreDestroy
   public void cleanup(){
	   System.out.println("-------------Destroy method called-1111111111-------");
   }
}
