package ecom.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookedProduct implements Serializable {
	private static final long serialVersionUID = -2055843779924333899L;

	private long productId;
	private long qty;
	private double sellPrice;	
	private int stock;
	private String warranty;
	private Product productBean;
	private GarmentOrder garmentOrder;
	private long cartWishlistId;	
	
	//Api
	private BigDecimal rate;
	private String     delivery;
	
	
	
	public BookedProduct() {
		productBean  = new Product();
		garmentOrder = new GarmentOrder();
	}
	
	
	
	
	public Product getProductBean() {
		return productBean;
	}
	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public GarmentOrder getGarmentOrder() {
		return garmentOrder;
	}
	public void setGarmentOrder(GarmentOrder garmentOrder) {
		this.garmentOrder = garmentOrder;
	}




	public long getCartWishlistId() {
		return cartWishlistId;
	}




	public void setCartWishlistId(long cartWishlistId) {
		this.cartWishlistId = cartWishlistId;
	}

	
}
