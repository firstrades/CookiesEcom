package ecom.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.Threads.APIDataThread;
import ecom.model.CartWishlist;
import ecom.model.Product;
import ecom.model.TwoObjects;
import ecom.model.User;

public class ApiDataMultiThreadBean {
	
	private List<TwoObjects<Product, CartWishlist>> productBeanAndQtyList;
	private User user;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ApiDataMultiThreadBean(List<TwoObjects<Product, CartWishlist>> productBeanAndQtyList, User user, 
			HttpServletRequest request, HttpServletResponse response) {
		this.productBeanAndQtyList = productBeanAndQtyList;
		this.user = user;
		this.request = request;
		this.response = response;
	}
	
	public List<TwoObjects<BigDecimal, String>> getRateAndDeliveryList() {
		
		List<TwoObjects<BigDecimal, String>> apiData = new ArrayList<>();		
		List<Thread> threads = new ArrayList<Thread>();
		
		for (TwoObjects<Product, CartWishlist> productIdAndQty : productBeanAndQtyList) {
		
			long productId = productIdAndQty.getObj1().getProductId();
			int  qty       = productIdAndQty.getObj2().getQty();
			
			APIDataThread apiDataThread = new APIDataThread(productId, user, qty, request, response);
			apiDataThread.start();
			TwoObjects<BigDecimal, String> apiRateAndDelivery = apiDataThread.getApiRateAndDelivery();			
			
			apiData.add(apiRateAndDelivery);
			threads.add(apiDataThread);
			
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		//Print
		for (TwoObjects<BigDecimal, String> twoObjects : apiData) {
			
			System.out.println(twoObjects.getObj1());
			System.out.println(twoObjects.getObj2());
		}
		
		
		
		return apiData;
	}	

}
