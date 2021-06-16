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
	
	public Map<String, Object> getResponse(){
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
		String employee_id = ServletActionContext.getRequest().getParameter("employee_id");
		String manager_id = ServletActionContext.getRequest().getParameter("manager_id");
		
		ArrayList<Employee> empList = null;
		if (employee_id != null)
			 empList = EmployeeRepository.findEmployeeById("employee_id", employee_id);
		else if (manager_id != null) {
			empList = EmployeeRepository.findEmployeeById("manager_id", manager_id);
		}
		response.put("employeedetails", empList);
		response.put("status", "OK");
		response.put("statuscode", 200);
		res.setStatus(200);
	}
	
	public void insertEmployee() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = EmployeeRepository.createEmployee(Integer.parseInt(map.get("employee_id")), map.get("employee_name"), 
				map.get("employee_email"), Integer.parseInt(map.get("current_project_id")), Integer.parseInt(map.get("team_leader_id")),
				Integer.parseInt(map.get("manager_id")), Integer.parseInt(map.get("hr_id")), Integer.parseInt(map.get("task_id")),
				Integer.parseInt(map.get("is_hr")), Integer.parseInt(map.get("is_manager")), Integer.parseInt(map.get("is_tl")));
		
		if (is_successfull.equals("success")) {
			response.put("status", "OK");
			response.put("statuscode", 200);
			res.setStatus(200);
		} else {
			response.put("status", "Conflict");
			response.put("statuscode", 409);
			res.setStatus(409);
		}
	}
}