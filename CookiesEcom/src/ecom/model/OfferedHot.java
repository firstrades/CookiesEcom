package ecom.model;

import java.io.Serializable;

public class OfferedHot implements Serializable {
	private static final long serialVersionUID = -2139075045107226404L;

	private long   productId;
	private boolean isOffered;
	private boolean isHot;
	
	
	public OfferedHot() {
		isOffered = false;
		isHot     = false;
	}
	
	public long getProductId() {
		return productId;
	}
	public boolean isOffered() {
		return isOffered;
	}
	public boolean isHot() {
		return isHot;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public void setOffered(boolean isOffered) {
		this.isOffered = isOffered;
	}
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
	
	
	
	
	
}
