package sims.model;

public class Arrow {
	private String productID;
	private String arrowID;
	private String tipsArrow;
	private String shaftArrow;
	private String spineArrow;

	public Arrow() {
		super();
	}
	
	public Arrow(String productID, String arrowID, String tipsArrow, String shaftArrow, String spineArrow) {
		super();
		this.productID = productID;
		this.arrowID = arrowID;
		this.tipsArrow = tipsArrow;	
		this.arrowID = shaftArrow;
		this.tipsArrow = spineArrow;	
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getBowID() {
		return arrowID;
	}

	public void setBowID(String arrowID) {
		this.arrowID = arrowID;
	}

	public String getStringBow() {
		return tipsArrow;
	}

	public void setStringBow(String tipsArrow) {
		this.tipsArrow = tipsArrow;
	}
	
	public String getShaftArrow() {
		return shaftArrow;
	}

	public void setShaftArrow(String shaftArrow) {
		this.shaftArrow = shaftArrow;
	}
	
	public String getSpineArrow() {
		return spineArrow;
	}

	public void setSpineArrow(String spineArrow) {
		this.spineArrow = spineArrow;
	}

	public String getArrowID() {
		return arrowID;
	}

	public void setArrowID(String arrowID) {
		this.arrowID = arrowID;
	}

	public String getTipsArrow() {
		return tipsArrow;
	}

	public void setTipsArrow(String tipsArrow) {
		this.tipsArrow = tipsArrow;
	}
	
}