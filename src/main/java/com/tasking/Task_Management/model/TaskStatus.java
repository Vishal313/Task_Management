package com.tasking.Task_Management.model;

public class TaskStatus {
	private int task_status_id;
	private String task_type;
	private String start_date;
	private String end_date;
	
	public TaskStatus(int task_status_id, String task_type, String start_date, String end_date) {
		this.task_status_id = task_status_id;
		this.task_type = task_type;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getTask_status_id() {
		return task_status_id;
	}

	public void setTask_status_id(int task_status_id) {
		this.task_status_id = task_status_id;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}