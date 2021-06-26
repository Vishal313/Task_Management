package com.tasking.Task_Management.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper implements DBQuery{
	private Connection conn;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public DBHelper() {
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBBASEURL, "root", "");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
