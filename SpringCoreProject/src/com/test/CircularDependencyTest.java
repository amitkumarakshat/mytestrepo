// Spring Circular dependecny  can happen in Spring when using constructor injection; if you use other types of injections 
//you should not find this problem since the dependencies will be injected when they are needed and not on the context loading.

package com.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/*@Component("classA")
 class ClassA implements ApplicationContextAware, InitializingBean {

 private ClassB classB;

 private ApplicationContext context;

 public ClassB getClassB() {
 return classB;
 }

 @Override
 public void afterPropertiesSet() throws Exception {
 classB = context.getBean(ClassB.class);
 }

 @Override
 public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
 context = ctx;
 }
 }*/

@Component("classA")
class ClassA {
	// Start :: Solution 1
	private ClassB classB;

	// End :: Solution 1

	/*
	 * @Autowired public ClassA(ClassB classB) {
	 * this.classB = classB; }
	 */

	// Start :: Solution 1
	@Autowired
	public void setClassB(ClassB classB) {
		this.classB = classB;
	}

	public ClassB getClassB() {
		return classB;
	}
	
	private String msg = "Hello!";
	
	public String getMsg() {
        return msg;
    }
	
	// End :: Solution 1

	// Start :: Solution 2
	/*
	 * @Autowired private ClassB classB;
	 * 
	 * @PostConstruct public void init() { classB.setClassA(this); }
	 */
	// End :: Solution 2
}

@Component("classB")
class ClassB {

	private ClassA classA;

	/*
	 * @Autowired public ClassB(ClassA classA) {
	 * this.classA = classA; }
	 */

	// Start :: Solution 1
	@Autowired
	public void setClassA(ClassA circA) {
		this.classA = circA;
	}
	
	private String message = "Hi!";
	
	public String getMessage() {
        return message;
    }

	public ClassA getClassA() {
		return classA;
	}
	
	
	// End :: Solution 1
}

public class CircularDependencyTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		System.out.println("===============START===============");
		ClassA classA = (ClassA) context.getBean("classA");
		ClassB classB = (ClassB) context.getBean("classB");
	
		System.out.println(classA.getClassB().getMessage());
		System.out.println(classB.getClassA().getMsg());
	}

}
