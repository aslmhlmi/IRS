package sims.model;

public class Sales_Product {
	
	private String salesID;
	private String productID;
	private String salesQuantity;
	
	
	
	private String teacherName;
	private String subjectName;
	public boolean valid;
	
	public Sales_Product() {
		super();
	}

	public Sales_Product(String salesID, String productID, String salesQuantity) {
		super();
		this.salesID = salesID;
		this.productID = productID;
		this.salesQuantity = salesQuantity;
	}	

	public String getSalesID() {
		return salesID;
	}

	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(String salesQuantity) {
		this.salesQuantity = salesQuantity;
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
