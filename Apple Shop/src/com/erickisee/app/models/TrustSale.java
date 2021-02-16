package com.erickisee.app.models;

public class TrustSale {
		
	@Override
	public String toString() {
		return "TrustSale [datetime=" + datetime + ", comment=" + comment + ", id=" + id + ", productId=" + productId
				+ ", merchantId=" + merchantId + ", userId=" + userId + ", status=" + status + "]";
	}

	private String datetime, comment;
	
	private int id, productId, merchantId, userId, status;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int product_id) {
		this.productId = product_id;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchant_id) {
		this.merchantId = merchant_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
