package com.test;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
mohan
public class EmployeeDao1 {

	private JdbcTemplate jdbcTemplate;  
	private PlatformTransactionManager platformTransactionManager;

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
	  this.platformTransactionManager = platformTransactionManager;
	}
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	} 
	
	private int getNextSequence(){
		String sql = "SELECT nextval('serial')";
		int seqNo = jdbcTemplate.queryForInt(sql);
		return seqNo;
	}
	
	public void insertEmployee(final Employee employee) throws SQLException {
	     
	    DefaultTransactionDefinition defaultTransactionDefinition = new    DefaultTransactionDefinition();
	    TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
		try{
			String sql = "INSERT into EMP123(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";
	        jdbcTemplate.update(sql, new Object[]{getNextSequence(), employee.getFirstName(), employee.getLastName()}); 
			platformTransactionManager.commit(transactionStatus);
		}catch (Exception e) {
			platformTransactionManager.rollback(transactionStatus);
		}
			
	}
	
}
