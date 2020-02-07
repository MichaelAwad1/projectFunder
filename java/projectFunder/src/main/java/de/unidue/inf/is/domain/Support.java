package de.unidue.inf.is.domain;

public class Support {
	private String donor;
	private int project;
	private double amount;
	private String donationStatus;
	private String name;
	
	public Support(String donor, int project, double amount, String donationStatus , String name) {
		super();
		this.donor = donor;
		this.project = project;
		this.amount = amount;
		this.donationStatus = donationStatus;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Support [donor=" + donor + ", project=" + project + ", amount=" + amount + ", donationStatus="
				+ donationStatus + ", name=" + name + "]";
	}

	public String getDonor() {
		return donor;
	}
	public void setDonor(String donor) {
		this.donor = donor;
	}
	public int getProject() {
		return project;
	}
	public void setProject(int project) {
		this.project = project;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDonationStatus() {
		return donationStatus;
	}
	public void setDonationStatus(String donationStatus) {
		this.donationStatus = donationStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
