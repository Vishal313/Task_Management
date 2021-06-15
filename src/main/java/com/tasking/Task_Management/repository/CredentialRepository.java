package com.tasking.Task_Management.repository;

import com.tasking.Task_Management.model.Credential;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tasking.Task_Management.service.*;

public class CredentialRepository implements DBQuery{
	
	public static ArrayList<Credential> findAllCredential(){
		ArrayList<Credential> credList = new ArrayList<Credential>();
		String query = GETALLCREDS;
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			
			while (result.next()) {
				int emp_id = Integer.parseInt(result.getString(1));
				String usr_name = result.getString(2);
				String pass = result.getString(3);
				credList.add(new Credential(emp_id, usr_name, pass));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return credList;
	}
	
	public static String createNewCredential(int emp_id, String usrname, String password) {
		String query = "INSERT INTO credential VALUES('"+emp_id+"', '"+usrname+"', '"+password+"')";
		return DBService.insertIntoDatabase(query);
	}
}