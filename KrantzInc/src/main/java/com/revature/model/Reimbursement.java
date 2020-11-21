package com.revature.model;

import java.sql.Date;

public class Reimbursement {
	private int reimbursement_id;
	private int employee_id;
	private int manager_id;
	private String reimbursement_name;
	private String reimbursement_type;
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

	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursement_id, int employee_id, int manager_id, String reimbursement_name,
			String reimbursement_type, double reimbursement_percent, double event_cost, double amount_reimbursed,
			Date request_submitted, Boolean approved) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.employee_id = employee_id;
		this.manager_id = manager_id;
		this.reimbursement_name = reimbursement_name;
		this.reimbursement_type = reimbursement_type;
		this.reimbursement_percent = reimbursement_percent;
		this.event_cost = event_cost;
		this.amount_reimbursed = amount_reimbursed;
		this.request_submitted = request_submitted;
		this.approved = approved;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
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

	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", employee_id=" + employee_id + ", manager_id="
				+ manager_id + ", reimbursement_name=" + reimbursement_name + ", reimbursement_type="
				+ reimbursement_type + ", reimbursement_percent=" + reimbursement_percent + ", event_cost=" + event_cost
				+ ", amount_reimbursed=" + amount_reimbursed + ", request_submitted=" + request_submitted
				+ ", end_date=" + end_date + ", grade=" + grade + ", approved=" + approved + ", reason=" + reason + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbursement_id;
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
		Reimbursement other = (Reimbursement) obj;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		return true;
	}

}
