package sims.model;

public class Orders {
	private String orderID;
	private String userID;
	private String totalPayment;
	private String dateOrder;
	private String statusOrder;

	public Orders() {
		super();
	}
	
	public Orders(String orderID, String userID, String totalPayment, String dateOrder, String statusOrder) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.totalPayment = totalPayment;
		this.dateOrder = dateOrder;
		this.statusOrder = statusOrder;		
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(String totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
}
