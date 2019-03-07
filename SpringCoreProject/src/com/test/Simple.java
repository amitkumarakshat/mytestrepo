package com.test;

import javax.annotation.PostConstruct;



public class Simple {

	
	
	public void initMethod(){
		System.out.println("----Simple INIT method called");
	}

	public void destroyMethod(){
		System.out.println("----Simple Destroy method called");
	}
}


// @PostConstruct will only works if you will declare class as @Component OR declare bean in applicationContext.xml file as
// <bean id="simple" class="com.test.Simple"/>