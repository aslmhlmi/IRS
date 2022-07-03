package sims.model;

public class Sale {
	private String saleID;
	private String userID;
	private String totalPayment;
	private String dateSale;
	private String statusSale;
	private Sale status;
	private boolean valid;

	public Sale() {
		super();
	}
	
	public Sale(String saleID, String userID, String totalPayment, String dateSale, String statusSale) {
		super();
		this.saleID = saleID;
		this.userID = userID;
		this.totalPayment = totalPayment;
		this.dateSale = dateSale;
		this.statusSale = statusSale;		
	}

	public String getSaleID() {
		return saleID;
	}

	public void setSaleID(String saleID) {
		this.saleID = saleID;
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
	public String getDateSale() {
		return dateSale;
	}

	public void setDateSale(String dateSale) {
		this.dateSale = dateSale;
	}
	public String getStatusSale() {
		return statusSale;
	}

	public void setStatusSale(String statusSale) {
		this.statusSale = statusSale;
	}
	
	public Sale getStatus() {
		return status;
	}

	public void setStatus(Sale status) {
		this.status = status;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
		
	}
}
