package com.tasking.Task_Management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tasking.Task_Management.model.*;
import com.tasking.Task_Management.service.DBQuery;
import com.tasking.Task_Management.service.DBService;

public class ProjectRepository implements DBQuery{
	
	public static ArrayList<Object> getAllProjects(){
		ArrayList<Object> projectList = new ArrayList<Object>();
		String query = GETALLPROJECTS;
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int proj_id = Integer.parseInt(result.getString(1));
				String proj_name = result.getString(2);
				boolean is_comp = Boolean.getBoolean(result.getString(3));
				projectList.add(new Project(proj_id, proj_name, is_comp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public static ArrayList<Object> getProjectById(String project_id){
		ArrayList<Object> projectList = new ArrayList<Object>();
		String query = "SELECT * FROM project WHERE project_id = '"+project_id+"'";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int proj_id = Integer.parseInt(result.getString(1));
				String proj_name = result.getString(2);
				boolean is_comp = Boolean.getBoolean(result.getString(3));
				projectList.add(new Project(proj_id, proj_name, is_comp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public static String createNewProject(int project_id, String project_name, boolean is_completed) {
		int is_comp = is_completed ? 1 : 0;
		String query = "INSERT INTO project VALUES('"+project_id+"', '"+project_name+"', '"+is_comp+"')";
		return DBService.insertIntoDatabase(query);
	}
}