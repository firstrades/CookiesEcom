package ecom.model;

import java.io.Serializable;

	public class CustomerOrderHistroy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long   id; // order_table ID
	private long   orderTableId;
	private long   productId;
	private int    qty;
	private double sellPrice;
	private double shippingCost;
	private String warranty;
	private String orderId;
	private String status;
	private String size;
	private String orderState;
	private String paymentType;
	private String deliveredDate;
	private int    calcellationAfterBooked;
	private String sellerCompany;
	private String productName;
	private String companyName;
	private String orderBookedDate;
	
	
	//Getter & Setter
	
	
	public long getId() {
		return id;
	}
	public String getOrderBookedDate() {
		return orderBookedDate;
	}
	public void setOrderBookedDate(String orderBookedDate) {
		this.orderBookedDate = orderBookedDate;
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
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public int getCalcellationAfterBooked() {
		return calcellationAfterBooked;
	}
	public void setCalcellationAfterBooked(int calcellationAfterBooked) {
		this.calcellationAfterBooked = calcellationAfterBooked;
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

}

