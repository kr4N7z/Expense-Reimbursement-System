package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;
import com.revature.utility.Validation;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import sun.security.provider.JavaKeyStore.CaseExactJKS;

public class RequestHelper {
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) {
		ReimbursementService reimbursementService = ReimbursementService.getInstance();
		EmployeeService employeeService = EmployeeService.getInstance();
		// How do we decide what to return?
		HttpSession session = request.getSession(false);

		final String URL = request.getRequestURI();
		System.out.println(URL);

		final String RESOURCE = URL.replace("/KrantzInc/actionObject", "");
		System.out.println(RESOURCE);
		int id = Integer.valueOf(session.getAttribute("id").toString());
		String username = session.getAttribute("email").toString();
		switch (RESOURCE) {
		case "/allRequests":
			return reimbursementService.getAllReimbursementRequests();
		case "/myRequests":
			return reimbursementService.getMyReimbursementRequests(id);
		case "/employeeRequestsClosed":
			return reimbursementService.getMyReimbursementRequestsClosed(id);
		case "/myRequestsToApprove":
			return reimbursementService.getRequestsToApprove(id);
		case "/myRequestsClosed":
			return reimbursementService.getRequestsClosed(id);
		case "/allEmployees":
			return employeeService.getAllEmployees();
		case "/getInfo":
			return employeeService.getEmployee(username);
		default:
			return "No such endpoint or resource";
		}
	}

	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		final String URL = request.getRequestURI();
		System.out.println(URL);
		ReimbursementService reimbursementService = ReimbursementService.getInstance();
		EmployeeService employeeService = EmployeeService.getInstance();
		Validation validation = Validation.getInstance();
		final String RESOURCE = URL.replace("/KrantzInc/actionObject", "");
		System.out.println(RESOURCE);
		HttpSession session = request.getSession(false);
		String destination = "/KrantzInc/views/managementportal/manager.html";
		switch (RESOURCE) {
		case "/addReimbursementRequest":
			// request.get
			boolean isValid = true;
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItem image;
			byte[] tmp = null;
			Reimbursement requestToAdd = new Reimbursement();
			final String id = session.getAttribute("id").toString();
			final String managerId = session.getAttribute("managerId").toString();
			requestToAdd.setEmployee_id(Integer.valueOf(id));
			requestToAdd.setManager_id(Integer.valueOf(managerId));

			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					switch (item.getFieldName()) {
					case "inputGroupFile01":
						image = item;
						requestToAdd.setImgFile(image.get());
						break;
					case "name":
						final String name = item.getString();
						requestToAdd.setReimbursement_name(name);
						break;
					case "type":
						final String type = item.getString();
						requestToAdd.setReimbursement_type(type);
						break;
					case "cost":
						final String cost = item.getString();
						Double costAmount = Double.valueOf(cost);
						requestToAdd.setEvent_cost(costAmount);
						if (isValid) {
							isValid = validation.validAmountCheck(Double.valueOf(item.getString()));
						}
						break;
					case "reimbursedPct":
						requestToAdd.setReimbursement_percent(Double.valueOf(item.getString()));
						if (isValid) {
							isValid = validation.validPercentCheck(Integer.valueOf(item.getString()));
						}
						break;
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			if (!isValid) {
				try {
					request.getRequestDispatcher("../views/reimbursementrequest.html").include(request, response);
					out.println(
							"<script>document.getElementById('error').innerHTML='Invalid Field Don't Edit My HTML!' </script>");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				requestToAdd.setRequest_submitted(new Date(Calendar.getInstance().getTime().getTime()));
				reimbursementService.addRequest(requestToAdd);

				try {
					response.sendRedirect(destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case "/judgeRequest":
			final String requestId = request.getParameter("idSelector");
			Integer requestNum = Integer.valueOf(requestId);
			final String judge = request.getParameter("Options");
			Boolean approve = Boolean.valueOf(judge);
			reimbursementService.judgeRequest(approve, judge, requestNum);
			try {
				response.sendRedirect(destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/updateRequest":
			break;
		case "/userUpdate":
			final String updateId = session.getAttribute("id").toString();
			int eId = Integer.valueOf(updateId);
			final String email = request.getParameter("email");
			boolean validEmail = validation.validEmail(email);
			if (!validEmail) {
				try {
					request.getRequestDispatcher("../views/userdata.html").include(request, response);
					out.println(
							"<script>document.getElementById('error').innerHTML='Email Not Valid or Already Taken' </script>");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				final String firstName = request.getParameter("firstName");
				final String lastName = request.getParameter("lastName");
				employeeService.updateEmployeeInfo(email, firstName, lastName, eId);
				try {
					response.sendRedirect(destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
