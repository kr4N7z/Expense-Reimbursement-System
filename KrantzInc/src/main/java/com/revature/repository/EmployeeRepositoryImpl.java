package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.model.Employee;

import com.revature.utility.ConnectionCloser;
import com.revature.utility.ConnectionFactory;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static EmployeeRepositoryImpl bankEmployeeRepositoryImpl;
	private ResultSet result = null;

	private EmployeeRepositoryImpl() {

	}

	public static EmployeeRepositoryImpl getInstance() {
		if (bankEmployeeRepositoryImpl == null) {
			bankEmployeeRepositoryImpl = new EmployeeRepositoryImpl();
		}
		return bankEmployeeRepositoryImpl;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> allEmployees = new ArrayList<Employee>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			String sqlStatement = "SELECT * FROM employees ORDER BY manager_id, employee_id";

			result = statement.executeQuery(sqlStatement);
			while (result.next()) {
				Employee nextEmployee = new Employee();
				nextEmployee.setEmployeeID(result.getInt("employee_id"));
				nextEmployee.setManagerID(result.getInt("manager_id"));
				nextEmployee.setEmployeeType(result.getString("employee_type"));
				nextEmployee.setPassword("**********");
				nextEmployee.setEmail(result.getString("email"));
				nextEmployee.setFirstName(result.getString("first_name"));
				nextEmployee.setLastName(result.getString("last_name"));
				nextEmployee.setCreatedDate(result.getTimestamp("created_on"));
				nextEmployee.setLastLogin(result.getTimestamp("last_login"));
				nextEmployee.setPendingReimbursements(result.getDouble("pending_reimbursements"));
				nextEmployee.setAwardedReimbursements(result.getDouble("awarded_reimbursements"));
				nextEmployee.setAvailableReimbursement(result.getDouble("available_reimbursement"));
				allEmployees.add(nextEmployee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(connection);
			ConnectionCloser.closeResource(statement);
			ConnectionCloser.closeResource(result);
		}
		return allEmployees;
	}

	
	@Override
	public Employee getEmployee(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	public Employee getEmployee(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewEmployee(Employee employeeToAdd) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();

			connection.setAutoCommit(false);

			// The sql Statement to add a new entry to the database

			String sqlStatement = "INSERT INTO employees (manager_id, employee_type, email, password, first_name, last_name, created_on) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employeeToAdd.getManagerID());
			preparedStatement.setString(2, employeeToAdd.getEmployeeType());
			preparedStatement.setString(3, employeeToAdd.getPassword());
			preparedStatement.setString(4, employeeToAdd.getEmail());
			preparedStatement.setString(5, employeeToAdd.getFirstName());
			preparedStatement.setString(6, employeeToAdd.getLastName());
			preparedStatement.setTimestamp(7, employeeToAdd.getCreatedDate());

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(connection);
			ConnectionCloser.closeResource(preparedStatement);
		}
		System.out.println("Employee succuesfully created! Your Employee can now log into their account");

	}

//	@Override
//	public void deleteEmployee(int employeeID) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void updateEmployee(int employeeID, Timestamp loginTime) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			String sqlStatement = "UPDATE employees SET last_login= ?  WHERE employee_id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setTimestamp(1, loginTime);
			preparedStatement.setInt(2, employeeID);
			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			ConnectionCloser.closeResource(connection);
			ConnectionCloser.closeResource(preparedStatement);
		}

	}

	@Override
	public Employee loginValidation(String email) {
		Employee returnEmployee = null;
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			String sqlStatement = "SELECT * FROM employees" + " WHERE email =?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, email);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				returnEmployee = new Employee();
				returnEmployee.setEmployeeID(result.getInt("employee_id"));
				returnEmployee.setManagerID(result.getInt("manager_id"));
				returnEmployee.setEmployeeType(result.getString("employee_type"));
				returnEmployee.setPassword(result.getString("password"));
				returnEmployee.setEmail(result.getString("email"));
				returnEmployee.setFirstName(result.getString("first_name"));
				returnEmployee.setLastName(result.getString("last_name"));
				returnEmployee.setPendingReimbursements(result.getDouble("pending_reimbursements"));
				returnEmployee.setAwardedReimbursements(result.getDouble("awarded_reimbursements"));
				returnEmployee.setAvailableReimbursement(result.getDouble("available_reimbursement"));
				returnEmployee.setCreatedDate(result.getTimestamp("created_on"));
				returnEmployee.setLastLogin(result.getTimestamp("last_login"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionCloser.closeResource(connection);
			ConnectionCloser.closeResource(preparedStatement);
			ConnectionCloser.closeResource(result);
		}

		return returnEmployee;
	}

	@Override
	public void updateEmployeeInfo(String email,String firstName, String lastName, int employeeID) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			String sqlStatement = "UPDATE employees SET email=?, first_name= ?, last_name=?  WHERE employee_id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setInt(4, employeeID);
			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			ConnectionCloser.closeResource(connection);
			ConnectionCloser.closeResource(preparedStatement);
		}

	}

}
