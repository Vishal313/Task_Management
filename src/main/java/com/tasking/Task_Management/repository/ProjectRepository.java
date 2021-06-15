package com.tasking.Task_Management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tasking.Task_Management.model.*;
import com.tasking.Task_Management.service.DBQuery;
import com.tasking.Task_Management.service.DBService;

public class ProjectRepository implements DBQuery{
	
	public static ArrayList<Project> getAllProjects(){
		ArrayList<Project> projectList = new ArrayList<Project>();
		String query = GETALLPROJECTS;
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int proj_id = Integer.parseInt(result.getString(1));
				String proj_name = result.getString(2);
				int emp_id = Integer.parseInt(result.getString(3));
				int is_comp = Integer.parseInt(result.getString(4));
				projectList.add(new Project(proj_id, proj_name, emp_id, is_comp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public static String createNewProject(int project_id, String project_name, int employee_id, int is_completed) {
		String query = "INSERT INTO project VALUES('"+project_id+"', '"+project_name+"', '"+employee_id+"', '"+is_completed+"')";
		return DBService.insertIntoDatabase(query);
	}
}