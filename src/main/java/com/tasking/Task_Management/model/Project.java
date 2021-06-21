package com.tasking.Task_Management.model;

public class Project {
	private int project_id;
	private String project_name;
	private boolean is_completed;
	
	public Project(int project_id, String project_name, boolean is_completed) {
		this.project_id = project_id;
		this.project_name = project_name;
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
	public boolean getIs_completed() {
		return is_completed;
	}
	public void setIs_completed(boolean is_completed) {
		this.is_completed = is_completed;
	}
}