package com.tasking.Task_Management.model;

public class Employee {
	private int employee_id;
	private String employee_name;
	private String employee_designation;
	private int current_project_id;
	private int team_leader_id;
	private int manager_id;
	private int hr_id;
	private int task_id;
	private boolean is_hr;
	private boolean is_manager;
	private boolean is_tl;
	
	public Employee(int employee_id, String employee_name, String employee_designation, int current_project_id,
			int team_leader_id, int manager_id, int hr_id, int task_id, boolean is_hr, boolean is_manager,
			boolean is_tl) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_designation = employee_designation;
		this.current_project_id = current_project_id;
		this.team_leader_id = team_leader_id;
		this.manager_id = manager_id;
		this.hr_id = hr_id;
		this.task_id = task_id;
		this.is_hr = is_hr;
		this.is_manager = is_manager;
		this.is_tl = is_tl;
	}
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_designation() {
		return employee_designation;
	}
	public void setEmployee_designation(String employee_designation) {
		this.employee_designation = employee_designation;
	}
	public int getCurrent_project_id() {
		return current_project_id;
	}
	public void setCurrent_project_id(int current_project_id) {
		this.current_project_id = current_project_id;
	}
	public int getTeam_leader_id() {
		return team_leader_id;
	}
	public void setTeam_leader_id(int team_leader_id) {
		this.team_leader_id = team_leader_id;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getHr_id() {
		return hr_id;
	}
	public void setHr_id(int hr_id) {
		this.hr_id = hr_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public boolean isIs_hr() {
		return is_hr;
	}
	public void setIs_hr(boolean is_hr) {
		this.is_hr = is_hr;
	}
	public boolean isIs_manager() {
		return is_manager;
	}
	public void setIs_manager(boolean is_manager) {
		this.is_manager = is_manager;
	}
	public boolean isIs_tl() {
		return is_tl;
	}
	public void setIs_tl(boolean is_tl) {
		this.is_tl = is_tl;
	}
} 