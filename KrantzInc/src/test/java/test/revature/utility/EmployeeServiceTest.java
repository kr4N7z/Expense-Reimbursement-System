package test.revature.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepositoryImpl;
import com.revature.service.EmployeeService;


public class EmployeeServiceTest {
	@Mock
	private EmployeeRepositoryImpl employeeRepositoryImpl;

	@InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	public void setUp() {
		employeeService = new EmployeeService();
	}

	@Before
	public void before() {
		/*
		 * We need to initialize our Mocks using the static init mocks methods
		 */
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testGetAllEmployees() {
		Mockito.when(employeeRepositoryImpl.getAllEmployees())
		.thenReturn(new ArrayList<>(Arrays.asList(new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())))));
		ArrayList<Employee> employees= employeeRepositoryImpl.getAllEmployees();
		assertEquals(employees.get(0).getEmail(), "ck@krantzinc.net");
	}
	@Test
	public void testGetAllEmployeesFail() {
		Mockito.when(employeeRepositoryImpl.getAllEmployees())
		.thenReturn(new ArrayList<>(Arrays.asList(new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())))));
		ArrayList<Employee> employees= employeeRepositoryImpl.getAllEmployees();
		assertNotEquals(employees.get(0).getEmail(), "ckkrantzinc.net");
	}
	@Test
	public void testGetEmployee() {
		String email = "ck@krantzinc.net";
		Mockito.when(employeeRepositoryImpl.getEmployee(email))
		.thenReturn( new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		Employee employee= employeeRepositoryImpl.getEmployee(email);
		assertEquals(employee.getEmployeeID(), 1);
	}
	@Test
	public void testGetEmployeeFail() {
		String email = "ck@krantzinc.net";
		Mockito.when(employeeRepositoryImpl.getEmployee(email))
		.thenReturn( new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		Employee employee= employeeRepositoryImpl.getEmployee(email);
		assertNotEquals(employee.getEmployeeID(), 2);
	}
	@Test
	public void testGetEmployeeId() {
		int id =1;
		Mockito.when(employeeRepositoryImpl.getEmployee(id))
		.thenReturn( new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		Employee employee= employeeRepositoryImpl.getEmployee(id);
		assertEquals(employee.getFirstName(), "Colton");
	}
	@Test
	public void testGetEmployeeIdFail() {
		int id =1;
		Mockito.when(employeeRepositoryImpl.getEmployee(id))
		.thenReturn( new Employee(1, 1, "manager", "ck@krantzinc.net", "test1234", "Colton", "Krantz",
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		Employee employee= employeeRepositoryImpl.getEmployee(id);
		assertNotEquals(employee.getFirstName(), "Bob");
	}
}
