package com.erickisee.app.models;

public class Sale {
	
	private String datetime;
	
	@Override
	public String toString() {
		return "Sale [datetime=" + datetime + ", id=" + id + ", productId=" + productId + ", userId=" + userId
				+ ", merchantId=" + merchantId + ", customerId=" + customerId + ", amount=" + amount + "]";
	}

	private int id, productId, userId, merchantId, customerId, amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
