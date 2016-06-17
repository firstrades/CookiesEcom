package ecom.model;

import java.io.Serializable;

public class GarmentOrder implements Serializable {
	private static final long serialVersionUID = 1L;	

	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
