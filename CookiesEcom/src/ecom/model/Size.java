package ecom.model;

import java.io.Serializable;

public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	private long   id;
	private long   productId;
	private String size;
	private int    count;
	
	
	public String getSize() {
		return size;
	}
	public int getCount() {
		return count;
	}
	public void setSize(String size) {
		this.size = size.trim().toUpperCase();
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public long getProductId() {
		return productId;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	
	
}
