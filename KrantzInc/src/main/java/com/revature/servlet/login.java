package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		employeeService = EmployeeService.getInstance();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Employee user;
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		String destination = "./views/employeeportal/employeePortal.html";
		PrintWriter out = response.getWriter();
		if (employeeService.loginValidation(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user = employeeService.getEmployee(email);
			employeeService.updateEmployee(user.getEmployeeID(), timestamp);
			session.setAttribute("id", user.getEmployeeID());
			session.setAttribute("Name", user.getFirstName() + " " + user.getLastName());
			session.setAttribute("managerId", user.getManagerID());
			session.setAttribute("employee type", user.getEmployeeType());
			if (user.getEmployeeType().equals("manager")) {
				destination = "./views/managementportal/manager.html";
			}
			response.sendRedirect(destination);
		} else {

			request.getRequestDispatcher("login.html").include(request, response);
			out.println("<script>document.getElementById('error').innerHTML='Username or Password Invalid'; </script>");
		}

		// RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		// dispatcher.forward(request, response);
	}

}
