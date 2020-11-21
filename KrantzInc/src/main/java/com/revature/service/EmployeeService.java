package com.revature.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import com.revature.model.Employee;

import com.revature.repository.EmployeeRepository;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.utility.Validation;

public class EmployeeService {
	private static EmployeeService employeeService;
	private EmployeeRepository employeeRepository;

	public EmployeeService() {
		employeeRepository = EmployeeRepositoryImpl.getInstance();
	}

	public static EmployeeService getInstance() {
		if (employeeService == null) {
			employeeService = new EmployeeService();
		}
		return employeeService;
	}
	
	public ArrayList<Employee> getAllEmployees(){
		return employeeRepository.getAllEmployees();
	}
	
	public Employee getEmployee(String username) {
		return employeeRepository.loginValidation(username);
	}
	
	public boolean loginValidation(String username, String password) {
		boolean isValid=false;
		Employee login = employeeRepository.loginValidation(username);
		if(login != null && password.equals(login.getPassword())) {
			isValid=true;
		}
		return isValid;
	}
	public void createNewEmployee() {
		
	}

	public void updateEmployee(int employeeID, Timestamp loginTime) {
		employeeRepository.updateEmployee(employeeID, loginTime);
	}
	
	public void updateEmployeeInfo(String email,String firstName,String lastName, int employeeID) {
		employeeRepository.updateEmployeeInfo(email,firstName, lastName, employeeID);
	}
}
