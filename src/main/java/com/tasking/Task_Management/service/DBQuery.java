package com.tasking.Task_Management.service;

public interface DBQuery {
	String DBBASEURL = "jdbc:mysql://localhost:3306/task_management";
	String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	String GETALLCREDS = "SELECT * FROM credential";
	
	String GETALLPROJECTS = "SELECT * FROM project";
	String GETALLTASK = "SELECT * FROM task";
	String GETALLTASKSTATUS = "SELECT * FROM taskstatus";
}