package ecom.model;

import java.io.Serializable;

public class UserAndPickupAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user;
	private DeliveryAddress deliveryAddress;
	
	public UserAndPickupAddress() {
		user            = new User();
		deliveryAddress = new DeliveryAddress();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
