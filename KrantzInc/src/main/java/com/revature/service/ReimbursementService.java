package com.revature.service;

import java.util.ArrayList;

import com.revature.model.ClosedReimbursementsView;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementManagerView;
import com.revature.repository.ReimbursementRepository;
import com.revature.repository.ReimbursementRepositoryImpl;

public class ReimbursementService {
	private static ReimbursementService reimbursementService;
	private ReimbursementRepository reimbursementRepository;

	public ReimbursementService() {
		reimbursementRepository = ReimbursementRepositoryImpl.getInstance();
	}

	public static ReimbursementService getInstance() {
		if (reimbursementService == null) {
			reimbursementService = new ReimbursementService();
		}
		return reimbursementService;
	}

	public ArrayList<Reimbursement> getAllReimbursementRequests() {
		return reimbursementRepository.getAllReimbursementRequests();
	}

	public ArrayList<Reimbursement> getMyReimbursementRequests(int employeeId) {
		return reimbursementRepository.getMyReimbursementRequests(employeeId);
	}
	public ArrayList<Reimbursement> getMyReimbursementRequestsClosed(int employeeId) {
		return reimbursementRepository.getMyReimbursementRequestsClosed(employeeId);
	}

	public ArrayList<ReimbursementManagerView> getRequestsToApprove(int managerId) {
		return reimbursementRepository.getRequestsToApprove(managerId);
	}
	public ArrayList<ClosedReimbursementsView> getRequestsClosed(int managerId) {
		return reimbursementRepository.getRequestsClosed(managerId);
	}
	public void addRequest(Reimbursement requestToAdd) {
		reimbursementRepository.addRequest(requestToAdd);
	}

	public void judgeRequest(boolean approve, String reason, int requestId) {
		reimbursementRepository.judgeRequest(approve, reason, requestId);
	}

	public void updateRequest(int grade, int requestId) {
		reimbursementRepository.updateRequest(grade, requestId);
	}

}
