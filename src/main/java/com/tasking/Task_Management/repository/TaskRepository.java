package com.tasking.Task_Management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
//				int proj_id = Integer.parseInt(result.getString(3));
				String curr_tsk_sts = result.getString(4);
				boolean tsk_iscomp = Boolean.getBoolean(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, curr_tsk_sts, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
//	public static ArrayList<Task> findTasksByProjectId(String project_id){
//		ArrayList<Task> taskList = new ArrayList<Task>();
//		String query = "SELECT * FROM task WHERE project_id = '"+project_id+"'";
//		try {
//			ResultSet result = DBService.getFromDatabase(query);
//			while (result.next()) {
//				int tsk_id = Integer.parseInt(result.getString(1));
//				String tsk_nm = result.getString(2);
////				int proj_id = Integer.parseInt(result.getString(3));
//				String curr_tsk_sts = result.getString(4);
//				boolean tsk_iscomp = Boolean.getBoolean(result.getString(5));
//				taskList.add(new Task(tsk_id, tsk_nm, curr_tsk_sts, tsk_iscomp));
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return taskList;
//	}
	
	public static Map<String, ArrayList<Task>> findTasksByProjectId(String project_id){
		ArrayList<Task> taskList = new ArrayList<Task>();
		String query = "SELECT * FROM task WHERE project_id = '"+project_id+"'";
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int tsk_id = Integer.parseInt(result.getString(1));
				String tsk_nm = result.getString(2);
//				int proj_id = Integer.parseInt(result.getString(3));
				String curr_tsk_sts = result.getString(4);
				boolean tsk_iscomp = Boolean.getBoolean(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, curr_tsk_sts, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, ArrayList<Task>> mp = new HashMap<String, ArrayList<Task>>();
		mp.put("taskList", taskList);
		return mp;
	}
	
	public static ArrayList<Task> findTasksByTaskId (String task_id){
		ArrayList<Task> taskList = new ArrayList<Task>();
		String query = "SELECT * FROM task WHERE task_id = '"+task_id+"'";
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int tsk_id = Integer.parseInt(result.getString(1));
				String tsk_nm = result.getString(2);
//				int proj_id = Integer.parseInt(result.getString(3));
				String curr_tsk_sts = result.getString(4);
				boolean tsk_iscomp = Boolean.getBoolean(result.getString(5));
				taskList.add(new Task(tsk_id, tsk_nm, curr_tsk_sts, tsk_iscomp));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}
	
	public static String createNewTask(int task_id, String task_name, int project_id, String current_task_status,boolean is_completed) {
		int is_comp = is_completed ? 1 : 0;
		String query = "INSERT INTO task VALUES('"+task_id+"', '"+task_name+"', '"+project_id+"', '"+current_task_status+"' ,'"+is_comp+"')";
		return DBService.insertIntoDatabase(query);
	}
	
	public static String updateTask(int task_id, String task_name, int project_id, String current_task_status, boolean is_completed) {
		int is_comp = is_completed ? 1 : 0;
		String query = "UPDATE task SET task_id = '"+task_id+"', task_name = '"+task_name+"', project_id = '"+project_id+"', current_task_status = '"+current_task_status+"', is_completed = '"+is_comp+"' WHERE task_id = '"+task_id+"'";
		return DBService.insertIntoDatabase(query);
	}
	
	public static String deleteTask(int task_id) {
		String query = "DELETE FROM task WHERE task_id = '"+task_id+"'";
		return DBService.insertIntoDatabase(query);
	}
	
	
}