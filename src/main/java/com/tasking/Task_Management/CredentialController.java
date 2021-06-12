package com.tasking.Task_Management;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			if (method.equals("GET")) {
				System.out.println("I am gett!!");
				CredentialRepository credRep = new CredentialRepository();
				ArrayList<Credential> credList = credRep.findAllCredential();
				response.put("credList", credList);
			}
			if (method.equals("POST")) {
				System.out.println("I am post!!");
				CredentialRepository credRep = new CredentialRepository();
				String empid = ServletActionContext.getRequest().getParameter("empid");
				String usrnm = ServletActionContext.getRequest().getParameter("usrnm");
				String pswd = ServletActionContext.getRequest().getParameter("pswd");
				credRep.createNewCredential(5, usrnm, pswd);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "success";
	}
}