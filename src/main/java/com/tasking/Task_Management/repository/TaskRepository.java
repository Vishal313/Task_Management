package com.tasking.Task_Management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.tasking.Task_Management.model.Task;
import com.tasking.Task_Management.service.*;

public class TaskRepository implements DBQuery{
	
	public static ArrayList<Task> findAllTasks(){
		ArrayList<Task> taskList = new ArrayList<Task>();
		String query = GETALLTASK;
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int tsk_id = Integer.parseInt(result.getString(1));
				String tsk_nm = result.getString(2);
				int proj_id = Integer.parseInt(result.getString(3));
				int cur_tas_sts_id = Integer.parseInt(result.getString(4));
				int tsk_iscomp = Integer.parseInt(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, proj_id, cur_tas_sts_id, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	public static ArrayList<Task> findTasksByProjectId(String project_id){
		ArrayList<Task> taskList = new ArrayList<Task>();
		String query = "SELECT * FROM task WHERE project_id = '"+project_id+"'";
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int tsk_id = Integer.parseInt(result.getString(1));
				String tsk_nm = result.getString(2);
				int proj_id = Integer.parseInt(result.getString(3));
				int cur_tas_sts_id = Integer.parseInt(result.getString(4));
				int tsk_iscomp = Integer.parseInt(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, proj_id, cur_tas_sts_id, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	public static ArrayList<Task> findTasksByTaskId (String project_id, String task_id){
		ArrayList<Task> taskList = new ArrayList<Task>();
		String query = "SELECT * FROM task WHERE project_id = '"+project_id+"' AND task_id = '"+task_id+"'";
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int tsk_id = Integer.parseInt(result.getString(1));
				String tsk_nm = result.getString(2);
				int proj_id = Integer.parseInt(result.getString(3));
				int cur_tas_sts_id = Integer.parseInt(result.getString(4));
				int tsk_iscomp = Integer.parseInt(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, proj_id, cur_tas_sts_id, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	public static String createNewTask(int task_id, String task_name, int project_id, int current_task_status_id, int is_completed) {
		String query = "INSERT INTO task VALUES('"+task_id+"', '"+task_name+"', '"+project_id+"','"+current_task_status_id+"', '"+is_completed+"')";
		return DBService.insertIntoDatabase(query);
	}
	
	public static String updateTask(int task_id, String task_name, int project_id, int current_task_status_id, int is_completed) {
		String query = "UPDATE task SET task_id = '"+task_id+"', task_name = '"+task_name+"', project_id = '"+project_id+"', current_task_status_id = '"+current_task_status_id+"', is_completed = '"+is_completed+"' WHERE task_id = '"+task_id+"'";
		return DBService.insertIntoDatabase(query);
	}
	
	public static String deleteTask(int task_id) {
		String query = "DELETE FROM task WHERE task_id = '"+task_id+"'";
		return DBService.insertIntoDatabase(query);
	}
}