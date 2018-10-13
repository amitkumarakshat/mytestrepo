package com.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Test {

	public static void main(String[] args) {
		System.out.println("-------------main method start-");
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//StudentService studentService = (StudentService) context.getBean("studentService");
		//System.out.println(studentService.getStudent().getName());
		
		//VehicleTest vehicleTest = (VehicleTest) context.getBean("vehicleTest");
		//vehicleTest.callMe();
		
	/*	System.out.println("-----------------------");
		TestService test = (TestService)context.getBean("testService");
		test.logic();
		*/
		
		Student s1 = (Student) context.getBean("student");
		s1.setName("tttt");
		System.out.println(s1.getName());
		
		Student s2 = (Student) context.getBean("student");
		System.out.println("----------::"+ s2.getName());
		
		
		context.registerShutdownHook();
		System.out.println("-------------main method ends-------------");
		
	}

}
