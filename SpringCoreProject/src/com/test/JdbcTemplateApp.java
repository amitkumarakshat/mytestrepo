package com.test;
its ok
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.*;

public class JdbcTemplateApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
		EmployeeDao1 employeeDao1 = (EmployeeDao1) context.getBean("edao");
		EmployeeDao2 employeeDao2 = (EmployeeDao2) context.getBean("empDao");
		
		
		

		Employee employee = new Employee();		
		employee.setFirstName("AAA");
		employee.setLastName("CCC");
		try {
			employeeDao2.insertEmployee(employee);
			employeeDao2.deleteEmployee(113);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		Employee emp = new Employee();		
		emp.setFirstName("Sita");
		emp.setLastName("Pandey");
		try {
			employeeDao1.insertEmployee(emp);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		Employee em = new Employee();		
		em.setFirstName("Akshat");
		em.setLastName("Choudhary");
		long var = employeeDao.addNew(em);
		System.out.println("Generated ID: " + var);
		
		Employee employee1 = new Employee();		
		employee1.setFirstName("Mohan");
		employee1.setLastName("Kumar");
		try {
			employeeDao.insertEmployee1(employee1);
			System.out.println("Insertion Done successfully");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		Employee employee2 = new Employee();		
		employee2.setFirstName("Rohan");
		employee2.setLastName("Kumar");
		try {
			employeeDao.insertEmployee2(employee2);
			System.out.println("Insertion Done successfully");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		List<Employee> employeeList = employeeDao.findAll();
		for (Employee emp1 : employeeList) {
			System.out.println("--------------------------");
			System.out.println("ID : " + emp1.getId());
			System.out.println("First Name : " + emp1.getFirstName());
			System.out.println("Last Name : " + emp1.getLastName());
		}

		System.out.println("-------------------------------");
		
		System.out.println("Total Employee table size-" + employeeDao.findTotalEmployee());
		
		System.out.println("-------------------------------");
		
		System.out.println("First Name feteched by ID-" + employeeDao.findEmployeeNameById(108));
		
		System.out.println("-------------------------------");
		
		List<Employee> employeeList2 = employeeDao.findAll2();
		for (Employee emp2 : employeeList2) {
			System.out.println("--------------------------");
			System.out.println("ID : " + emp2.getId());
			System.out.println("First Name : " + emp2.getFirstName());
			System.out.println("Last Name : " + emp2.getLastName());
		}
		
		System.out.println("--------------------------");
		Employee emp3 = employeeDao.findByEmployeeId(107);
		System.out.println("ID : " + emp3.getId());
		System.out.println("First Name : " + emp3.getFirstName());
		System.out.println("Last Name : " + emp3.getLastName());
		
		
		
		
	}

}
