package de.unidue.inf.is.interfaces;


import java.util.ArrayList;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;
import de.unidue.inf.is.domain.User;

public interface IProjects {
	
	public ArrayList<User> getAllUsers();
	public ArrayList<Project> getAllProjects(String status);  
	public ArrayList<Project> getProjectsByTitle(String title); 
	public Project getParentProject(int pre); 
	public ArrayList<Project> getAllUserProjects(String email);  
	public ArrayList<Project> getAllSupportedProjects(String email); 
	
	public ArrayList<Support> getAllProjectDonations(int projectID); 
	public ArrayList<Support> getAllUserSupports(String email );
	
	public ArrayList<Comment> getAllProjectComments(int projectID); 
	
	public ArrayList<Comment> getAllUserComments(User user);	
	
	public boolean saveProject(String title, String description, double fundingLimit,
								String creator, int pre, int category);
	public boolean editProject(int projectID, String title, String description, 
			String status, double fundingLimit, String creator,  int pre, int category);
	public boolean deleteProject(int id);
	public boolean saveComment( String text, String commentStatus, int projectID , String email );
	public boolean saveSupport(String donor , int project , double amount , String donationStatus);
	public User getUserInformation(String email);
	
	
}
