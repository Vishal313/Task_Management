package com.tasking.Task_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tasking.Task_Management.model.Task;
import com.tasking.Task_Management.repository.ProjectRepository;
import com.tasking.Task_Management.repository.TaskRepository;
import com.tasking.Task_Management.service.DBService;

public class TaskController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getProjects(){
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
		HttpServletRequest request = ServletActionContext.getRequest();
		
//		String project_id = ServletActionContext.getRequest().getParameter("project_id");
//		String task_id = ServletActionContext.getRequest().getParameter("task_id");
		
		String project_id = null;
		String task_id = null;
		try {
		project_id = request.getServletPath().substring(9, 10);
		task_id = request.getServletPath().substring(16, 17);
		} 
		catch (Exception e) {}
		
//		ArrayList<Task> taskList = new ArrayList<Task>();		
		Map<String, ArrayList<Task>> taskList = new HashMap<String, ArrayList<Task>>();
		
		if (task_id == null)
			taskList = TaskRepository.findTasksByProjectId(project_id);
//		else 
//			taskList = TaskRepository.findTasksByTaskId(task_id);
		
		if (project_id != null) 
			response.put("project",ProjectRepository.getProjectById(project_id));
		
		response.put("taskList", taskList);
		res.setStatus(200);
	}
	
	public void createTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.createNewTask(map.get("task_name"),Integer.parseInt(map.get("project_id")),
				map.get("current_task_status"), map.get("is_completed").equals("true") ? true : false, Integer.parseInt(map.get("employee_id")));
		
		if (is_successfull.equals("success")) {
			response.put("Message", "Task Created Successfully!");
			res.setStatus(200);
		} else {
			response.put("Message", "Cannot Create Task!");
			res.setStatus(409);
		}
	}
	
	public void updateTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.updateTask(Integer.parseInt(map.get("task_id")), map.get("task_name"),
				Integer.parseInt(map.get("project_id")), map.get("current_task_status"), Boolean.getBoolean(map.get("is_completed")));
		
		if (is_successfull.equals("success")) {
			response.put("Message", "Task Updated Successfully!");
			res.setStatus(200);
		} else {
			response.put("Message", "Cannot Update Task!");
			res.setStatus(409);
		}
	}
	
	public void deleteTask() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = TaskRepository.deleteTask(Integer.parseInt(map.get("task_id")));
		
		if (is_successfull.equals("success")) {
			response.put("Message", "Task Deleted Successfully!");
			res.setStatus(200);
		} else {
			response.put("Message", "Cannot Delete Task!");
			res.setStatus(403);
		}
	}
}