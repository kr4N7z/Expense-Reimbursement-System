package com.revature.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.model.Employee;

public interface EmployeeRepository {
	public ArrayList<Employee> getAllEmployees();
	public Employee getEmployee(String email);
	public void createNewEmployee(Employee employeeToAdd);
	
	public void updateEmployee(int employeeID, Timestamp loginTime);
	public void updateEmployeeInfo(String email, String firstName,String lastName, int employeeID);
	public Employee loginValidation(String username);
}
