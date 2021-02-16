package com.erickisee.app.models;

public class Product {
	
	public Sale getSale() {
		Sale sale = new Sale();
		return sale;
	}
	
	
	@Override
	public String toString() {
		return "Product [imei=" + imei + ", code=" + code + ", datetime=" + datetime + ", id=" + id + ", categoryId="
				+ categoryId + ", price=" + price + ", userId=" + userId + "]";
	}

	private String imei, code, datetime;
	
	private int id, categoryId , price, userId;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
