package ecom.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 938330248253012261L;

	private long                id;
	private DeliveryAddress     deliveryAddress;	
	private List<BookedProduct> bookedProductList;		
	
	private double              totalSellPrice;
	private double              totalSavings;
	
	public Order() {
		deliveryAddress = new DeliveryAddress();				
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<BookedProduct> getBookedProductList() {
		return bookedProductList;
	}

	public void setBookedProductList(List<BookedProduct> bookedProductList) {
		this.bookedProductList = bookedProductList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public double getTotalSavings() {
		return totalSavings;
	}

	public void setTotalSavings(double totalSavings) {
		this.totalSavings = totalSavings;
	}
	
	

}
