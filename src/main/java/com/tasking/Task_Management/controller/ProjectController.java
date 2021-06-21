package com.tasking.Task_Management.controller;

import com.tasking.Task_Management.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.tasking.Task_Management.model.Task;
import com.tasking.Task_Management.repository.*;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ProjectController {
	private String method;
	private ArrayList<Object> projects = new ArrayList<Object>();
	
	public ArrayList<Object> getProjects(){
		return projects;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("GET")) {
				getProjects1();
			} 
			else if (method.equals("POST")) {
				createProject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	public void getProjects1() {
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String project_id = null;
		try {
			project_id = request.getServletPath().substring(9, 10);
		}
		catch (Exception e) {}
		
		if (project_id != null) {
			projects = ProjectRepository.getProjectById(project_id);
			projects.add(TaskRepository.findTasksByProjectId(project_id));
		}
		else
			projects = ProjectRepository.getAllProjects();
		
		res.setStatus(200);
	}
	
	public void createProject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = ProjectRepository.createNewProject(Integer.parseInt(map.get("project_id")), map.get("project_name"),
				Boolean.getBoolean(map.get("is_completed")));
		
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