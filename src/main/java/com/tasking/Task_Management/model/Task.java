package com.tasking.Task_Management.model;

public class Task {
	private int task_id;
	private String task_name;
//	private int project_id;
	private String current_task_status;
	private boolean is_completed;
	
//	public Task(int task_id, String task_name, int project_id, String current_task_status, boolean is_completed) {
//		this.task_id = task_id;
//		this.task_name = task_name;
//		this.project_id = project_id;
//		this.current_task_status = current_task_status;
//		this.is_completed = is_completed;
//	}
	
	public Task(int task_id, String task_name, String current_task_status, boolean is_completed) {
		this.task_id = task_id;
		this.task_name = task_name;
		this.current_task_status = current_task_status;
		this.is_completed = is_completed;
	}
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
//	public int getProject_id() {
//		return project_id;
//	}
//	public void setProject_id(int project_id) {
//		this.project_id = project_id;
//	}
	public String getCurrent_task_status() {
		return current_task_status;
	}
	public void setCurrent_task_status(String current_task_status) {
		this.current_task_status = current_task_status;
	}
	public boolean isIs_completed() {
		return is_completed;
	}
	public void setIs_completed(boolean is_completed) {
		this.is_completed = is_completed;
	}
}