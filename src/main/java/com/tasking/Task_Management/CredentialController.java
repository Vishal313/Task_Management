package com.tasking.Task_Management;

import java.util.ArrayList;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

public class CredentialController implements ModelDriven<Object> {
	private Object model;
	private CredentialRepository credentialRepository = new CredentialRepository();
	
	private static ArrayList<Credential> credList;
	{
		credList = credentialRepository.findAllCredential();
	}
	
	public HttpHeaders index() {
		model = credList;
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public String add(String user, String Passwor){
		Credential cred = new Credential(5,"Dustin", "Dustin");
		model = cred;
		return "SUCCESS";
	}
	
	@Override
	public Object getModel() {
		return model;
	}
}