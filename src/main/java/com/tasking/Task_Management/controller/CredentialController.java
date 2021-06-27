package com.tasking.Task_Management.controller;

import com.tasking.Task_Management.service.*;
import com.tasking.Task_Management.repository.*;
import com.tasking.Task_Management.model.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


public class CredentialController {
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getCredential(){
		return response;
	}
	
	public String execute() {
		String method = ServletActionContext.getRequest().getMethod();
		if (method.equals("POST")) 
			checkCredentials();
		else if (method.equals("PUT")) 
			insertCredentials();
		return "success";
	}
	
	public void checkCredentials() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		ArrayList<Credential> credList = CredentialRepository.findAllCredential();
		
		boolean loggedIn = false;
		boolean userNameFlag = false;
		int employee_id = 0;
		for (int i = 0; i < credList.size(); i++) {
			String dbUser = credList.get(i).getUser_name();
			String dbPass = credList.get(i).getPassword();
			if (dbUser.equals(map.get("user_name"))){
				userNameFlag = true;
			}
			if (dbUser.equals(map.get("user_name")) && dbPass.equals(getMd5(map.get("password")))) {
				loggedIn = true;
				employee_id = credList.get(i).getEmployee_id();
			}
		}
		if (userNameFlag) {
			if (loggedIn) {
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("id", employee_id);
				String desgn = EmployeeRepository.findDesignation(String.valueOf(employee_id));
				
				temp.put("designation", desgn);
				response.put("employee", temp);
				res.setStatus(200);
			} else {
				response.put("Message", "Password Is Incorrect!");
				res.setStatus(401);	
			}
		} else {
			response.put("Message", "Check Your UserName!");
			res.setStatus(401);
		}
	}
	
	public void insertCredentials() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = null;
		if (map.get("user_name") == null) 
			is_successfull = CredentialRepository.updateCredential(map.get("employee_id"), getMd5(map.get("password")));
		else 
		    is_successfull = CredentialRepository.createNewCredential(Integer.parseInt(map.get("employee_id")),
				map.get("user_name"), getMd5(map.get("password")));
		
		if (is_successfull.equals("success")) {
			res.setStatus(200);
			response.put("Message", "Credentials Created Successfully!");
		} else {
			res.setStatus(409);
			response.put("Message", "Cannot Create Credentials!");
		}
	}
	
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(input.getBytes());
	        BigInteger no = new BigInteger(1, messageDigest);
	        String hashtext = no.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
		}
	}
}