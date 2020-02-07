package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class Comment {

	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", text=" + text + ", date=" + date + ", commentStatus="
				+ commentStatus + ", projectID=" + projectID + ", userName=" + userName + "]";
	}

	private int commentID;
	private String text;
	private Timestamp date;
	private String commentStatus;
	private int projectID;
	private String userName;
	
	

	public Comment(int commentID, String text, Timestamp date, String commentStatus, int projectID, String userName) {
		super();
		this.commentID = commentID;
		this.text = text;
		this.date = date;
		this.commentStatus = commentStatus;
		this.projectID = projectID;
		this.userName = userName;
	}


	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public int getProject() {
		return projectID;
	}

	public void setProject(int projectID) {
		this.projectID = projectID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
