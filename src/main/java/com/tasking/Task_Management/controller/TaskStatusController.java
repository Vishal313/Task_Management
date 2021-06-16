package com.tasking.Task_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.tasking.Task_Management.model.TaskStatus;
import com.tasking.Task_Management.repository.TaskRepository;
import com.tasking.Task_Management.repository.TaskStatusRepository;
import com.tasking.Task_Management.service.DBService;

public class TaskStatusController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getResponse(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("GET")) {
				getTaskStatus();
			} else if (method.equals("POST")) {
				createTaskStatus();
			} else if (method.equals("PUT")) {
				updateTaskStatus();
			} else if (method.equals("DELETE")) {
				deleteTaskStatus();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	public void getTaskStatus() {
		HttpServletResponse res = ServletActionContext.getResponse();
		ArrayList<TaskStatus> taskStatusList = TaskStatusRepository.findAllTaskStatus();
		response.put("taskStatusList", taskStatusList);
		response.put("status", "OK");
		response.put("statuscode", 200);
		res.setStatus(200);
	}
	
	public void createTaskStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		System.out.println(map.get("task_status_id"));
		
		String is_successfull = TaskStatusRepository.createNewTaskStatus(Integer.parseInt(map.get("task_status_id")), map.get("task_type"),
				map.get("start_date"), map.get("end_date"));
		
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
	
	public void updateTaskStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskStatusRepository.updateTaskStatus(Integer.parseInt(map.get("employee_id")), map.get("task_type"),
				map.get("start_date"), map.get("end_date"));
		
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
	
	public void deleteTaskStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskStatusRepository.deleteTaskStatus(Integer.parseInt(map.get("task_status_id")));
		
		if (is_successfull.equals("success")) {
			response.put("status", "OK");
			response.put("statuscode", 200);
			res.setStatus(200);
		} else {
			response.put("status", "no data available");
			response.put("statuscode", 403);
			res.setStatus(403);
		}
	}
}