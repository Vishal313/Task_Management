package com.tasking.Task_Management.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tasking.Task_Management.model.Employee;
import com.tasking.Task_Management.service.*;

public class EmployeeRepository implements DBQuery {
	
	public static ArrayList<Employee> findEmployeeById(String condition, String id) {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		String query = "SELECT * FROM employee WHERE "+condition+" = '"+id+"'";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int employee_id = Integer.parseInt(result.getString(1));;
				String employee_name = result.getString(2);
				String employee_email = result.getString(3);
				int current_project_id = Integer.parseInt(result.getString(4));;
				int team_leader_id = Integer.parseInt(result.getString(5));;
				int manager_id = Integer.parseInt(result.getString(6));;
				int hr_id = Integer.parseInt(result.getString(7));;
				int task_id = Integer.parseInt(result.getString(8));;
				int is_hr = Integer.parseInt(result.getString(9));;
				int is_manager = Integer.parseInt(result.getString(10));;
				int is_tl = Integer.parseInt(result.getString(11));;
				empList.add(new Employee(employee_id, employee_name, employee_email, current_project_id, team_leader_id, manager_id, hr_id, task_id, is_hr, is_manager, is_tl));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
	public static String createEmployee(int employee_id, String employee_name, String employee_email, int current_project_id, int team_leader_id, 
			int manager_id, int hr_id, int task_id, int is_hr, int is_manager, int is_tl) {
		String query = "INSERT INTO employee VALUES('"+employee_id+"', '"+employee_name+"', '"+employee_email+"', '"+current_project_id+"', '"+team_leader_id+"', '"+manager_id+"', '"+hr_id+"', '"+task_id+"', '"+is_hr+"', '"+is_manager+"', '"+is_tl+"') ";                      
		return DBService.insertIntoDatabase(query);
	}
}