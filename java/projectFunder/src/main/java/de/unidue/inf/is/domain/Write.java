package de.unidue.inf.is.domain;

public class Write {
	private String user;
	private int project;
	private int comment;
	
	public Write(String user, int project, int comment) {
		super();
		this.user = user;
		this.project = project;
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}
	
	
}
