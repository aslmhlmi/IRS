package sims.model;

public class Orders_Product {
	
	private String orderID;
	private String productID;
	private String orderQuantity;
	
	
	
	private String teacherName;
	private String subjectName;
	public boolean valid;
	
	public Orders_Product() {
		super();
	}

	public Orders_Product(String orderID, String productID, String orderQuantity) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.orderQuantity = orderQuantity;
	}	

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
