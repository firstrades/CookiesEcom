package ecom.Implementation.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ecom.model.CartWishlist;
import ecom.model.Product;
import ecom.model.TwoObjects;

public class MultiShippingDelivery {
	
	private List<TwoObjects<Product, CartWishlist>> productBeanAndCW;

	public MultiShippingDelivery(List<TwoObjects<Product, CartWishlist>> productBeanAndCW) {
		this.productBeanAndCW = productBeanAndCW;
	}
	
	public List<TwoObjects<BigDecimal, String>> getRateAndDeliveryList() {
		
		List<TwoObjects<BigDecimal, String>> objects = new ArrayList<>();
		ShippingDelivery shippingDelivery = null;
		TwoObjects<BigDecimal, String> twoObjects = null;
		
		for (TwoObjects<Product, CartWishlist> productCW : productBeanAndCW) {
			
			long productId = productCW.getObj1().getProductId();
			shippingDelivery = new ShippingDelivery(productId);
			BigDecimal shippingRate = shippingDelivery.getRate();
			String delivery = shippingDelivery.getDelivery();
			
			twoObjects = new TwoObjects<BigDecimal, String>();
			twoObjects.setObj1(shippingRate);
			twoObjects.setObj2(delivery);
			
			objects.add(twoObjects);
			
		}
		
		return objects;
	}
}
