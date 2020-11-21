package test.revature.utility;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.repository.ReimbursementRepositoryImpl;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class ReimbursementServiceTest {
	@Mock
	private ReimbursementRepositoryImpl employeeRepositoryImpl;

	@InjectMocks
	private ReimbursementService reimbursementService;

	@BeforeEach
	public void setUp() {
		reimbursementService = new ReimbursementService();
	}

	@Before
	public void before() {
		/*
		 * We need to initialize our Mocks using the static init mocks methods
		 */
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testGetAllRequests() {
		Mockito.when(reimbursementService.getAllReimbursementRequests())
		.thenReturn(new ArrayList<>(Arrays.asList(new Reimbursement(1, 1, 1, "request1", "Certification", 100, 100, 100,
				new Date(Calendar.getInstance().getTime().getTime()),true ))));
		ArrayList<Reimbursement> requests= employeeRepositoryImpl.getAllReimbursementRequests();
		assertEquals(requests.get(0).getEvent_cost(), 100);
	}
	@Test
	public void testGetAllRequestsFail() {
		Mockito.when(reimbursementService.getAllReimbursementRequests())
		.thenReturn(new ArrayList<>(Arrays.asList(new Reimbursement(1, 1, 1, "request1", "Certification", 100, 100, 100,
				new Date(Calendar.getInstance().getTime().getTime()),true ))));
		ArrayList<Reimbursement> requests= employeeRepositoryImpl.getAllReimbursementRequests();
		assertNotEquals(requests.get(0).getEvent_cost(), 1);
	}
	@Test
	public void testGetMyRequests() {
		int employeeId=1;
		Mockito.when(reimbursementService.getMyReimbursementRequests(employeeId))
		.thenReturn(new ArrayList<>(Arrays.asList(new Reimbursement(1, 1, 1, "request1", "Certification", 100, 100, 100,
				new Date(Calendar.getInstance().getTime().getTime()),true ),new Reimbursement(2, 1, 1, "request1", "Certification", 100, 200, 100,
						new Date(Calendar.getInstance().getTime().getTime()),true ) )));
		ArrayList<Reimbursement> requests= employeeRepositoryImpl.getMyReimbursementRequests(employeeId);
		assertEquals(requests.get(1).getEvent_cost(), 200);
	}
	@Test
	public void testGetMyRequestsFail() {
		int employeeId=1;
		Mockito.when(reimbursementService.getMyReimbursementRequests(employeeId))
		.thenReturn(new ArrayList<>(Arrays.asList(new Reimbursement(1, 1, 1, "request1", "Certification", 100, 100, 100,
				new Date(Calendar.getInstance().getTime().getTime()),true ),new Reimbursement(2, 1, 1, "request1", "Certification", 50, 200, 100,
						new Date(Calendar.getInstance().getTime().getTime()),true ) )));
		ArrayList<Reimbursement> requests= employeeRepositoryImpl.getMyReimbursementRequests(employeeId);
		assertNotEquals(requests.get(1).getEvent_cost(), 100);
	}
	
}
