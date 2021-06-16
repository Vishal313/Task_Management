package com.tasking.Task_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tasking.Task_Management.model.Task;
import com.tasking.Task_Management.repository.TaskRepository;
import com.tasking.Task_Management.service.DBService;

public class TaskController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getResponse(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("GET")) {
				getTasks();
			} else if (method.equals("POST")) {
				createTask();
			} else if (method.equals("PUT")) {
				updateTask();
			} else if (method.equals("DELETE")) {
				deleteTask();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public void getTasks() {
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String project_id = ServletActionContext.getRequest().getParameter("project_id");
		String task_id = ServletActionContext.getRequest().getParameter("task_id");
		
		ArrayList<Task> taskList = new ArrayList<Task>();		
		
		if (task_id == null) {
			taskList = TaskRepository.findTasksByProjectId(project_id);
		} else {
			taskList = TaskRepository.findTasksByTaskId(project_id, task_id);
		}
		
		response.put("taskList", taskList);
		response.put("status", "OK");
		response.put("statuscode", 200);
		res.setStatus(200);
	}
	
	public void getTaskByTaskId() {
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String project_id = ServletActionContext.getRequest().getParameter("project_id");
		String task_id = ServletActionContext.getRequest().getParameter("task_id");
		ArrayList<Task> taskList = TaskRepository.findTasksByTaskId(project_id, task_id);
		
		response.put("taskList", taskList);
		response.put("status", "OK");
		response.put("statuscode", 200);
		res.setStatus(200);
	}
	
	public void createTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.createNewTask(Integer.parseInt(map.get("task_id")), map.get("task_name"),
				Integer.parseInt(map.get("project_id")), Integer.parseInt(map.get("current_task_status_id")), 
				Integer.parseInt(map.get("is_completed")));
		
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
	
	public void updateTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.updateTask(Integer.parseInt(map.get("task_id")), map.get("task_name"),
				Integer.parseInt(map.get("project_id")), Integer.parseInt(map.get("current_task_status_id")), 
				Integer.parseInt(map.get("is_completed")));
		
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
	
	public void deleteTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.deleteTask(Integer.parseInt(map.get("task_id")));
		
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