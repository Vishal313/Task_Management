package com.tasking.Task_Management.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tasking.Task_Management.controller.CredentialController;
import com.tasking.Task_Management.service.*;

public class EmployeeRepository implements DBQuery {
	
	public static ArrayList<Object> findEmployeeById(String condition, String id) {
		ArrayList<Object> empList = new ArrayList<Object>();
		String query = "SELECT * FROM employee WHERE "+condition+" = '"+id+"'";
		
		if (condition.equals("allemp"))
			query = "SELECT * FROM employee";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int employee_id = Integer.parseInt(result.getString(1));
				String employee_name = result.getString(2);
				String employee_email = result.getString(3);
				String team_leader_id = result.getString(4);
				String manager_id = result.getString(5);
				String hr_id = result.getString(6);
				boolean is_hr = result.getString(7).equals("1") ? true : false;
				boolean is_manager = result.getString(8).equals("1") ? true : false;
				boolean is_tl = result.getString(9).equals("1") ? true : false;
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
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
	public static String createEmployee(String employee_name, String employee_email, String team_leader_id, 
			String manager_id, String hr_id, boolean is_hr, boolean is_manager, boolean is_tl) {
		
		ResultSet result = DBService.getFromDatabase(GETEMPLOYEEID);
		String newEmpId = null;
		try {
			while (result.next())
				newEmpId = result.getString(1);
		} catch (SQLException e) {}
		
		String query = null;
		if (is_manager) {
			query = "INSERT INTO employee VALUES('"+newEmpId+"', '"+employee_name+"', '"+employee_email+"', -1, '"+newEmpId+"', '"+hr_id+"', 0, 1, 0)";
		} else if (is_tl) {
			query = "INSERT INTO employee VALUES('"+newEmpId+"', '"+employee_name+"', '"+employee_email+"', '"+newEmpId+"', '"+manager_id+"', '"+hr_id+"', 0, 0, 1)";
		} else {
			query = "INSERT INTO employee VALUES('"+newEmpId+"', '"+employee_name+"', '"+employee_email+"', '"+team_leader_id+"', '"+manager_id+"', '"+hr_id+"', 0, 0, 0)";
		}
		CredentialRepository.createNewCredential(Integer.parseInt(newEmpId), employee_name, CredentialController.getMd5(employee_name));
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
	
	public static String findDesignation(String employee_id) {
		String query = "SELECT * FROM employee WHERE employee_id = '"+employee_id+"'";
		String desgn = "";
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				String is_hr = result.getString(7);
				String is_manager = result.getString(8);
				String is_tl = result.getString(9);
				if (is_hr.equals("1"))
					desgn = "HR";
				else if (is_manager.equals("1"))
					desgn = "Manager";
				else if (is_tl.equals("1"))
					desgn = "TL";
				else 
					desgn = "Employee";
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return desgn;
	}
}