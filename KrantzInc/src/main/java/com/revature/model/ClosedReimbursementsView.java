package com.revature.model;

import java.sql.Date;

import javax.print.DocFlavor.BYTE_ARRAY;

public class ClosedReimbursementsView {
	private int reimbursement_id;
	private String firstName;
	private String lastName;
	private String reimbursement_name;
	private String reimbursement_type;
	private int manager_id;

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public Boolean getApproved() {
		return approved;
	}

	private double reimbursement_percent;
	private double event_cost;
	private double amount_reimbursed;
	private Date request_submitted;
	private Date end_date;
	private int grade;
	private Boolean approved;
	private String reason;
	private byte[] imgFile;

	public int getReimbursement_id() {
		return reimbursement_id;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
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

	public String getReimbursement_name() {
		return reimbursement_name;
	}

	public void setReimbursement_name(String reimbursement_name) {
		this.reimbursement_name = reimbursement_name;
	}

	public String getReimbursement_type() {
		return reimbursement_type;
	}

	public void setReimbursement_type(String reimbursement_type) {
		this.reimbursement_type = reimbursement_type;
	}

	public double getReimbursement_percent() {
		return reimbursement_percent;
	}

	public void setReimbursement_percent(double reimbursement_percent) {
		this.reimbursement_percent = reimbursement_percent;
	}

	public double getEvent_cost() {
		return event_cost;
	}

	public void setEvent_cost(double event_cost) {
		this.event_cost = event_cost;
	}

	public double getAmount_reimbursed() {
		return amount_reimbursed;
	}

	public void setAmount_reimbursed(double amount_reimbursed) {
		this.amount_reimbursed = amount_reimbursed;
	}

	public Date getRequest_submitted() {
		return request_submitted;
	}

	public void setRequest_submitted(Date request_submitted) {
		this.request_submitted = request_submitted;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Boolean isApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte[] getImgFile() {
		return imgFile;
	}

	public void setImgFile(byte[] imgFile) {
		this.imgFile = imgFile;
	}

}
