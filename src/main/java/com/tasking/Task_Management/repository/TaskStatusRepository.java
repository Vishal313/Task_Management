package com.tasking.Task_Management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.tasking.Task_Management.model.TaskStatus;
import com.tasking.Task_Management.service.*;

public class TaskStatusRepository implements DBQuery {
	
	public static ArrayList<TaskStatus> findTaskStatusByTaskId(String task_id){
		ArrayList<TaskStatus> taskStatusList = new ArrayList<TaskStatus>();
//		String query = GETALLTASKSTATUS;
		String query = "SELECT * FROM taskstatus WHERE task_id = '"+task_id+"' ";
		
		try {
			ResultSet result = DBService.getFromDatabase(query);
			while (result.next()) {
				int task_status_id = Integer.parseInt(result.getString(1));
//				int task_idd = Integer.parseInt(result.getString(2));
				String task_type = result.getString(3);
				String start_date = result.getString(4);
				String end_date = result.getString(5);
				taskStatusList.add(new TaskStatus(task_status_id, task_type, start_date, end_date));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return taskStatusList;
	}
	
	public static String createNewTaskStatus(int task_status_id, int task_id, String task_type, String start_date, String end_date) {
		String query = "INSERT INTO taskstatus VALUES('"+task_status_id+"', '"+task_id+"' , '"+task_type+"', '"+start_date+"', '"+end_date+"')";
		return DBService.insertIntoDatabase(query);
	}
	
	public static String updateTaskStatus(int task_status_id, int task_id, String task_type, String start_date, String end_date) {
		String query = "UPDATE taskstatus SET task_status_id = '"+task_status_id+"', task_id = '"+task_id+"',task_type = '"+task_type+"', start_date = '"+start_date+"', end_date = '"+end_date+"'";
		return DBService.insertIntoDatabase(query);
	}

	public static String deleteTaskStatus(int task_status_id) {
		String query = "DELETE FROM taskstatus WHERE task_status_id = '"+task_status_id+"'";
		return DBService.insertIntoDatabase(query);
	}
}