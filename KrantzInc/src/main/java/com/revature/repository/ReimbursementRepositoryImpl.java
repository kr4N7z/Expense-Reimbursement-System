package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.ClosedReimbursementsView;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementManagerView;
import com.revature.utility.ConnectionCloser;
import com.revature.utility.ConnectionFactory;

public class ReimbursementRepositoryImpl implements ReimbursementRepository {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ReimbursementRepositoryImpl reimbursementRepositoryImpl;
	private ResultSet result = null;

	private ReimbursementRepositoryImpl() {

	}

	public static ReimbursementRepositoryImpl getInstance() {
		if (reimbursementRepositoryImpl == null) {
			reimbursementRepositoryImpl = new ReimbursementRepositoryImpl();
		}
		return reimbursementRepositoryImpl;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursementRequests() {
		ArrayList<Reimbursement> allRequests = new ArrayList<Reimbursement>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			String sqlStatement = "SELECT * FROM reimbursements ORDER BY reimbursement_id";

			result = statement.executeQuery(sqlStatement);
			while (result.next()) {
				Reimbursement nextRequest = new Reimbursement();
				nextRequest.setReimbursement_id(result.getInt(1));
				nextRequest.setEmployee_id(result.getInt(2));
				nextRequest.setManager_id(result.getInt(3));
				nextRequest.setReimbursement_name(result.getString(4));
				nextRequest.setReimbursement_type(result.getString(5));
				nextRequest.setReimbursement_percent(result.getDouble(6));
				nextRequest.setEvent_cost(result.getDouble(7));
				nextRequest.setAmount_reimbursed(result.getDouble(8));
				nextRequest.setRequest_submitted(result.getDate(9));
				nextRequest.setEnd_date(result.getDate(10));
				nextRequest.setGrade(result.getInt(11));
				nextRequest.setApproved(result.getBoolean(12));
				nextRequest.setImgFile(result.getBytes(14));
				allRequests.add(nextRequest);
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
		return allRequests;
	}

	@Override
	public ArrayList<Reimbursement> getMyReimbursementRequests(int employeeId) {
		ArrayList<Reimbursement> allRequests = new ArrayList<Reimbursement>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();

			String sqlStatement = "SELECT * FROM reimbursements WHERE employee_id=? and approved is null ORDER BY reimbursement_id";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employeeId);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Reimbursement nextRequest = new Reimbursement();
				nextRequest.setReimbursement_id(result.getInt(1));
				nextRequest.setEmployee_id(result.getInt(2));
				nextRequest.setManager_id(result.getInt(3));
				nextRequest.setReimbursement_name(result.getString(4));
				nextRequest.setReimbursement_type(result.getString(5));
				nextRequest.setReimbursement_percent(result.getDouble(6));
				nextRequest.setEvent_cost(result.getDouble(7));
				nextRequest.setAmount_reimbursed(result.getDouble(8));
				nextRequest.setRequest_submitted(result.getDate(9));
				nextRequest.setEnd_date(result.getDate(10));
				nextRequest.setGrade(result.getInt(11));
				nextRequest.setApproved(result.getBoolean(12));
				nextRequest.setImgFile(result.getBytes(14));

				allRequests.add(nextRequest);
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
		return allRequests;
	}

	@Override
	public ArrayList<ReimbursementManagerView> getRequestsToApprove(int managerId) {
		ArrayList<ReimbursementManagerView> allRequests = new ArrayList<ReimbursementManagerView>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();

			String sqlStatement = "select es.first_name, es.last_name, reimbursements.reimbursement_id, reimbursements.reimbursement_name, reimbursements.reimbursement_type, reimbursements.reimbursement_percent, reimbursements.event_cost, reimbursements.amount_reimbursed, reimbursements.request_submitted,reimbursements.approved, reimbursements.receipt_img "
					+ "from reimbursements "
					+ "inner join employees es "
					+ "on reimbursements.employee_id = es.employee_id "
					+ "where es.manager_id =? and reimbursements.approved is null;";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, managerId);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				ReimbursementManagerView nextRequest = new ReimbursementManagerView();
				nextRequest.setFirstName(result.getString(1));
				nextRequest.setLastName(result.getString(2));
				nextRequest.setReimbursement_id(result.getInt(3));
				//nextRequest.setManager_id(result.getInt(3));
				nextRequest.setReimbursement_name(result.getString(4));
				nextRequest.setReimbursement_type(result.getString(5));
				nextRequest.setReimbursement_percent(result.getDouble(6));
				nextRequest.setEvent_cost(result.getDouble(7));
				nextRequest.setAmount_reimbursed(result.getDouble(8));
				nextRequest.setRequest_submitted(result.getDate(9));
				nextRequest.setApproved(result.getBoolean(10));
				nextRequest.setImgFile(result.getBytes(11));
				allRequests.add(nextRequest);
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
		return allRequests;
	}

