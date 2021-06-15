package com.tasking.Task_Management.model;

public class Credential {
	private int employee_id;
	private String user_name;
	private String password;
	
	public Credential(int employee_id, String user_name, String password) {
		this.employee_id = employee_id;
		this.user_name = user_name;
		this.password = password;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}