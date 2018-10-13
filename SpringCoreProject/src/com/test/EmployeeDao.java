package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
sumit
@Transactional
public class EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		System.out.println("----INIT method calledDAO------");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println("-------------Destroy method calledDAO--------");
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private int getNextSequence() {
		String sql = "SELECT nextval('serial')";
		int seqNo = jdbcTemplate.queryForInt(sql);
		return seqNo;
	}

	// insert single record
	public void insertEmployee1(final Employee employee) throws SQLException {
		String sql = "INSERT into EMP123(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, getNextSequence());
				ps.setString(2, employee.getFirstName());
				ps.setString(3, employee.getLastName());
			}
		});
	}

	// insert single record using parameter values insert
	public void insertEmployee2(final Employee employee) throws SQLException {
		String sql = "INSERT into EMP123(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql,
				new Object[] { getNextSequence(), employee.getFirstName(),
						employee.getLastName() });
	}

	// insert batch example
	public void insertBatch(final List<Employee> empList) {

		String sql = "INSERT INTO EMP123 "
				+ "(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Employee employee = empList.get(i);
				ps.setInt(1, getNextSequence());
				ps.setString(2, employee.getFirstName());
				ps.setString(3, employee.getLastName());
			}

			@Override
			public int getBatchSize() {
				return empList.size();
			}
		});
	}

	public long addNew(final Employee employee) {
		final String sql = "INSERT INTO EMP123 "
				+ "(ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)";
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, getNextSequence());
				ps.setString(2, employee.getFirstName());
				ps.setString(3, employee.getLastName());
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(psc, holder);

		int newNameId = 0;
		Map<String, Object> map = holder.getKeys();

		if (map.containsKey("ID")) {
			newNameId = (Integer) map.get("ID");
		}
		return newNameId;
	}

	public List<Employee> findAll() {
		String sql = "SELECT * FROM EMP123";
		List<Employee> employeeList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Employee.class));
		return employeeList;
	}

	public int findTotalEmployee() {
		String sql = "SELECT COUNT(*) FROM EMP123";
		int total = jdbcTemplate.queryForInt(sql);
		return total;
	}

	public String findEmployeeNameById(int empId) {
		String sql = "SELECT FIRSTNAME FROM EMP123 WHERE ID = ?";
		String firstName = (String) jdbcTemplate.queryForObject(sql,
				new Object[] { empId }, String.class);
		return firstName;

	}

	public List<Employee> findAll2() {
		String sql = "SELECT * FROM EMP123";
		List<Employee> empList = new ArrayList<Employee>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Employee employee = new Employee();
			employee.setId((Integer) (row.get("ID")));
			employee.setFirstName((String) row.get("FIRSTNAME"));
			employee.setLastName((String) row.get("LASTNAME"));
			empList.add(employee);
		}

		return empList;
	}

	public Employee findByEmployeeId(int empId) {
		String sql = "SELECT * FROM EMP123 WHERE ID = ?";
		Employee employee = (Employee) jdbcTemplate.queryForObject(sql,
				new Object[] { empId }, new EmployeeRowMapper());
		return employee;
	}

}

class EmployeeRowMapper implements RowMapper<Object> {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("ID"));
		employee.setFirstName(rs.getString("FIRSTNAME"));
		employee.setLastName(rs.getString("LASTNAME"));
		return employee;
	}
}
