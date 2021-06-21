package com.tasking.Task_Management.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tasking.Task_Management.model.Employee;
import com.tasking.Task_Management.service.*;

public class EmployeeRepository implements DBQuery {
	
	public static ArrayList<Object> findEmployeeById(String condition, String id) {
		ArrayList<Object> empList = new ArrayList<Object>();
		String query = "SELECT * FROM employee WHERE "+condition+" = '"+id+"'";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int employee_id = Integer.parseInt(result.getString(1));
				String employee_name = result.getString(2);
				String employee_email = result.getString(3);
				//int team_leader_id = Integer.parseInt(result.getString(4));
				String team_leader_id = result.getString(4);
				String manager_id = result.getString(5);
				String hr_id = result.getString(6);
				boolean is_hr = Boolean.getBoolean(result.getString(7));
				boolean is_manager = Boolean.getBoolean(result.getString(8));
				boolean is_tl = Boolean.getBoolean(result.getString(9));
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("employee_id", employee_id);
				mp.put("employee_name", employee_name);
				mp.put("employee_email", employee_email);
				
				Map<String, Object> tl = new HashMap<String, Object>();
				tl.put("id", team_leader_id);
				tl.put("name", getTlName("team_leader_id", team_leader_id, "is_tl"));
				mp.put("team_leader", tl);
				
				Map<String, Object> mg = new HashMap<String, Object>();
				mg.put("id", manager_id);
				mg.put("name", getTlName("manager_id", manager_id, "is_manager"));
				mp.put("manager", mg);
				
				Map<String, Object> hrr = new HashMap<String, Object>();
				hrr.put("id", hr_id);
				hrr.put("name", getTlName("hr_id", hr_id, "is_hr"));
				mp.put("hr", hrr);
				
				mp.put("is_hr", is_hr);
				mp.put("is_manager", is_manager);
				mp.put("is_tl", is_tl);
				
				empList.add(mp);
				// empList.add(new Employee(employee_id, employee_name, employee_email, team_leader_id, manager_id, hr_id, is_hr, is_manager, is_tl));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
	public static String createEmployee(int employee_id, String employee_name, String employee_email, int team_leader_id, 
			int manager_id, int hr_id, boolean is_hr, boolean is_manager, boolean is_tl) {
		int ih = is_hr ? 1: 0;
		int im = is_manager? 1: 0;
		int itl = is_tl ? 1: 0;
		String query = "INSERT INTO employee VALUES('"+employee_id+"', '"+employee_name+"', '"+employee_email+"', '"+team_leader_id+"', '"+manager_id+"', '"+hr_id+"', '"+ih+"', '"+im+"', '"+itl+"') ";                      
		return DBService.insertIntoDatabase(query);
	}
	
	public static String getTlName(String cond, String id, String cond1) {
		String query = "SELECT employee_name FROM employee WHERE "+cond+" = '"+id+"' AND "+cond1+" = 1";
		String employee_name = null;
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				employee_name = result.getString(1);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return employee_name;
	}
}