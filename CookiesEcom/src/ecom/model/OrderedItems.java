package ecom.model;

import java.io.Serializable;

public class OrderedItems implements Serializable {
	private static final long serialVersionUID = 1L;

	private int    id;
	private long   productId;
	private int    qty;
	private String size;
	private double sellPriceWithShipping;
	private String sellerCompany;
	private String productName;
	private String companyName;
	private String tranxId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getSellPriceWithShipping() {
		return sellPriceWithShipping;
	}
	public void setSellPriceWithShipping(double sellPriceWithShipping) {
		this.sellPriceWithShipping = sellPriceWithShipping;
	}
	public String getSellerCompany() {
		return sellerCompany;
	}
	public void setSellerCompany(String sellerCompany) {
		this.sellerCompany = sellerCompany;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTranxId() {
		return tranxId;
	}
	public void setTranxId(String tranxId) {
		this.tranxId = tranxId;
	}
	
	
	
}
