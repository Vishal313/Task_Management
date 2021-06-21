package com.tasking.Task_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tasking.Task_Management.model.Employee;
import com.tasking.Task_Management.repository.EmployeeRepository;
import com.tasking.Task_Management.service.DBService;

public class EmployeeController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getEmployee(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("GET")) {
				getEmployeeByID();
			} else if (method.equals("POST")) {
				insertEmployee();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public void getEmployeeByID() {
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String employee_id = null;
		String manager_id = null;
		
		try {
			employee_id = request.getServletPath().substring(6, 7); // http://localhost:8080/Task_Management/user/2/
			manager_id = request.getServletPath().substring(9, 10); // http://localhost:8080/Task_Management/manager/2/
		} catch (Exception e) {}
		
		System.out.println(employee_id);
		
		ArrayList<Object> empList = null;
		if (employee_id != null)
			 empList = EmployeeRepository.findEmployeeById("employee_id", employee_id); 
		if (manager_id != null) 
			empList = EmployeeRepository.findEmployeeById("manager_id", manager_id);
		
		response.put("employeedetails", empList);
		res.setStatus(200);
	}
	
	public void insertEmployee() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = EmployeeRepository.createEmployee(Integer.parseInt(map.get("employee_id")), map.get("employee_name"), 
				map.get("employee_email"), Integer.parseInt(map.get("team_leader_id")),	Integer.parseInt(map.get("manager_id")),
				Integer.parseInt(map.get("hr_id")), Boolean.getBoolean(map.get("is_hr")), Boolean.getBoolean(map.get("is_manager")),
				Boolean.getBoolean(map.get("is_tl")));
		
		if (is_successfull.equals("success")) {
			response.put("message", "User Created Successfully!");
			res.setStatus(200);
		} else {
			response.put("message", "User Creation Failed!");
			res.setStatus(409);
		}
	}
}