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
				boolean is_comp = result.getString(3).equals("1") ? true : false;
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
				boolean is_comp = result.getString(3).equals("1") ? true : false;
				projectList.add(new Project(proj_id, proj_name, is_comp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public static ArrayList<Object> getProjectByManagerId(String manager_id){
		ArrayList<Object> projectList = new ArrayList<Object>();
		String query = "SELECT project.* FROM project INNER JOIN project_employee_map ON project_employee_map.project_id = project.project_id WHERE project_employee_map.employee_id = '"+manager_id+"'";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int proj_id = Integer.parseInt(result.getString(1));
				String proj_name = result.getString(2);
				boolean is_comp = result.getString(3).equals("1") ? true : false;
				projectList.add(new Project(proj_id, proj_name, is_comp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public static String createNewProject(String project_name, String employee_id) {
		String query = "INSERT INTO project(project_name, is_completed) VALUES('"+project_name+"', 0)";
		DBService.insertIntoDatabase(query);
		String q1 = "INSERT INTO project_employee_map VALUES((SELECT MAX(project_id) FROM project) , '"+employee_id+"')";
		return DBService.insertIntoDatabase(q1);
	}
}