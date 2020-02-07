package de.unidue.inf.is.domain;

public class Category {
	private int categoryID;
	private String name;
	private String icon;
	
	public Category(int categoryID, String name, String icon) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.icon = icon;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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
