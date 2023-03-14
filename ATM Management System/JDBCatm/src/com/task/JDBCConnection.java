package com.task;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	
	public static Connection getConnection(){
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank","postgres","1234");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return c;
	}

}
