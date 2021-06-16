package com.tasking.Task_Management.model;

public class Task {
	private int task_id;
	private String task_name;
	private int project_id;
	private int current_task_status_id;
	private int is_completed;
	
	public Task(int task_id, String task_name, int project_id, int current_task_status_id, int is_completed) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.project_id = project_id;
		this.current_task_status_id = current_task_status_id;
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

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getCurrent_task_status_id() {
		return current_task_status_id;
	}

	public void setCurrent_task_status_id(int current_task_status_id) {
		this.current_task_status_id = current_task_status_id;
	}

	public int isIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	
	
}