package ecom.model;

import java.io.Serializable;

public class Price implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double listPrice;
	private double discount;
	private double salePriceCustomer;
	private double markup;
	private double salePriceToAdmin;
	private double profitMarginPercentage;
	private double manufacturingCost;
	
	
	
	public double getSalePriceCustomer() {
		return salePriceCustomer;
	}
	public void setSalePriceCustomer(double salePriceCustomer) {
		this.salePriceCustomer = salePriceCustomer;
	}
	public double getProfitMarginPercentage() {
		return profitMarginPercentage;
	}
	public void setProfitMarginPercentage(double profitMarginPercentage) {
		this.profitMarginPercentage = profitMarginPercentage;
	}
	public double getManufacturingCost() {
		return manufacturingCost;
	}
	public void setManufacturingCost(double manufacturingCost) {
		this.manufacturingCost = manufacturingCost;
	}
	public double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getSalePriceToAdmin() {
		return salePriceToAdmin;
	}
	public void setSalePriceToAdmin(double salePriceToAdmin) {
		this.salePriceToAdmin = salePriceToAdmin;
	}
	public double getMarkup() {
		return markup;
	}
	public void setMarkup(double markup) {
		this.markup = markup;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/******************* Process Methods ************************/
	
	public double getMarkedupSellPrice() {	
		double newSalePrice = getMarkup() + getSalePriceToAdmin();
		return round(newSalePrice, 2);
	}
	
	public double getMarkedupDiscount() {	
		
		double ratio      = getMarkedupSellPrice() / getListPrice();
		double percentage = (1 - ratio) * 100;
		
		return round(percentage, 2);
	}
	
	/********** private method ************/
	
	private double round(double value, int places) {
		
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    
	    return (double) tmp / factor;
	}
	
	
}
