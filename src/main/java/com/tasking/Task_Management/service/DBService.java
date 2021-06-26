package com.tasking.Task_Management.service;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DBService implements DBQuery{
	static DBHelper dbhelper = new DBHelper();
	static Connection con = dbhelper.getConn();
	
	public static ResultSet getFromDatabase(String query) {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			Statement statement = con.createStatement();
			result = statement.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String insertIntoDatabase(String query) {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	public static HashMap<String,String> pasrseRequest(HttpServletRequest request){
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    	jb.append(line);
		} catch (Exception e) {
			
		}
		String jsonString = jb.toString();
		HashMap<String,String> map = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>(){}.getType());
		return map;
	}
}




