package com.revature.repository;

import java.util.ArrayList;

import com.revature.model.ClosedReimbursementsView;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementManagerView;

public interface ReimbursementRepository {
	public ArrayList<Reimbursement> getAllReimbursementRequests();
	public ArrayList<Reimbursement> getMyReimbursementRequests(int employeeId);
	public ArrayList<Reimbursement> getMyReimbursementRequestsClosed(int employeeId);
	public ArrayList<ReimbursementManagerView> getRequestsToApprove(int managerId);
	public ArrayList<ClosedReimbursementsView> getRequestsClosed(int managerId);
	public void addRequest(Reimbursement requestToAdd);
	public void judgeRequest(boolean approve, String reason, int requestId);
	public void updateRequest(int grade, int requestId);
	
}
