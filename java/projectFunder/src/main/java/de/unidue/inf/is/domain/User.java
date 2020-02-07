package de.unidue.inf.is.domain;

import java.util.ArrayList;

public class User {
	private String email;
	private String name;
	private String profileDescription;
	private Account account;
	
	
	
	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", profileDescription=" + profileDescription + ", balance="
				+ account.getBalance() + ", secretnumber = " +account.getSecretNumber() +"]";
	}

	private int numSupported;
	private int numCreated;
	
	private ArrayList<Project> createdProjects;
	private ArrayList<Project> supportedProjects;
	
	private ArrayList<Support> supporters;
	

	
	public User(String email, String name, String profileDescription, Account account) {
		super();
		this.email = email;
		this.name = name;
		this.profileDescription = profileDescription;
		this.account = account;
		createdProjects = new ArrayList<Project>();
		supportedProjects = new ArrayList<Project>();
		supporters = new ArrayList<Support>();
		
		
		
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<Project> getCreatedProjects() {
		return createdProjects;
	}

	public void setCreatedProjects(ArrayList<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}

	public ArrayList<Project> getSupportedProjects() {
		return supportedProjects;
	}

	public void setSupportedProjects(ArrayList<Project> supportedProjects) {
		this.supportedProjects = supportedProjects;
	}

	public ArrayList<Support> getSupporters() {
		return supporters;
	}

	public void setSupporters(ArrayList<Support> supporters) {
		this.supporters = supporters;
	}

	public int getNumSupported() {
		return numSupported;
	}

	public void setNumSupported(ArrayList<Project> p) {
		this.numSupported = p.size();
	}

	public int getNumCreated() {
		return numCreated;
	}

	public void setNumCreated(ArrayList<Project> p) {
		this.numCreated = p.size();
	}
	
	
	
	
}
