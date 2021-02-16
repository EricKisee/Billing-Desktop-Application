package com.erickisee.app.models;

public class Customer {
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", purchases=" + purchases + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + "]";
	}

	private int id, purchases;
	
	private String name , phoneNumber, email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
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
