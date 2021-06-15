package com.tasking.Task_Management.model;

public class Project {
	private int project_id;
	private String project_name;
	private int employee_id;
	private int is_completed;
	
	public Project(int project_id, String project_name, int employee_id, int is_completed) {
		this.project_id = project_id;
		this.project_name = project_name;
		this.employee_id = employee_id;
		this.is_completed = is_completed;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int isIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	
	
}