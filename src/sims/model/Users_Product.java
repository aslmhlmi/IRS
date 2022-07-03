package sims.model;

public class Users_Product {
	
	private String userID;
	private String productID;
	private String quantityOrder;
	private Double totalPrice;
	private String orderDate;
	
	public boolean valid;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(String quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

}
