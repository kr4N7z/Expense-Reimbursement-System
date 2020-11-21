package com.revature.utility;

import java.sql.SQLException;
/**
 * 
 * Christina's connection closer class from the demo using to close my connections.
 *
 */
public class ConnectionCloser {
	
	public static void closeResource(AutoCloseable auto) {
		try{
			auto.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
