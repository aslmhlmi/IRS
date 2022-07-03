package sims.model;

public class Product {
	private String productID;
	private String codeProduct;
	private String nameProduct;
	private Product namePro;
	private String priceProduct;
	private String totalProduct;
	private String quantityProduct;
	private String supplierID;	
	private boolean valid;

	public Product() {
		super();
	}
	
	public Product(String productID, String codeProduct, String nameProduct, String priceProduct, String totalProduct, String quantityProduct, String supplierID) {
		super();
		this.productID = productID;
		this.codeProduct = codeProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.totalProduct = totalProduct;
		this.quantityProduct = quantityProduct;
		this.supplierID = supplierID;		
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	
	public Product getNamePro() {
		return namePro;
	}

	public void setNamePro(Product namePro) {
		this.namePro = namePro;
	}

	public String getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(String priceProduct) {
		this.priceProduct = priceProduct;
	}

	public String getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(String totalProduct) {
		this.totalProduct = totalProduct;
	}
	
	public String getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(String quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	
	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
		
	}

}


