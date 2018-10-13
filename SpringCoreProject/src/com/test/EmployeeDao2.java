package com.test;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class EmployeeDao2 {

	private JdbcTemplate jdbcTemplate;  
	private TransactionTemplate transactionTemplate;
	 
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	  this.transactionTemplate = transactionTemplate;
	}
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	} 
	
	private int getNextSequence(){
		String sql = "SELECT nextval('serial')";
		int seqNo = jdbcTemplate.queryForInt(sql);
		return seqNo;
	}
	
	public int insertEmployee(final Employee employee) throws SQLException {
	    
	    return transactionTemplate.execute(new TransactionCallback<Integer>() {
	    @Override
	        public Integer doInTransaction(TransactionStatus transactionStatus) {
	        try {
	        	String sql = "INSERT into EMP123(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";
		        int value = jdbcTemplate.update(sql, new Object[]{getNextSequence(), employee.getFirstName(), employee.getLastName()});      
	            return value;
	        } catch (Exception e) {
	            transactionStatus.setRollbackOnly();
	        }
	        return 0;
	        }
	    });		
	}

	public void deleteEmployee(final int eid) {
	//use TransactionCallbackWithoutResult handler if ur query doesnt result anything
	  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
		try{
		  String sql = "DELETE from EMP123 where ID = ?";
		  jdbcTemplate.update(sql, new Object[]{eid});
		}catch (Exception e) {
		  //use this to rollback exception in case of exception
		  transactionStatus.setRollbackOnly();
		}
	    }
	  });
	}
	
	
}
