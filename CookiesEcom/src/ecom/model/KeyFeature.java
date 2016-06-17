package ecom.model;

import java.io.Serializable;

public class KeyFeature implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long   id;
	private long   productId;
	private String key;
	private String value;
	
	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setKey(String key) {
		this.key = key.trim();
	}
	public void setValue(String value) {
		this.value = value.trim();
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
