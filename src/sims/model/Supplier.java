package sims.model;

import sims.model.Supplier;

public class Supplier {
	protected String supplierID;
	private String nameSupplier;
	private String emailSupplier;
	private String telSupplier;
	private String addressSupplier;
	private Supplier addressSup;
	private boolean valid;

	public Supplier() {
		super();
	}
	
	public Supplier(String supplierID, String nameSupplier, String emailSupplier, String telSupplier, String addressSupplier) {
		super();
		this.supplierID = supplierID;
		this.nameSupplier = nameSupplier;
		this.emailSupplier = emailSupplier;
		this.telSupplier = telSupplier;
		this.addressSupplier = addressSupplier;		
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
	public String getNameSupplier() {
		return nameSupplier;
	}

	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	public String getEmailSupplier() {
		return emailSupplier;
	}

	public void setEmailSupplier(String emailSupplier) {
		this.emailSupplier = emailSupplier;
	}

	public String getTelSupplier() {
		return telSupplier;
	}

	public void setTelSupplier(String telSupplier) {
		this.telSupplier = telSupplier;
	}

	public String getAddressSupplier() {
		return addressSupplier;
	}

	public void setAddressSupplier(String addressSupplier) {
		this.addressSupplier = addressSupplier;
	}
	
	public Supplier getAddressSup() {
		return addressSup;
	}

	public void setAddressSup(Supplier addressSup) {
		this.addressSup = addressSup;
	}
		
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
		
	}

}


