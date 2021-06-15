package com.tasking.Task_Management;
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
				System.out.println("I am post!!");
				checkCredentials();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	public void checkCredentials() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap<String,String> map = JDBC.pasrseRequest(request);
		HttpServletResponse res = ServletActionContext.getResponse();
		
		CredentialRepository credRep = new CredentialRepository();
		ArrayList<Credential> credList = credRep.findAllCredential();
		boolean loggedIn = false;
		int employee_id = 0;
		for (int i = 0; i < credList.size(); i++) {
			System.out.println();
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
		} else {
			System.out.println("Login Failed");
			res.setStatus(401);			
		}
		res.setHeader("Access-Control-Allow-Origin", "*");
		
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