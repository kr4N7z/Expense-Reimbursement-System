package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Validation {
	/**
	 * Verifies that the input for the welcome screen is valid.
	 * 
	 * @param optionSelected
	 * @return
	 */
	private static Validation validation;

	private Validation() {

	}

	public static Validation getInstance() {
		if (validation == null) {
			validation = new Validation();
		}
		return validation;
	}

	public boolean validAmountCheck(Double amount) {
		boolean isValid;
		if (amount > 0) {
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}

	public double amountReimbursed(double amountReuimbersed, double availableFunds) {
		if (amountReuimbersed > availableFunds) {
			double amountOver = amountReuimbersed - availableFunds;
			amountReuimbersed = amountReuimbersed - amountOver;
		}
		return amountReuimbersed;
	}

	public boolean validPercentCheck(int percent) {
		boolean isValid = false;
		if (percent > 0 && percent < 101) {
			isValid = true;
		}
		return isValid;
	}

	public boolean validEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

}
