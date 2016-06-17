package ecom.model;

import java.io.Serializable;

public class Cookies implements Serializable {
	private static final long serialVersionUID = 3193792614094278648L;
	
	
	private long productIds;
	private String cartWishlist;
	private int qty;
	private String size;
	
	
	public long getProductIds() {
		return productIds;
	}
	public String getCartWishlist() {
		return cartWishlist;
	}
	public int getQty() {
		return qty;
	}
	public String getSize() {
		return size;
	}
	public void setProductIds(long productIds) {
		this.productIds = productIds;
	}
	public void setCartWishlist(String cartWishlist) {
		this.cartWishlist = cartWishlist;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
	
	
}
