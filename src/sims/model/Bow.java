package sims.model;

public class Bow {
	private String productID;
	private String bowID;
	private String stringBow;

	public Bow() {
		super();
	}
	
	public Bow(String productID, String bowID, String stringBow) {
		super();
		this.productID = productID;
		this.bowID = bowID;
		this.stringBow = stringBow;	
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getBowID() {
		return bowID;
	}

	public void setBowID(String bowID) {
		this.bowID = bowID;
	}

	public String getStringBow() {
		return stringBow;
	}

	public void setStringBow(String stringBow) {
		this.stringBow = stringBow;
	}
}


