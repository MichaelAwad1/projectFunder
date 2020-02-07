package de.unidue.inf.is.domain;
import java.util.ArrayList;

public class Project {

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", title=" + title + ", description=" + description + ", status="
				+ status + ", fundingLimit=" + fundingLimit + ", creator=" + creator + ", pre=" + pre + ", category="
				+ category + ", totalDonations=" + totalDonations + ", name=" + name + ", icon=" + icon + ", comments="
				+ comments + ", supporters=" + supporters + "]";
	}

	private int projectID;
	private String title;
	private String description;
	private String status;
	private double fundingLimit;
	private String creator;
	private int pre;
	private int category;
	private double totalDonations;
	private String name;
	private String icon;
	
	ArrayList<Comment> comments;
	ArrayList<Support> supporters;
	

	public Project(int projectID, String title, String description, String status, double fundingLimit, String creator,
			int pre, int category, double totalDonations , String name , String icon) {
		super();
		this.projectID = projectID;
		this.title = title;
		this.description = description;
		this.status = status;
		this.fundingLimit = fundingLimit;
		this.creator = creator;
		this.pre = pre;
		this.category = category;
		this.totalDonations = totalDonations;
		this.name = name;
		this.icon = icon;
		
		comments = new ArrayList<Comment>();
		supporters = new ArrayList<Support>();
		
	}

	public Project() {
		// TODO Auto-generated constructor stub
	}



	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFundingLimit() {
		return fundingLimit;
	}

	public void setFundingLimit(double fundingLimit) {
		this.fundingLimit = fundingLimit;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public ArrayList<Support> getSupporters() {
		return supporters;
	}

	public void setSupporters(ArrayList<Support> supporters) {
		this.supporters = supporters;
	}

	public double getTotalDonations() {
		return totalDonations;
	}

	public void setTotalDonations(double totalDonations) {
		this.totalDonations = totalDonations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
		

}
