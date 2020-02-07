package de.unidue.inf.is.app;

import java.util.ArrayList;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.Support;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.interfaces.UserCommands;
import de.unidue.inf.is.stores.UserStore;

public class Application implements UserCommands {
	
	private static Application instance;

	
	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}
	

	@Override
	public ArrayList<Project> queryAllProjects(String status) {
		ArrayList<Project> result = UserStore.getInstance().getAllProjects(status);
		
		return result;
	}
	

	@Override
	public ArrayList<Project> queryProjectsByTitle(String title) {
		ArrayList<Project> result = UserStore.getInstance().getProjectsByTitle(title);
		return result;
	}

	@Override
	public Project queryParentProject(String pre) {
		int preSQL = Integer.parseInt(pre);
		Project project = UserStore.getInstance().getParentProject(preSQL);
		return project;
	}

	@Override
	public ArrayList<Project> queryAllUserProjects(String email) {
		ArrayList<Project> result = UserStore.getInstance().getAllUserProjects(email);
		return result;
	}

	@Override
	public ArrayList<Project> queryAllSupportedProjects(String email) {
		ArrayList<Project> result = UserStore.getInstance().getAllSupportedProjects(email);
		return result;
	}

	@Override
	public ArrayList<Comment> queryAllProjectComments(String projectID) {
		int projectIDSQL = Integer.parseInt(projectID);
		ArrayList<Comment> result = UserStore.getInstance().getAllProjectComments(projectIDSQL);
		return result;
	}

	@Override
	public ArrayList<Support> queryAllProjectDonations(String projectID) {
		int projectIDSQL = Integer.parseInt(projectID);
		ArrayList<Support> result = UserStore.getInstance().getAllProjectDonations(projectIDSQL);
		return result;
	}

	@Override
	public boolean addProject(String title, String description, String status, String fundingLimit,
			String creator, String pre, String category) {
		int preSQL = Integer.parseInt(pre);
		int categorySQL = Integer.parseInt(category);
		double fundingLimitSQL = Double.parseDouble(fundingLimit);
		return UserStore.getInstance().saveProject(title, description, fundingLimitSQL, 
													creator, preSQL, categorySQL);
		}

	@Override
	public boolean addComment(String text, String commentStatus, String projectID, String email) {
		
		int projectIDSQL = Integer.parseInt(projectID);
		return UserStore.getInstance().saveComment(text, commentStatus, projectIDSQL, email);
	}

	@Override
	public boolean addSupport(String donor , String project , String amount , String donationStatus) {
		int projectSQL = Integer.parseInt(project);
		double amountSQL = Double.parseDouble(amount);
		return UserStore.getInstance().saveSupport(donor, projectSQL, amountSQL, donationStatus);
	}

	@Override
	public boolean updateProject(String projectID , String title, String description, String status, String fundingLimit,
			String creator, String pre, String category) {
		int preSQL = Integer.parseInt(pre);
		int projectIDSQL = Integer.parseInt(projectID);
		double fundingLimitSQL = Double.parseDouble(fundingLimit);
		int categorySQL = Integer.parseInt(category);
		return UserStore.getInstance().editProject(projectIDSQL, title, description, status, fundingLimitSQL, creator, preSQL, categorySQL);
	}


	@Override
	public User queryUserInformation(String email) {
		User user = UserStore.getInstance().getUserInformation(email);
		return user;
	}


	@Override
	public ArrayList<Support> queryAllUserSupports(String email) {
		ArrayList<Support> result = UserStore.getInstance().getAllUserSupports(email);
		return result;
	}


	@Override
	public boolean removeProject(String id) {
		int idSQL = Integer.parseInt(id);
		return UserStore.getInstance().deleteProject(idSQL);
	}


	@Override
	public ArrayList<User> queryAllUsers() {
		return UserStore.getInstance().getAllUsers();
	}

	

}
