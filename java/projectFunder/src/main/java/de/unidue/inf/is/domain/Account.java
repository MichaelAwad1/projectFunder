package de.unidue.inf.is.domain;

public class Account {
	private String owner;
	private double balance;
	private String secretNumber;
	
	
	public Account(String owner, double balance, String secretNumber) {
		super();
		this.owner = owner;
		this.balance = balance;
		this.secretNumber = secretNumber;
	}


	public Account() {
		// TODO Auto-generated constructor stub
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public String getSecretNumber() {
		return secretNumber;
	}


	public void setSecretNumber(String secretNumber) {
		this.secretNumber = secretNumber;
	}
	
	
}
