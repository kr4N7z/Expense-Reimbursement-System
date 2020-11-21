package test.revature.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.core.SetupQueryRunner;

import com.revature.utility.Validation;

class ValidationTest {
	private Validation validation;
	
	@BeforeEach
	public void setUp() {
		validation=Validation.getInstance();
	}
	
	@Test
	public void validAmountCheckTest() {
		assertEquals(true, validation.validAmountCheck(100d));

	}
	
	@Test
	public void validAmountCheckTestFalse() {
		assertEquals(false, validation.validAmountCheck(0d));
	}
	
	@Test
	public void validAmountCheckTestFalseNeegative() {
		assertEquals(false, validation.validAmountCheck(-100d));
	}
	
	@Test
	public void amountReimbursedCheck() {
		assertEquals(150, validation.amountReimbursed(150, 900));
	}
	
	@Test
	public void amountReimbursedCheckChanged() {
		assertEquals(100, validation.amountReimbursed(150, 100));
	}
	
	@Test
	public void amountReimbursedCheckChangedDecimals() {
		assertEquals(100.50, validation.amountReimbursed(150.75, 100.50));
	}
	
	@Test 
	public void validPercentCheckTestMiddle() {
		assertEquals(true, validation.validPercentCheck(80));
	}
	
	@Test 
	public void validPercentCheckTestUpperLimit() {
		assertEquals(true, validation.validPercentCheck(100));
	}
	
	@Test 
	public void validPercentCheckTestLowerLimit() {
		assertEquals(true, validation.validPercentCheck(1));
	}
	
	@Test 
	public void validPercentCheckTestFailUpperLimit() {
		assertEquals(false, validation.validPercentCheck(101));
	}
	@Test 
	public void validPercentCheckTestFailUpperLimitAbove() {
		assertEquals(false, validation.validPercentCheck(150));
	}
	
	@Test 
	public void validPercentCheckTestFailLowerLimit() {
		assertEquals(false, validation.validPercentCheck(0));
	}
	
	@Test 
	public void validPercentCheckTestFailLowerLimitBelow() {
		assertEquals(false, validation.validPercentCheck(-150));
	}
	
	@Test 
	public void validEmailCheckTest() {
		assertEquals(true, validation.validEmail("coltonkrantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("colton-krantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("colton.krantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("c.oltonkrantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("coltonkrantz1@krantzinc.net"));
	}
	@Test 
	public void validEmailCheckTestFailNoAtSign() {
		assertEquals(false, validation.validEmail("coltonkrantzkrantzinc.net"));
	}
	@Test 
	public void validEmailCheckTestFailEnding() {
		assertEquals(false, validation.validEmail("coltonkrantz@krantzincnet"));
	}
	@Test 
	public void validEmailCheckTestFailStarting() {
		assertEquals(false, validation.validEmail(".coltonkrantz@krantzincnet"));
		assertEquals(false, validation.validEmail("_coltonkrantz@krantzincnet"));

	}
	@Test 
	public void validEmailCheckTestFailStartingBlank() {
		assertEquals(false, validation.validEmail("@krantzincnet"));
	}
	@Test 
	public void validEmailCheckTestFailLeftMiddle() {
		assertEquals(false, validation.validEmail("coltonkrantz.@krantzincnet"));
		assertEquals(false, validation.validEmail("coltonkrantz_@krantzincnet"));
		
	}
	@Test 
	public void validEmailCheckTestFailRightMiddle() {
	assertEquals(false, validation.validEmail("coltonkrantz@.krantzincnet"));
	assertEquals(false, validation.validEmail("coltonkrantz@_krantzincnet"));

	}

}
