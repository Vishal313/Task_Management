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
	private String method;
	private Map<String, Object> response = new HashMap<String, Object>();
	
	public Map<String, Object> getResponse(){
		return response;
	}
	
	public String execute() {
		method = ServletActionContext.getRequest().getMethod();
		try {
			if (method.equals("POST")) {
				checkCredentials();
			}	
			else if (method.equals("PUT")) {
				insertCredentials();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public void checkCredentials() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		ArrayList<Credential> credList = CredentialRepository.findAllCredential();
		
		boolean loggedIn = false;
		int employee_id = 0;
		for (int i = 0; i < credList.size(); i++) {
			String dbUser = credList.get(i).getUser_name();
			String dbPass = credList.get(i).getPassword();
			if (dbUser.equals(map.get("user_name")) && dbPass.equals(getMd5(map.get("password")))) {
				loggedIn = true;
				employee_id = credList.get(i).getEmployee_id();
			}
		}
		
		if (loggedIn) {
			System.out.println("Login Successfull");
			response.put("employee_id", employee_id);
			response.put("status", "OK");
			response.put("statuscode", 200);
			res.setStatus(200);
		} else {
			System.out.println("Login Failed");
			res.setStatus(401);	
			response.put("statuscode", 401);
			response.put("status", "Unauthorized");
		}
	}
	
	public void insertCredentials() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = DBService.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		String is_successfull = CredentialRepository.createNewCredential(Integer.parseInt(map.get("employee_id")),
				map.get("user_name"), getMd5(map.get("password")));
		
		if (is_successfull.equals("success")) {
			response.put("statuscode", 200);
			response.put("status", "OK");			
			res.setStatus(200);
		} else {
			response.put("statuscode", 409);
			response.put("status", "Conflict");
			res.setStatus(409);
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