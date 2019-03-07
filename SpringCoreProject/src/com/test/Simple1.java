package com.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

// Simple1 class
@Component
public class Simple1 implements DisposableBean,InitializingBean{

	@Override
	public void destroy() throws Exception {
		System.out.println("Simple111 Destroy called");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Simple111 init called");
		
	}

	
}