	@Override
	public void addRequest(Reimbursement requestToAdd) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();

			connection.setAutoCommit(false);

			// The sql Statement to add a new entry to the database
			String sqlStatement = "insert into reimbursements (	employee_id, manager_id, reimbursement_name, reimbursement_type, reimbursement_percent, "
					+ "event_cost, request_submitted, end_date, receipt_img) " 
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, requestToAdd.getEmployee_id());
			preparedStatement.setInt(2, requestToAdd.getManager_id());
			preparedStatement.setString(3, requestToAdd.getReimbursement_name());
			preparedStatement.setString(4, requestToAdd.getReimbursement_type());
			preparedStatement.setDouble(5, requestToAdd.getReimbursement_percent());
			preparedStatement.setDouble(6, requestToAdd.getEvent_cost());
			preparedStatement.setDate(7, requestToAdd.getRequest_submitted());
			preparedStatement.setDate(8, requestToAdd.getEnd_date());
			preparedStatement.setBytes(9, requestToAdd.getImgFile());
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
	public void judgeRequest(boolean approve, String reason, int requestId) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			String sqlStatement = "UPDATE reimbursements SET approved= ?, reason= ?  WHERE reimbursement_id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setBoolean(1, approve);
			preparedStatement.setString(2, reason);
			preparedStatement.setInt(3, requestId);
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
	public void updateRequest(int grade, int requestId) {
		try {
			Class.forName(System.getenv("DbName"));
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			String sqlStatement = "UPDATE reimbursements SET grade= ?  WHERE request_id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, grade);
			preparedStatement.setInt(2, requestId);
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
	public ArrayList<ClosedReimbursementsView> getRequestsClosed(int managerId) {
		ArrayList<ClosedReimbursementsView> allRequests = new ArrayList<ClosedReimbursementsView>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();

			String sqlStatement = "select es.first_name, es.last_name, reimbursements.manager_id, reimbursements.reimbursement_id, reimbursements.reimbursement_name, reimbursements.reimbursement_type, reimbursements.reimbursement_percent, reimbursements.event_cost, reimbursements.amount_reimbursed, reimbursements.request_submitted,reimbursements.approved, reimbursements.receipt_img "
					+ "from reimbursements "
					+ "inner join employees es "
					+ "on reimbursements.employee_id = es.employee_id "
					+ "where reimbursements.approved is not null;";
			preparedStatement = connection.prepareStatement(sqlStatement);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				ClosedReimbursementsView nextRequest = new ClosedReimbursementsView();
				nextRequest.setFirstName(result.getString(1));
				nextRequest.setLastName(result.getString(2));
				nextRequest.setManager_id(result.getInt(3));
				nextRequest.setReimbursement_id(result.getInt(4));
				nextRequest.setReimbursement_name(result.getString(5));
				nextRequest.setReimbursement_type(result.getString(6));
				nextRequest.setReimbursement_percent(result.getDouble(7));
				nextRequest.setEvent_cost(result.getDouble(8));
				nextRequest.setAmount_reimbursed(result.getDouble(9));
				nextRequest.setRequest_submitted(result.getDate(10));
				nextRequest.setApproved(result.getBoolean(11));
				nextRequest.setImgFile(result.getBytes(12));
				allRequests.add(nextRequest);
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
		return allRequests;

	}

	@Override
	public ArrayList<Reimbursement> getMyReimbursementRequestsClosed(int employeeId) {
		ArrayList<Reimbursement> allRequests = new ArrayList<Reimbursement>();
		try {
			Class.forName(System.getenv("DbName"));

			connection = ConnectionFactory.getConnection();

			String sqlStatement = "SELECT * FROM reimbursements WHERE employee_id=? and approved is not null ORDER BY reimbursement_id";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employeeId);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Reimbursement nextRequest = new Reimbursement();
				nextRequest.setReimbursement_id(result.getInt(1));
				nextRequest.setEmployee_id(result.getInt(2));
				nextRequest.setManager_id(result.getInt(3));
				nextRequest.setReimbursement_name(result.getString(4));
				nextRequest.setReimbursement_type(result.getString(5));
				nextRequest.setReimbursement_percent(result.getDouble(6));
				nextRequest.setEvent_cost(result.getDouble(7));
				nextRequest.setAmount_reimbursed(result.getDouble(8));
				nextRequest.setRequest_submitted(result.getDate(9));
				nextRequest.setEnd_date(result.getDate(10));
				nextRequest.setGrade(result.getInt(11));
				nextRequest.setApproved(result.getBoolean(12));
				nextRequest.setImgFile(result.getBytes(14));

				allRequests.add(nextRequest);
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
		return allRequests;
	}

}
