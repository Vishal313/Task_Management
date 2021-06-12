package com.tasking.Task_Management;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CredentialRepository{
	private static ArrayList<Credential> credList = new ArrayList<Credential>();
	
	public CredentialRepository(){
		credList = new ArrayList<Credential>();
		String query = "SELECT * FROM credential";
		
		try {
			ResultSet result = JDBC.getFromDatabase(query);
			
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
	
	public void createNewCredential(int emp_id, String usrname, String password) {
		String query = "INSERT INTO credential VALUES('"+emp_id+"', '"+usrname+"', '"+password+"')";
		JDBC.insertIntoDatabase(query);
	}
}