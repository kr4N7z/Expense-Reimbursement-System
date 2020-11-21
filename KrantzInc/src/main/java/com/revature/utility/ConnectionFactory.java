package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	// don't want to create instances of this
	// one static method that will return a jdbc connection
	private static Connection connection;

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(System.getenv("URL"), System.getenv("Usernamepsql"),
					System.getenv("passwordPSQL"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
