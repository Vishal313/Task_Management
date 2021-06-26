package com.tasking.Task_Management.controller;

import com.tasking.Task_Management.service.*;
import com.tasking.Task_Management.repository.*;
import java.util.HashMap;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ProjectController {
	private ArrayList<Object> projects = new ArrayList<Object>();
	
	public ArrayList<Object> getProjects(){
		return projects;
	}
	
	public String execute() {
		String method = ServletActionContext.getRequest().getMethod();
		if (method.equals("GET")) 
			getProjects1();
		else if (method.equals("POST")) 
			createProject();
		return "success";
	}
	
	public void getProjects1() {
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		try {
			String project_id = request.getServletPath().split("/")[2]; // http://localhost:8080/Task_Management/project/12/
			projects = ProjectRepository.getProjectById(project_id);
			projects.add(TaskRepository.findTasksByProjectId(project_id));
		} catch (Exception e) {
			projects = ProjectRepository.getAllProjects(); // http://localhost:8080/Task_Management/project
		}
		res.setStatus(200);
	}
	
	public void createProject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = ProjectRepository.createNewProject(map.get("project_name"), map.get("employee_id"));
		
		if (is_successfull.equals("success")) {
			projects.add("Project Created Successfully!");
			res.setStatus(200);
		} else {
			res.setStatus(409);
			res.setHeader("message", "Cannot Create Project!");
			projects.add("Cannot Create Project!");
		}
	}
}