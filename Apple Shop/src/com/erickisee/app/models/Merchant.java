package com.erickisee.app.models;

public class Merchant {
	
	private String  email, name , phoneNumber;
	@Override
	public String toString() {
		return "Merchant [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + ", id=" + id + "]";
	}

	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
