package com.erickisee.app.models;

public class Category {
	
	private String name, description;
	
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
	
	
	
	

}
