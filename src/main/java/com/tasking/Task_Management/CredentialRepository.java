package com.tasking.Task_Management;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CredentialRepository{
	private static ArrayList<Credential> credList = new ArrayList<Credential>();
	
	public CredentialRepository(){
		String query = "SELECT * FROM credential";
		
		try {
			ResultSet result = getFromDatabase(query);
			
			while (result.next()) {
				int emp_id = Integer.parseInt(result.getString(1));
				String usr_name = result.getString(2);
				String pass = result.getString(3);
				credList.add(new Credential(emp_id, usr_name, pass));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Credential> findAllCredential(){
		return credList;
	}
	
	public ResultSet getFromDatabase(String query) {
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
	
}