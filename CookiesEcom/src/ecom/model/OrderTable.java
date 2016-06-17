package ecom.model;

import java.io.Serializable;

public class OrderTable implements Serializable {
	private static final long serialVersionUID = 1L;

	//Order Table Data
	private long   id;
	private long   customerId;
	private long   productId;
	private long   sellerId;
	private int    qty;
	private double sellPrice;
	private double shippingCost;
	private String warranty;
	private String orderId;
	private String bookedDateTime;
	private String status;
	private String size;
	private String orderState;
	private String delivered;
	private String paymentType;	
	private DeliveryAddress deliveryAddress; // Buyer
	private OrderTableAccessories orderTableAccessories;
	private User user;
	
	
	public OrderTable() {
		deliveryAddress       = new DeliveryAddress();
		orderTableAccessories = new OrderTableAccessories();
		user                  = new User();
	}
	
	
	//Getter & Setter
	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getDelivered() {
		return delivered;
	}
	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBookedDateTime() {
		return bookedDateTime;
	}
	public void setBookedDateTime(String dateTime) {
		this.bookedDateTime = dateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public OrderTableAccessories getOrderTableAccessories() {
		return orderTableAccessories;
	}
	public void setOrderTableAccessories(OrderTableAccessories orderTableAccessories) {
		this.orderTableAccessories = orderTableAccessories;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
	
}
