package com.tasking.Task_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.tasking.Task_Management.repository.EmployeeRepository;
import com.tasking.Task_Management.repository.ProjectRepository;
import com.tasking.Task_Management.repository.TaskRepository;
import com.tasking.Task_Management.service.DBService;
import com.tasking.Task_Management.model.Task;

public class EmployeeController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getEmployee(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		if (method.equals("GET")) {
			if (ServletActionContext.getRequest().getServletPath().contains("task"))
				getTaskByEmployeeID();
			else
				getEmployeeByID();
		} else if (method.equals("POST")) {
			insertEmployee();
		}
		return "success";
	}
	
	public void getTaskByEmployeeID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String servletPath = request.getServletPath(); // http://localhost:8080/Task_Management/user/12/task/
		String employee_id = servletPath.split("/")[2];
		ArrayList<Task> taskList = TaskRepository.findTaskByEmployeeId(employee_id);
		response.put("taskDetails", taskList);
	}
	
	public void getEmployeeByID() {
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String servletPath = request.getServletPath();
		ArrayList<Object> empList = null;
		
		if (servletPath.contains("user/")) {
			String employee_id = servletPath.split("/")[2]; // http://localhost:8080/Task_Management/user/2/
			empList = EmployeeRepository.findEmployeeById("employee_id", employee_id);
		}
		else if (servletPath.contains("user")) {
			empList = EmployeeRepository.findEmployeeById("allemp", "0");
		}
		else if (servletPath.contains("manager")) {
			String manager_id = servletPath.split("/")[2]; // http://localhost:8080/Task_Management/manager/2/
			empList = EmployeeRepository.findEmployeeById("manager_id", manager_id);
			response.put("projectList", ProjectRepository.getProjectByManagerId(manager_id));
		}
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