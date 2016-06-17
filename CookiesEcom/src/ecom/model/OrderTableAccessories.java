package ecom.model;

import java.io.Serializable;

public class OrderTableAccessories implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long   id;
	private long   orderTableId;
	private String trackingIdType;
	private String formId;
	private String trackNumber;
	private String deliveredDate;
	private String pickedUpDate;
	private int    cancellationAfterBooked;
	private String courier;
	
	
	//Getter & Setter
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderTableId() {
		return orderTableId;
	}
	public void setOrderTableId(long orderTableId) {
		this.orderTableId = orderTableId;
	}
	public String getTrackingIdType() {
		return trackingIdType;
	}
	public void setTrackingIdType(String trackingIdType) {
		this.trackingIdType = trackingIdType;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public String getPickedUpDate() {
		return pickedUpDate;
	}
	public void setPickedUpDate(String pickedUpDate) {
		this.pickedUpDate = pickedUpDate;
	}
	public int getCancellationAfterBooked() {
		return cancellationAfterBooked;
	}
	public void setCancellationAfterBooked(int cancellationAfterBooked) {
		this.cancellationAfterBooked = cancellationAfterBooked;
	}
	public String getCourier() {
		return courier;
	}
	public void setCourier(String courier) {
		this.courier = courier;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
