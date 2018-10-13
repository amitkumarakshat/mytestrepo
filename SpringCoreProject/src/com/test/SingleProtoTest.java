package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingleProtoTest {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SingletonBean singleton = (SingletonBean) context.getBean("singletonBean");
		
		PrototypeBean prototypeBeanA = singleton.getPrototypeBean();
		PrototypeBean prototypeBeanB = singleton.getPrototypeBean();
		
		System.out.println(prototypeBeanA);
		
		System.out.println(prototypeBeanB);
		
		System.out.println("Is prototypeBeanA and prototypeBeanA  same ? "
				+ (prototypeBeanA == prototypeBeanB));
	}

}
