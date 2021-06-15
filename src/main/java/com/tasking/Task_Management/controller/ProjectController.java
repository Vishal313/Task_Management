package com.tasking.Task_Management.controller;

import com.tasking.Task_Management.service.*;
import com.tasking.Task_Management.repository.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.tasking.Task_Management.model.*;


public class ProjectController {
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getResponse(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("GET")) {
				getProjects();
			} 
			else if (method.equals("POST")) {
				createProject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	public void getProjects() {
		HttpServletResponse res = ServletActionContext.getResponse();
		ArrayList<Project> projectList = ProjectRepository.getAllProjects();
		response.put("projectList", projectList);
		response.put("status", "OK");
		response.put("statuscode", 200);
		res.setStatus(200);
	}
	
	public void createProject() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = ProjectRepository.createNewProject(Integer.parseInt(map.get("project_id")), map.get("project_name"),
				Integer.parseInt(map.get("employee_id")), Integer.parseInt(map.get("is_completed")));
		
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
















