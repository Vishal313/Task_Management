package com.tasking.Task_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public static ResultSet getFromDatabase(String query) {
		String url = "jdbc:mysql://localhost:3306/task_management";
		String uname = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void insertIntoDatabase(String query) {
		String url = "jdbc:mysql://localhost:3306/task_management";
		String uname = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




