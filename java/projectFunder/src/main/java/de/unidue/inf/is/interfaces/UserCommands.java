package de.unidue.inf.is.interfaces;

import java.util.ArrayList;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;
import de.unidue.inf.is.domain.User;

public interface UserCommands {
	public ArrayList<User> queryAllUsers();
	public ArrayList<Project> queryAllProjects(String status);
	public ArrayList<Project> queryProjectsByTitle(String title);
	public Project queryParentProject(String pre); 
	public ArrayList<Project> queryAllUserProjects(String email);  
	public ArrayList<Project> queryAllSupportedProjects(String email); 
	
	public ArrayList<Comment> queryAllProjectComments(String projectID); 
	
	public ArrayList<Support> queryAllProjectDonations(String projectID);
	public ArrayList<Support> queryAllUserSupports(String email );
	
	
	public boolean addProject(String title, String description, String status,
			String fundingLimit, String creator, String pre ,String category);	
	public boolean addComment(String text, String donationStatus, String projectID, String email );	
	public boolean addSupport(String donor , String project , String amount , String donationStatus);
	public boolean updateProject(String projectID , String title, String description, String status, String fundingLimit,
			String creator, String pre, String category);
	public User queryUserInformation(String email);
	public boolean removeProject(String id);
}
