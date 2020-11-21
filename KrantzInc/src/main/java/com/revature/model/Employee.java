package com.revature.model;
import java.sql.Timestamp;


/**
 * Data Model for employees at the bank.
 * 
 * @author Colton Krantz
 *
 */
public class Employee {
	private int employeeID;
	private int managerID;
	private String employeeType;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private double MAX_REIMBURSEMENT = 1000;
	private double pendingReimbursements;
	private double awardedReimbursements;
	private double availableReimbursement;
	private Timestamp createdDate;
	private Timestamp lastLogin;

	public Employee() {

	}

	public Employee(int employeeID, int managerID, String employeeType, String email, String password, String firstName,
			String lastName, Timestamp createdDate, Timestamp lastLogin) {
		super();
		this.employeeID = employeeID;
		this.managerID = managerID;
		this.employeeType = employeeType;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = createdDate;
		this.lastLogin = lastLogin;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getPendingReimbursements() {
		return pendingReimbursements;
	}

	public void setPendingReimbursements(double pendingReimbursements) {
		this.pendingReimbursements = pendingReimbursements;
	}

	public double getAwardedReimbursements() {
		return awardedReimbursements;
	}

	public void setAwardedReimbursements(double awardedReimbursements) {
		this.awardedReimbursements = awardedReimbursements;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", managerID=" + managerID + ", employeeType=" + employeeType
				+ ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", MAX_REIMBURSEMENT=" + MAX_REIMBURSEMENT + ", pendingReimbursements=" + pendingReimbursements
				+ ", awardedReimbursements=" + awardedReimbursements + ", availableReimbursement="
				+ availableReimbursement + ", createdDate=" + createdDate + ", lastLogin=" + lastLogin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeID != other.employeeID)
			return false;
		return true;
	}
	
}
